package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PartecipazioneModel;
import pojo.Interessamento;
import pojo.Partecipazione;

public class PartecipazioneModelDAO implements PartecipazioneModel{

	private static final String TABLE_NAME = "partecipazione";

	@Override
	public void doSave(Partecipazione partecipazione, int idEvento, int idArtista) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + PartecipazioneModelDAO.TABLE_NAME + " (idPartecipazione, idEventoPartecipazione, "
				+ "idArtistaPartecipante) " +
				"VALUES (?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, partecipazione.getIdPartecipazione());
			preparedStatement.setInt(2, idEvento);
			preparedStatement.setInt(3, idArtista);

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
	public void doSave(Partecipazione partecipazione, int idEvento, String emailBand) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + PartecipazioneModelDAO.TABLE_NAME + " (idPartecipazione, idEventoPartecipazione, "
				+ "emailBandPartecipante) " +
				"VALUES (?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, partecipazione.getIdPartecipazione());
			preparedStatement.setInt(2, idEvento);
			preparedStatement.setString(3, emailBand);

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
	public boolean doDelete(int idEvento, int idArtista) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + PartecipazioneModelDAO.TABLE_NAME + " WHERE idEventoPartecipazione = ?"
				+ " AND idArtistaPartecipante = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idEvento);
			preparedStatement.setInt(2, idArtista);

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
	public boolean doDelete(int idEvento, String emailBand) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM " + PartecipazioneModelDAO.TABLE_NAME + " WHERE idEventoPartecipazione = ?"
				+ " AND emailBandPartecipante = ?";
		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, idEvento);
			preparedStatement.setString(2, emailBand);

			result = preparedStatement.executeUpdate();
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
	public Partecipazione doRetrieveByKey(int idEvento) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Partecipazione partecipazione = new Partecipazione();

		String selectSQL = "SELECT * FROM " + PartecipazioneModelDAO.TABLE_NAME + " WHERE idEventoPartecipazione = ?";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idEvento);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				partecipazione.setIdPartecipazione(rs.getInt("idPartecipazione"));
				partecipazione.setIdEvento(rs.getInt("idEventoPartecipazione"));
				partecipazione.setIdBandPartecipante(rs.getString("emailBandPartecipante"));
				partecipazione.setIdArtistaPartecipante(rs.getInt("idArtistaPartecipante"));

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return partecipazione;

	}

	@Override
	public ArrayList<Partecipazione> doretrieveAll(String order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Partecipazione> listaPartecipanti = new ArrayList<Partecipazione>();

		String selectSQL = "SELECT * FROM " + PartecipazioneModelDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Partecipazione partecipazione = new Partecipazione();

				partecipazione.setIdPartecipazione(rs.getInt("idPartecipazione"));
				partecipazione.setIdEvento(rs.getInt("idEventoPartecipazione"));
				partecipazione.setIdBandPartecipante(rs.getString("emailBandPartecipante"));
				partecipazione.setIdArtistaPartecipante(rs.getInt("idArtistaPartecipante"));

				listaPartecipanti.add(partecipazione);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				ConnectionDriverManager.releaseConnection(connection);
			}
		}
		return listaPartecipanti;
	}
}


