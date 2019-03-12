package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PropostaEventoModel;
import pojo.Artista;
import pojo.Band;
import pojo.Evento;
import pojo.Indirizzo;
import pojo.PropostaEvento;

public class PropostaEventoModelDAO implements PropostaEventoModel{

	private static final String TABLE_NAME = "propostaevento";
	
	@Override
	public void doSave(PropostaEvento propostaEvento) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + PropostaEventoModelDAO.TABLE_NAME + " (idPropostaEvento, nomePropostaEvento, "
				+ "nomeLocale, data, orario, via, citta, provincia, regione, generiRichiesti, descrizione, emailPromotore) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, propostaEvento.getIdPropostaEvento());
			preparedStatement.setString(2, propostaEvento.getNomePropostaEvento());
			preparedStatement.setString(3, propostaEvento.getNomeLocale());
			preparedStatement.setString(4, propostaEvento.getDataPropostaEvento());
			preparedStatement.setString(5, propostaEvento.getOrarioPropostaEvento());

			Indirizzo indirizzoEvento = propostaEvento.getIndirizzo();

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
			ArrayList<String> generiRichiesti = propostaEvento.getGeneriRichiesti();
			String generiRichiestiStringa = "";
			for(int i = 0; i<generiRichiesti.size(); i++){
				String genereSuonato = generiRichiesti.get(i);
				generiRichiestiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(10, generiRichiestiStringa);

			preparedStatement.setString(11, propostaEvento.getDescrizione());
			preparedStatement.setString(12, propostaEvento.getEmailPromotore());

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
	public int doUpdate(PropostaEvento propostaEvento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE " + PropostaEventoModelDAO.TABLE_NAME + " SET nomePropostaEvento = ? , nomeLocale = ? ," +
				"data = ? , orario = ? , via = ? , citta = ? , provincia = ? ," +
				"regione = ? , generiRichiesti = ? , descrizione = ? , emailPromotore = ? "+
				"WHERE idPropostaEvento = ?";

		try {

			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, propostaEvento.getNomePropostaEvento());
			preparedStatement.setString(2, propostaEvento.getNomeLocale());
			preparedStatement.setString(3, propostaEvento.getDataPropostaEvento());
			preparedStatement.setString(4, propostaEvento.getOrarioPropostaEvento());

			Indirizzo indirizzoEvento = propostaEvento.getIndirizzo();

			String via = indirizzoEvento.getVia();
			String citta = indirizzoEvento.getCitta();
			String provincia = indirizzoEvento.getProvincia();
			String regione = indirizzoEvento.getRegione();

			preparedStatement.setString(5, via);
			preparedStatement.setString(6, citta);
			preparedStatement.setString(7, provincia);
			preparedStatement.setString(8, regione);

			ArrayList<String> generiRichiesti = propostaEvento.getGeneriRichiesti();
			String generiRichiestiStringa = "";
			for(int i = 0; i<generiRichiesti.size(); i++){
				String genereSuonato = generiRichiesti.get(i);
				generiRichiestiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(9, generiRichiestiStringa);

			preparedStatement.setString(10, propostaEvento.getDescrizione());
			preparedStatement.setString(11, propostaEvento.getEmailPromotore());

			preparedStatement.setInt(12, propostaEvento.getIdPropostaEvento());

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
	public boolean doDelete(int idPropostaEvento) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + PropostaEventoModelDAO.TABLE_NAME + " WHERE idPropostaEvento = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idPropostaEvento);

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
	public PropostaEvento doRetrieveByKey(int idEvento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PropostaEvento propostaEvento = new PropostaEvento();

		String selectSQL = "SELECT * FROM " + PropostaEventoModelDAO.TABLE_NAME + " WHERE idPropostaEvento = ?";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idEvento);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				propostaEvento.setIdPropostaEvento(rs.getInt("idPropostaEvento"));
				propostaEvento.setNomePropostaEvento(rs.getString("nomePropostaEvento"));
				propostaEvento.setNomeLocale(rs.getString("nomeLocale"));
				propostaEvento.setDataPropostaEvento(rs.getString("data"));
				propostaEvento.setOrarioPropostaEvento(rs.getString("orario"));
				
				String via = rs.getString("via");
				String citta = rs.getString("citta");
				String provincia = rs.getString("provincia");
				String regione = rs.getString("regione");
				
				propostaEvento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
				
				ArrayList<String> listaGeneri = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiRichiesti");
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
				propostaEvento.setGeneriRichiesti(listaGeneri);
				
				propostaEvento.setDescrizione(rs.getString("descrizione"));
				propostaEvento.setEmailPromotore(rs.getString("emailPromotore"));
				
				/*
				 * AGGIUNGERE LE BAND E GLI ARTISTI CHE VI HANNO MESSO "MI INTERESSA"
				 * FACENDO UNA QUERY CHE USA QUELL'EMAIL DI QUEL PROMOTORE
				 * NELLA TABELLA INTERESSAMENTO
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
		return propostaEvento;
	}

	@Override
	public ArrayList<PropostaEvento> doRetrieveAll(String order) throws SQLException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<PropostaEvento> listaProposteEvento = new ArrayList<PropostaEvento>();

        String selectSQL = "SELECT * FROM " + PropostaEventoModelDAO.TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PropostaEvento propostaEvento = new PropostaEvento();
                
                propostaEvento.setIdPropostaEvento(rs.getInt("idPropostaEvento"));
				propostaEvento.setNomePropostaEvento(rs.getString("nomePropostaEvento"));
				propostaEvento.setNomeLocale(rs.getString("nomeLocale"));
				propostaEvento.setDataPropostaEvento(rs.getString("data"));
				propostaEvento.setOrarioPropostaEvento(rs.getString("orario"));
				
				String via = rs.getString("via");
				String citta = rs.getString("citta");
				String provincia = rs.getString("provincia");
				String regione = rs.getString("regione");
				
				propostaEvento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
				
				ArrayList<String> listaGeneri = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiRichiesti");
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
				propostaEvento.setGeneriRichiesti(listaGeneri);
				
				propostaEvento.setDescrizione(rs.getString("descrizione"));
				propostaEvento.setEmailPromotore(rs.getString("emailPromotore"));
				
                
				/*
				 * AGGIUNGERE LE BAND E GLI ARTISTI CHE VI HANNO MESSO "MI INTERESSA"
				 * FACENDO UNA QUERY CHE USA QUELL'EMAIL DI QUEL PROMOTORE
				 * NELLA TABELLA PARTECIPAZIONE
				 */
               

				String mailPromotore=propostaEvento.getEmailPromotore();
				int idevento= propostaEvento.getIdPropostaEvento();
				
				String queryDbArtista = "select * " + 
						" from artista join ( select PA.idArtistaInteressato from propostaevento as E inner join promotore as P " + 
						" inner join interessamento as PA on E.emailPromotore=P.emailPromotore  and  PA.idPropostaEvento=E.idpropostaEvento " + 
						" where P.emailPromotore= ?  and E.idpropostaEvento= ?  ) as W " + 
						" where artista.idArtista = idArtistaInteressato";
				
				
				preparedStatement = connection.prepareStatement(queryDbArtista);
				preparedStatement.setString(1, mailPromotore);
				preparedStatement.setInt(2, idevento);
				
				  ResultSet rd = preparedStatement.executeQuery();
				 ArrayList<String> listaArtisti= new ArrayList<String>();
				
				while (rd.next()) {
					String artista ="";
		
					
					artista= (rd.getString("nickname"));
					
					listaArtisti.add(artista);
				}
				
				 propostaEvento.setListaArtistiInteressati(listaArtisti);
				
				String queryDbBand = "select * " + 
						" from band join ( select PA.emailBandInteressata from propostaevento as E inner join promotore as P " + 
						" inner join interessamento as PA on E.emailPromotore=P.emailPromotore  and  PA.idPropostaEvento=E.idpropostaEvento " + 
						"							where P.emailPromotore= ? and E.idpropostaEvento= ? ) as W " + 
						" where band.emailBand = emailBandInteressata ";
				
				preparedStatement = connection.prepareStatement(queryDbBand);
				
				preparedStatement.setString(1, mailPromotore);
				preparedStatement.setInt(2, idevento);
				
				ArrayList<String> listaBand = new ArrayList<String>();
				 ResultSet rz = preparedStatement.executeQuery();

				while (rz.next()) {
					String band = "";

					band = (rz.getString("nomeBand"));
		
					
					listaBand.add(band);
				}
				
				
				propostaEvento.setListaBandInteressate(listaBand); 

				
				
				
				
				
				
						
				
                listaProposteEvento.add(propostaEvento);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                ConnectionDriverManager.releaseConnection(connection);
            }
        }
        return listaProposteEvento;
	}

}
