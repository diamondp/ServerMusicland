package modelDAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EventoModel;
import pojo.Artista;
import pojo.Band;
import pojo.Evento;
import pojo.Indirizzo;

public class EventoModelDAO implements EventoModel {

	private static final String TABLE_NAME = "evento";

	@Override
	public void doSave(Evento evento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + EventoModelDAO.TABLE_NAME + " (idEvento, nomeLocale, "
				+ "nomeEvento, data, orario, via, citta, provincia, regione, generiSuonati, descrizione, emailPromotore) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, evento.getIdEvento());
			preparedStatement.setString(2, evento.getNomeLocale());
			preparedStatement.setString(3, evento.getNomeEvento());
			preparedStatement.setString(4, evento.getDataEvento());
			preparedStatement.setString(5, evento.getOrarioEvento());

			Indirizzo indirizzoEvento = evento.getIndirizzo();

			String via = indirizzoEvento.getVia();
			String citta = indirizzoEvento.getCitta();
			String provincia = indirizzoEvento.getProvincia();
			String regione = indirizzoEvento.getRegione();

			preparedStatement.setString(6, via);
			preparedStatement.setString(7, citta);
			preparedStatement.setString(8, provincia);
			preparedStatement.setString(9, regione);

			/*
			 * prendere un arraylist formato da più elementi o da uno solo
			 * prendere ciascun elemento
			 * costruire una stringa che lega ogni elemento con &
			 */
			ArrayList<String> generiSuonati = evento.getGeneriSuonati();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonati.size(); i++){
				String genereSuonato = generiSuonati.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(10, generiSuonatiStringa);

			preparedStatement.setString(11, evento.getDescrizione());
			preparedStatement.setString(12, evento.getEmailPromotore());

			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
	}

	@Override
	public int doUpdate(Evento evento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE " + EventoModelDAO.TABLE_NAME + " SET nomeLocale = ? , nomeEvento = ? ," +
				"data = ? , orario = ? , via = ? , citta = ? , provincia = ? ," +
				"regione = ? , generiSuonati = ? , descrizione = ? , emailPromotore = ? "+
				"WHERE idEvento = ?";

		try {

			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, evento.getNomeLocale());
			preparedStatement.setString(2, evento.getNomeEvento());
			preparedStatement.setString(3, evento.getDataEvento());
			preparedStatement.setString(4, evento.getOrarioEvento());

			Indirizzo indirizzoEvento = evento.getIndirizzo();

			String via = indirizzoEvento.getVia();
			String citta = indirizzoEvento.getCitta();
			String provincia = indirizzoEvento.getProvincia();
			String regione = indirizzoEvento.getRegione();

			preparedStatement.setString(5, via);
			preparedStatement.setString(6, citta);
			preparedStatement.setString(7, provincia);
			preparedStatement.setString(8, regione);

			ArrayList<String> generiSuonati = evento.getGeneriSuonati();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonati.size(); i++){
				String genereSuonato = generiSuonati.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(9, generiSuonatiStringa);

			preparedStatement.setString(10, evento.getDescrizione());
			preparedStatement.setString(11, evento.getEmailPromotore());

			preparedStatement.setInt(12, evento.getIdEvento());

			result = preparedStatement.executeUpdate();
			//connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}

		return result; // dovrebbe ritornare uno se è andato tutto ok altrimenti 0
	}

	@Override
	public boolean doDelete(int idEvento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + EventoModelDAO.TABLE_NAME + " WHERE idEvento = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idEvento);

			result = preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public Evento doRetrieveByKey(int idEvento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Evento evento = new Evento();

		String selectSQL = "SELECT * FROM " + EventoModelDAO.TABLE_NAME + " WHERE idEvento = ?";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idEvento);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNomeLocale(rs.getString("nomeLocale"));
				evento.setNomeEvento(rs.getString("nomeEvento"));
				evento.setDataEvento(rs.getString("data"));
				evento.setOrarioEvento(rs.getString("orario"));
				
				String via = rs.getString("via");
				String citta = rs.getString("citta");
				String provincia = rs.getString("provincia");
				String regione = rs.getString("regione");
				
				evento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
				
				ArrayList<String> listaGeneri = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiSuonati");
				String singoloGenere = "";
				if(generiFromDatabase.contains("&")){
					String str = generiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 

					for (String a : arrOfStr) {
						singoloGenere = a;
						listaGeneri.add(singoloGenere);
					}
				}
				else
				{
					listaGeneri.add(generiFromDatabase); //il db ha restituito un solo genere
				}
				evento.setGeneriSuonati(listaGeneri);
				
				evento.setDescrizione(rs.getString("descrizione"));
				evento.setEmailPromotore(rs.getString("emailPromotore"));
				
				/*
				 * AGGIUNGERE LE BAND E GLI ARTISTI CHE VI HANNO PARTECIPATO
				 * FACENDO UNA QUERY CHE USA QUELL'EMAIL DI QUEL PROMOTORE
				 * NELLA TABELLA PARTECIPAZIONE
				 */
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return evento;
	}

