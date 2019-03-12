package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Artista;
import pojo.Evento;
import pojo.Indirizzo;
import model.ArtistaModel;

public class ArtistaModelDAO implements ArtistaModel {

	private static final String TABLE_NAME = "artista";
	private static final String EVENTO = "evento";
	private static final String TABLE_NAME_PARTE = "partecipazione";

	@Override
	public void doSave(Artista artista) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + ArtistaModelDAO.TABLE_NAME + " (idArtista, email, " +
				"password, isBand, nome, cognome, age, nickname, biografia, sesso, luogo, generiSuonati, " +
				"strumentiSuonati, telefono, contattoSocial) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, artista.getIdArtista());
			preparedStatement.setString(2, artista.getEmail());
			preparedStatement.setString(3, artista.getPassword());
			preparedStatement.setBoolean(4, artista.isBand());
			preparedStatement.setString(5, artista.getNome());
			preparedStatement.setString(6, artista.getCognome());
			preparedStatement.setInt(7, artista.getAge());
			preparedStatement.setString(8, artista.getNickname());
			preparedStatement.setString(9, artista.getBiografia());
			preparedStatement.setString(10, artista.getSesso());
			preparedStatement.setString(11, artista.getLuogo());

			ArrayList<String> generiSuonatiArtista = artista.getGeneriMusicali();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonatiArtista.size(); i++){
				String genereSuonato = generiSuonatiArtista.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(12, generiSuonatiStringa);

			ArrayList<String> strumentiSuonatiArtista = artista.getStrumentiSuonati();
			String strumentiSuonatiStringa = "";
			for(int i = 0; i<strumentiSuonatiArtista.size(); i++){
				String strumentoSuonato = strumentiSuonatiArtista.get(i);
				strumentiSuonatiStringa += strumentoSuonato + "&";
			}
			preparedStatement.setString(13, strumentiSuonatiStringa);

			//preparedStatement.setString(14, artista.getFoto());
			preparedStatement.setString(14, artista.getNumeroTelefono());
			preparedStatement.setString(15, artista.getLinkSocial());

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
	public int doUpdate(Artista artista) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE " + ArtistaModelDAO.TABLE_NAME + " SET email = ? , password = ? ," +
				"isBand = ? , nome = ? , cognome = ? , age = ? , nickname = ? ," +
				"biografia = ? , sesso = ? , luogo = ? , generiSuonati = ? , strumentiSuonati = ? " +
				"telefono = ? , contattoSocial = ? "+
				"WHERE idArtista = ?";

		try {

			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, artista.getEmail());
			preparedStatement.setString(2, artista.getPassword());
			preparedStatement.setBoolean(3, artista.isBand());
			preparedStatement.setString(4, artista.getNome());
			preparedStatement.setString(5, artista.getCognome());
			preparedStatement.setInt(6, artista.getAge());
			preparedStatement.setString(7, artista.getNickname());
			preparedStatement.setString(8, artista.getBiografia());
			preparedStatement.setString(9, artista.getSesso());
			preparedStatement.setString(10, artista.getLuogo());

			ArrayList<String> generiSuonatiArtista = artista.getGeneriMusicali();
			String generiSuonatiStringa = "";
			for(int i = 0; i<generiSuonatiArtista.size(); i++){
				String genereSuonato = generiSuonatiArtista.get(i);
				generiSuonatiStringa += genereSuonato + "&";
			}
			preparedStatement.setString(11, generiSuonatiStringa);

			ArrayList<String> strumentiSuonatiArtista = artista.getStrumentiSuonati();
			String strumentiSuonatiStringa = "";
			for(int i = 0; i<strumentiSuonatiArtista.size(); i++){
				String strumentoSuonato = strumentiSuonatiArtista.get(i);
				strumentiSuonatiStringa += strumentoSuonato + "&";
			}
			preparedStatement.setString(12, strumentiSuonatiStringa);

			//preparedStatement.setString(13, artista.getFoto());
			preparedStatement.setString(13, artista.getNumeroTelefono());
			preparedStatement.setString(14, artista.getLinkSocial());

			preparedStatement.setInt(15, artista.getIdArtista());

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
	public void doDelete(String emailArtista) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//int result = 0;
		String deleteSQL = "DELETE FROM " + ArtistaModelDAO.TABLE_NAME + " WHERE email = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, emailArtista);

			preparedStatement.executeUpdate();
			connection.commit();
			//result = preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		//return (result != 0);
	//	return false;
	}

	@Override
	public Artista doRetrieveByKey(String emailArtista) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Artista artista = new Artista();

		String selectSQL = "SELECT * FROM " + ArtistaModelDAO.TABLE_NAME + " WHERE email = ?";
	

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, emailArtista);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				artista.setIdArtista(rs.getInt("idArtista"));
				artista.setEmail(rs.getString("email"));
				artista.setPassword(rs.getString("password"));
				artista.setBand(rs.getBoolean("isBand"));
				artista.setNome(rs.getString("nome"));
				artista.setCognome(rs.getString("cognome"));
				artista.setAge(rs.getInt("age"));
				artista.setNickname(rs.getString("nickname"));
				artista.setBiografia(rs.getString("biografia"));
				artista.setSesso(rs.getString("sesso"));
				artista.setLuogo(rs.getString("luogo"));

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
				artista.setGeneriMusicali(listaGeneriSuonati);

				ArrayList<String> listaStrumentiSuonati = new ArrayList<String>();
				String strumentiFromDatabase = rs.getString("strumentiSuonati");
				String singoloStrumento = "";
				if(strumentiFromDatabase.contains("&")){

					String str = strumentiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 
					for (String a : arrOfStr) {
						singoloStrumento = a;
						listaStrumentiSuonati.add(singoloStrumento);
					}


				}
				else
				{
					listaStrumentiSuonati.add(strumentiFromDatabase); //il db ha restituito un solo genere
				}
				artista.setStrumentiSuonati(listaStrumentiSuonati);

				//artista.setFoto(rs.getString("foto"));
				artista.setNumeroTelefono(rs.getString("telefono"));
				artista.setLinkSocial(rs.getString("contattoSocial"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return artista;
	}

	@Override
	public ArrayList<Artista> doRetrieveAll(String order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Artista> listaArtisti = new ArrayList<Artista>();
		

		String selectSQL = "SELECT * FROM " + ArtistaModelDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				
				Artista artista = new Artista();
				artista.setBand(rs.getBoolean("isBand"));
	if( ! (artista.isBand() )) {
				artista.setIdArtista(rs.getInt("idArtista"));
				artista.setEmail(rs.getString("email"));
				artista.setPassword(rs.getString("password"));
				artista.setBand(rs.getBoolean("isBand"));
				artista.setNome(rs.getString("nome"));
				artista.setCognome(rs.getString("cognome"));
				artista.setAge(rs.getInt("age"));
				artista.setNickname(rs.getString("nickname"));
				artista.setBiografia(rs.getString("biografia"));
				artista.setSesso(rs.getString("sesso"));
				artista.setLuogo(rs.getString("luogo"));


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
				artista.setGeneriMusicali(listaGeneriSuonati);

				ArrayList<String> listaStrumentiSuonati = new ArrayList<String>();
				String strumentiFromDatabase = rs.getString("strumentiSuonati");
				String singoloStrumento = "";
				if(strumentiFromDatabase.contains("&")){

					String str = strumentiFromDatabase; 
					String[] arrOfStr = str.split("&", 0); 
					for (String a : arrOfStr) {
						singoloStrumento = a;
						listaStrumentiSuonati.add(singoloStrumento);
					}

				}
				else
				{
					listaStrumentiSuonati.add(strumentiFromDatabase); //il db ha restituito un solo genere
				}
				artista.setStrumentiSuonati(listaStrumentiSuonati);

				//artista.setFoto(rs.getString("foto"));
				artista.setNumeroTelefono(rs.getString("telefono"));
				artista.setLinkSocial(rs.getString("contattoSocial"));

				
				listaArtisti.add(artista);
			}
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
	public ArrayList<Evento> doRetrieveAllEventiArtista(String emailArtista) throws SQLException {
		ArrayList<Evento> e = new ArrayList<Evento>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String recuperaEventiArtista = "SELECT nomeLocale, nomeEvento, data, orario,"
				+ " via, citta, provincia, regione, E.generiSuonati, descrizione FROM " 
				+ ArtistaModelDAO.TABLE_NAME + " AS A JOIN " + 
			ArtistaModelDAO.TABLE_NAME_PARTE + " AS P JOIN " + 
				ArtistaModelDAO.EVENTO + " AS E " + 
						" WHERE A.email = ? AND A.idArtista = P.idArtistaPartecipante "
						+ "AND P.idEventoPartecipazione = E.idEvento ";

	try {	
		
		connection = ConnectionDriverManager.getConnection();
		preparedStatement = connection.prepareStatement(recuperaEventiArtista);
		preparedStatement.setString(1, emailArtista);
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
