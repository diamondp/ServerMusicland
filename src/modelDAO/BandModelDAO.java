package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BandModel;
import pojo.*;

public class BandModelDAO implements BandModel{
	private static final String TABLE_NAME = "band";
	private static final String TABLE_NAME_PARTE = "partecipazione";
	private static final String TABLE_EVENTI = "evento";

	@Override
	public void doSave(Band band) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + BandModelDAO.TABLE_NAME + " (emailBand, " +
				"password, nomeBand, biografia, luogo, generiSuonati, numeroComponenti, " +
				"telefono, contattoSocial) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, band.getEmail());
			preparedStatement.setString(2, band.getPassword());
			preparedStatement.setString(3, band.getNomeBand());
			preparedStatement.setString(4, band.getBiografia());
			preparedStatement.setString(5, band.getLuogo());

			ArrayList<String> generiSuonatiBand = band.getGeneriMusicali();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonatiBand.size(); i++){
				String genereSuonato = generiSuonatiBand.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(6, generiSuonatiStringa);

			//preparedStatement.setString(6, band.getGeneriMusicali());
			preparedStatement.setInt(7, band.getNumeroComponenti());
			//preparedStatement.setString(8, band.getFoto());
			preparedStatement.setString(8, band.getNumeroTelefono());
			preparedStatement.setString(9, band.getLinkSocial());

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
	public int doUpdate(Band band) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE " + BandModelDAO.TABLE_NAME + " SET password = ? ," +
				"nomeBand = ? , biografia = ? , luogo = ? , generiSuonati = ? , numeroComponenti = ? " +
				"telefono = ? , contattoSocial = ? "+
				"WHERE emailBand = ?";

		try {

			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);


			preparedStatement.setString(1, band.getPassword());
			preparedStatement.setString(2, band.getNomeBand());
			preparedStatement.setString(3, band.getBiografia());
			preparedStatement.setString(4, band.getLuogo());

			ArrayList<String> generiSuonatiBand = band.getGeneriMusicali();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonatiBand.size(); i++){
				String genereSuonato = generiSuonatiBand.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(5, generiSuonatiStringa);


			preparedStatement.setInt(6, band.getNumeroComponenti());
			//preparedStatement.setString(7, band.getFoto());
			preparedStatement.setString(7, band.getNumeroTelefono());
			preparedStatement.setString(8, band.getLinkSocial());

			preparedStatement.setString(9, band.getEmail());

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
	public boolean doDelete(String emailBand) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + BandModelDAO.TABLE_NAME + " WHERE emailBand = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, emailBand );

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
	public Band doRetrieveByKey(String emailBand) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Band band = new Band();

		String selectSQL = "SELECT * FROM " + BandModelDAO.TABLE_NAME + " WHERE emailBand = ?";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, emailBand);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				band.setEmail(rs.getString("emailBand"));
				band.setPassword(rs.getString("password"));
				band.setNomeBand(rs.getString("nomeBand"));
				band.setBiografia(rs.getString("biografia"));
				band.setLuogo(rs.getString("luogo"));

				ArrayList<String> listaGeneriSuonati = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiSuonati");
				String singoloGenere = "";
				if(generiFromDatabase.contains("&")){

					String str = generiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 

					for (String a : arrOfStr) {
						singoloGenere = a;
						listaGeneriSuonati.add(singoloGenere);
					}

				}
				else
				{
					listaGeneriSuonati.add(generiFromDatabase); //il db ha restituito un solo genere
				}
				band.setGeneriMusicali(listaGeneriSuonati);

				band.setNumeroComponenti(rs.getInt("numeroComponenti"));
				//band.setFoto(rs.getString("foto"));
				band.setNumeroTelefono(rs.getString("telefono"));
				band.setLinkSocial(rs.getString("contattoSocial"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return band;
	}

	@Override
	public ArrayList<Band> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Band> listaArtisti = new ArrayList<Band>();

		String selectSQL = "SELECT * FROM " + BandModelDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Band band = new Band();

				band.setEmail(rs.getString("emailBand"));
				band.setPassword(rs.getString("password"));
				band.setNomeBand(rs.getString("nomeBand"));
				band.setBiografia(rs.getString("biografia"));
				band.setLuogo(rs.getString("luogo"));

				ArrayList<String> listaGeneriSuonati = new ArrayList<String>();
				String generiFromDatabase = rs.getString("generiSuonati");
				String singoloGenere = "";
				if(generiFromDatabase.contains("&")){
					String str = generiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 

					for (String a : arrOfStr) {
						singoloGenere = a;
						listaGeneriSuonati.add(singoloGenere);
					}
				}
				else
				{
					listaGeneriSuonati.add(generiFromDatabase); //il db ha restituito un solo genere
				}
				band.setGeneriMusicali(listaGeneriSuonati);

				band.setNumeroComponenti(rs.getInt("numeroComponenti"));
				//band.setFoto(rs.getString("foto"));
				band.setNumeroTelefono(rs.getString("telefono"));
				band.setLinkSocial(rs.getString("contattoSocial"));

				listaArtisti.add(band);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return listaArtisti;
	}

	@Override
	public ArrayList<Evento> doRetrieveAllEventiBand(String emailBand) throws SQLException {
		ArrayList<Evento> e = new ArrayList<Evento>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String recuperaEventiBand = "SELECT nomeLocale, nomeEvento, data, orario,"
				+ " via, citta, provincia, regione, E.generiSuonati, descrizione FROM " 
				+ BandModelDAO.TABLE_NAME + " AS B JOIN " + 
			BandModelDAO.TABLE_NAME_PARTE + " AS P JOIN " + 
				BandModelDAO.TABLE_EVENTI + " AS E " + 
						" WHERE B.emailBand = ? AND B.emailBand = P.emailBandPartecipante "
						+ "AND P.idEventoPartecipazione = E.idEvento ";

	try {	
		
		connection = ConnectionDriverManager.getConnection();
		preparedStatement = connection.prepareStatement(recuperaEventiBand);
		preparedStatement.setString(1, emailBand);
		ResultSet re= preparedStatement.executeQuery();
		
		while(re.next()) {
			Evento evento = new Evento();
			
			
			evento.setNomeLocale(re.getString("nomeLocale"));
			evento.setNomeEvento(re.getString("nomeEvento"));
			evento.setDataEvento(re.getString("data"));
			evento.setOrarioEvento(re.getString("orario"));
			
			String via = re.getString("via");
			String citta = re.getString("citta");
			String provincia = re.getString("provincia");
			String regione = re.getString("regione");
			
			evento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
			
			ArrayList<String> listaGeneri = new ArrayList<String>();
			String generiFromDatabase = re.getString("generiSuonati");
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
			
			evento.setDescrizione(re.getString("descrizione"));
				
			e.add(evento);
		}
		

	} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return e;
	}
}