	@Override
	public ArrayList<Evento> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Evento> listaEventi = new ArrayList<Evento>();

        String selectSQL = "SELECT * FROM " + EventoModelDAO.TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Evento evento = new Evento();
                
                evento.setIdEvento(rs.getInt("idEvento"));
				evento.setNomeLocale(rs.getString("nomeLocale"));
				evento.setNomeEvento(rs.getString("nomeEvento"));
				evento.setDataEvento(rs.getString("data"));
				evento.setOrarioEvento(rs.getString("orario"));
				
				String via = rs.getString("via");
				String citta = rs.getString("citta");
				String provincia = rs.getString("provincia");
				String regione = rs.getString("regione");
				
				evento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
				
				ArrayList<String> listaGeneri = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiSuonati");
				String singoloGenere = "";
				if(generiFromDatabase.contains("&")){
					String str = generiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 

					for (String a : arrOfStr) {
						singoloGenere = a;
						listaGeneri.add(singoloGenere);
					}
				}
				else
				{
					listaGeneri.add(generiFromDatabase); //il db ha restituito un solo genere
				}
				evento.setGeneriSuonati(listaGeneri);
				
				evento.setDescrizione(rs.getString("descrizione"));
				evento.setEmailPromotore(rs.getString("emailPromotore"));
				
				/*
				 * AGGIUNGERE LE BAND E GLI ARTISTI CHE VI HANNO PARTECIPATO
				 * FACENDO UNA QUERY CHE USA QUELL'EMAIL DI QUEL PROMOTORE
				 * NELLA TABELLA PARTECIPAZIONE
				 */
               
				String mailPromotore=evento.getEmailPromotore();
				int idevento= evento.getIdEvento();
				
				String queryDbArtista = "select * " + 
						" from artista join ( select PA.idArtistaPartecipante from evento as E inner join promotore as P " + 
						" inner join partecipazione as PA on E.emailPromotore=P.emailPromotore  and  PA.idEventoPartecipazione=E.idevento " + 
						" where P.emailPromotore= ?  and idevento= ?  ) as W " + 
						" where artista.idArtista = idArtistaPartecipante";
				
				
				preparedStatement = connection.prepareStatement(queryDbArtista);
				preparedStatement.setString(1, mailPromotore);
				preparedStatement.setInt(2, idevento);
				
				  ResultSet rd = preparedStatement.executeQuery();
				 ArrayList<String> listaArtisti= new ArrayList<String>();
				
				while (rd.next()) {
					String artista ="";

					artista = (rd.getString("nickname"));
					

					
					listaArtisti.add(artista);
				}
				
				evento.setArtistiPartecipanti(listaArtisti);
				
				String queryDbBand = "select * " + 
						" from band join ( select PA.emailBandPartecipante from evento as E inner join promotore as P " + 
						" inner join partecipazione as PA on E.emailPromotore=P.emailPromotore  and  PA.idEventoPartecipazione=E.idevento " + 
						"							where P.emailPromotore= ? and idevento= ? ) as W " + 
						" where band.emailBand = emailBandPartecipante ";
				
				preparedStatement = connection.prepareStatement(queryDbBand);
				
				preparedStatement.setString(1, mailPromotore);
				preparedStatement.setInt(2, idevento);
				
				ArrayList<String> listaBand = new ArrayList<String>();
				 ResultSet rz = preparedStatement.executeQuery();

				while (rz.next()) {
					String band ="";

					band = (rz.getString("nomeBand"));
					
				
					listaBand.add(band);
				}
				
				
				evento.setBandPartecipanti(listaBand);
				
                listaEventi.add(evento);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                ConnectionDriverManager.releaseConnection(connection);
            }
        }
        return listaEventi;
	}

}
