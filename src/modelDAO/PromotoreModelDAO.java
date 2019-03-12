package modelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PromotoreModel;
import pojo.*;

public class PromotoreModelDAO implements PromotoreModel {

	private static final String TABLE_NAME = "promotore";
	private static final String EVENTI = "evento";  
	private static final String PEVENTI = "propostaevento";
	

	@Override
	public void doSave(Promotore promotore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + PromotoreModelDAO.TABLE_NAME + " (emailPromotore, " +
				"password, nome, cognome, age, biografia, sesso, luogo, " +
				"telefono, contattoSocial) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, promotore.getEmail());
			preparedStatement.setString(2, promotore.getPassword());
			preparedStatement.setString(3, promotore.getNome());
			preparedStatement.setString(4, promotore.getCognome());
			preparedStatement.setInt(5, promotore.getAge());
			preparedStatement.setString(6, promotore.getBiografia());
			preparedStatement.setString(7, promotore.getSesso());
			preparedStatement.setString(8, promotore.getLuogo());
			//preparedStatement.setString(9, promotore.getFoto());
			preparedStatement.setString(9, promotore.getNumeroTelefono());
			preparedStatement.setString(10, promotore.getLinkSocial());

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
	public int doUpdate(Promotore promotore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;

		String selectSQL = "UPDATE " + PromotoreModelDAO.TABLE_NAME + " SET password = ? , " +
				"nome = ? , cognome = ? , age = ? , " +
				"biografia = ? , sesso = ? , luogo = ? , " +
				"telefono = ? , contattoSocial = ? "+
				"WHERE emailPromotore = ?";

		try {

			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setString(1, promotore.getPassword());
			preparedStatement.setString(2, promotore.getNome());
			preparedStatement.setString(3, promotore.getCognome());
			preparedStatement.setInt(4, promotore.getAge());
			preparedStatement.setString(5, promotore.getBiografia());
			preparedStatement.setString(6, promotore.getSesso());
			preparedStatement.setString(7, promotore.getLuogo());
			//preparedStatement.setString(8, promotore.getFoto());
			preparedStatement.setString(8, promotore.getNumeroTelefono());
			preparedStatement.setString(9, promotore.getLinkSocial());

			preparedStatement.setString(10, promotore.getEmail());

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
	public boolean doDelete(String emailPromotore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + PromotoreModelDAO.TABLE_NAME + " WHERE emailPromotore = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, emailPromotore);

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
	public Promotore doRetrieveByKey(String emailPromotore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Promotore promotore = new Promotore();

		String selectSQL = "SELECT * FROM " + PromotoreModelDAO.TABLE_NAME + " WHERE emailPromotore = ?";
				
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, emailPromotore);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				promotore.setEmail(rs.getString("emailPromotore"));
				promotore.setPassword(rs.getString("password"));
				promotore.setNome(rs.getString("nome"));
				promotore.setCognome(rs.getString("cognome"));
				promotore.setAge(rs.getInt("age"));
				promotore.setBiografia(rs.getString("biografia"));
				promotore.setSesso(rs.getString("sesso"));
				promotore.setLuogo(rs.getString("luogo"));
				//promotore.setFoto(rs.getString("foto"));
				promotore.setNumeroTelefono(rs.getString("telefono"));
				promotore.setLinkSocial(rs.getString("contattoSocial"));
			}
			
		}
			

		 finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return promotore;
	}
	
	
	public ArrayList<Evento> doRetrieveEventiPromotore(String emailPromotore) throws SQLException{
		ArrayList<Evento> e = new ArrayList<Evento>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String recuperaEventoPromotore = "SELECT * "
				+ " FROM " 
				+ PromotoreModelDAO.TABLE_NAME + " AS P JOIN " + 
				PromotoreModelDAO.EVENTI + " AS E " +
						" WHERE P.emailPromotore = ? AND P.emailPromotore=E.emailPromotore ";

	try {	
		
		connection = ConnectionDriverManager.getConnection();
		preparedStatement = connection.prepareStatement(recuperaEventoPromotore);
		preparedStatement.setString(1, emailPromotore);
		ResultSet re= preparedStatement.executeQuery();
		
		while(re.next()) {
			Evento evento = new Evento();
			
			evento.setIdEvento(re.getInt("idevento"));
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
				

			//String mailPromotore=evento.getEmailPromotore();
			int idevento= evento.getIdEvento();
			
			String queryDbArtista = "select * " + 
					" from artista join ( select PA.idArtistaPartecipante from evento as E inner join promotore as P " + 
					" inner join partecipazione as PA on E.emailPromotore=P.emailPromotore  and  PA.idEventoPartecipazione=E.idevento " + 
					" where P.emailPromotore= ? and idevento = ?  ) as W " + 
					" where artista.idArtista = idArtistaPartecipante";
			
			
			preparedStatement = connection.prepareStatement(queryDbArtista);
			preparedStatement.setString(1, emailPromotore);
			preparedStatement.setInt(2, idevento);
			
			  ResultSet rd = preparedStatement.executeQuery();
			 ArrayList<String> listaArtisti= new ArrayList<String>();
			
			while (rd.next()) {
				String artista = "";

				
				
				artista= (rd.getString("nickname"));
				
				
				listaArtisti.add(artista);
			}
			
			evento.setArtistiPartecipanti(listaArtisti);
			
			String queryDbBand = "select * " + 
					" from band join ( select PA.emailBandPartecipante from evento as E inner join promotore as P " + 
					" inner join partecipazione as PA on E.emailPromotore=P.emailPromotore  and  PA.idEventoPartecipazione=E.idevento " + 
					"							where P.emailPromotore= ? and idevento= ? ) as W " + 
					" where band.emailBand = emailBandPartecipante ";
			
			preparedStatement = connection.prepareStatement(queryDbBand);
			
			preparedStatement.setString(1, emailPromotore);
			preparedStatement.setInt(2, idevento);
			
			ArrayList<String> listaBand = new ArrayList<String>();
			 ResultSet rz = preparedStatement.executeQuery();

			while (rz.next()) {
				String band = "";

				
				
				band = (rz.getString("nomeBand"));
				
				
				
				
				listaBand.add(band);
			}
			
			
			evento.setBandPartecipanti(listaBand);
			
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
		
		
		
		
	@Override
	public ArrayList<Promotore> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Promotore> listaPromotori = new ArrayList<Promotore>();

		String selectSQL = "SELECT * FROM " + PromotoreModelDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Promotore promotore = new Promotore();

				promotore.setEmail(rs.getString("emailPromotore"));
				promotore.setPassword(rs.getString("password"));
				promotore.setNome(rs.getString("nome"));
				promotore.setCognome(rs.getString("cognome"));
				promotore.setAge(rs.getInt("age"));
				promotore.setBiografia(rs.getString("biografia"));
				promotore.setSesso(rs.getString("sesso"));
				promotore.setLuogo(rs.getString("luogo"));
				//promotore.setFoto(rs.getString("foto"));
				promotore.setNumeroTelefono(rs.getString("telefono"));
				promotore.setLinkSocial(rs.getString("contattoSocial"));

				listaPromotori.add(promotore);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return listaPromotori;
	}

	@Override
	public ArrayList<PropostaEvento> doRetrieveProposteEventoPromotore(String emailPromotore) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<PropostaEvento> pp = new ArrayList<PropostaEvento>();
		String recuperaProposteEvento = "SELECT * "
				+ " FROM " 
				+ PromotoreModelDAO.TABLE_NAME + " AS P JOIN " + 
				PromotoreModelDAO.PEVENTI + " AS PE " +
						" WHERE P.emailPromotore = ? AND P.emailPromotore=PE.emailPromotore ";
		
		try {	
			
			
			connection = ConnectionDriverManager.getConnection();
		preparedStatement = connection.prepareStatement(recuperaProposteEvento);
		preparedStatement.setString(1, emailPromotore);
		ResultSet pe = preparedStatement.executeQuery();
		
		
		while (pe.next()) {
		
			PropostaEvento propostaEvento = new PropostaEvento();
			propostaEvento.setIdPropostaEvento (pe.getInt("idpropostaEvento"));
			propostaEvento.setNomePropostaEvento(pe.getString("nomePropostaEvento"));
			propostaEvento.setNomeLocale(pe.getString("nomeLocale"));
			propostaEvento.setDataPropostaEvento(pe.getString("data"));
			propostaEvento.setOrarioPropostaEvento(pe.getString("orario"));
			
			String via = pe.getString("via");
			String citta = pe.getString("citta");
			String provincia = pe.getString("provincia");
			String regione = pe.getString("regione");
			
			propostaEvento.setIndirizzo(new Indirizzo(via, citta, provincia, regione));
			
			ArrayList<String> listaGeneri = new ArrayList<String>();
			String generiFromDatabase = pe.getString("generiRichiesti");
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
			
			propostaEvento.setDescrizione(pe.getString("descrizione"));

		
			
			

			//String mailPromotore=propostaEvento.getEmailPromotore();
			int idevento= propostaEvento.getIdPropostaEvento();
			
			String queryDbArtista = "select * " + 
					" from artista join ( select PA.idArtistaInteressato from propostaevento as E inner join promotore as P " + 
					" inner join interessamento as PA on E.emailPromotore=P.emailPromotore  and  PA.idPropostaEvento=E.idpropostaEvento " + 
					" where P.emailPromotore= ?  and E.idpropostaEvento= ?  ) as W " + 
					" where artista.idArtista = idArtistaInteressato";
			
			
			preparedStatement = connection.prepareStatement(queryDbArtista);
			preparedStatement.setString(1, emailPromotore);
			preparedStatement.setInt(2, idevento);
			
			  ResultSet rd = preparedStatement.executeQuery();
			 ArrayList<String> listaArtisti= new ArrayList<String>();
			
			while (rd.next()) {
				String artista = "";

				
				
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
			
			preparedStatement.setString(1, emailPromotore);
			preparedStatement.setInt(2, idevento);
			
			ArrayList<String> listaBand = new ArrayList<String>();
			 ResultSet rz = preparedStatement.executeQuery();

			while (rz.next()) {
				String band = "";

				
				
				band = (rz.getString("nomeBand"));
	
				
				listaBand.add(band);
			}
			
			
			propostaEvento.setListaBandInteressate(listaBand); 


			
			pp.add(propostaEvento);
		}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}	
		return pp;
	}



}
