package modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.InteressamentoModel;
import pojo.Artista;
import pojo.Indirizzo;
import pojo.Interessamento;

public class InteressamentoModelDAO implements InteressamentoModel{

	private static final String TABLE_NAME = "interessamento";

	@Override
	public void doSave(Interessamento interessamento, int idPropostaEvento, int idArtista) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + InteressamentoModelDAO.TABLE_NAME + " (idInteressamento, idPropostaEvento, "
				+ "idArtistaInteressato) " +
				"VALUES (?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, interessamento.getIdInteressamento());
			preparedStatement.setInt(2, idPropostaEvento);
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
	public void doSave(Interessamento interessamento, int idPropostaEvento, String emailBand) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO " + InteressamentoModelDAO.TABLE_NAME + " (idInteressamento, idPropostaEvento, "
				+ "emailBandInteressata) " +
				"VALUES (?, ?, ?)";

		try {
			connection = ConnectionDriverManager.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, interessamento.getIdInteressamento());
			preparedStatement.setInt(2, idPropostaEvento);
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
	public boolean doDelete(int idPropostaEvento, int idArtista) throws SQLException {

		Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String deleteSQL = "DELETE FROM " + InteressamentoModelDAO.TABLE_NAME + " WHERE idPropostaEvento = ?"
        		+ " AND idArtistaInteressato = ?";
        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idPropostaEvento);
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
	public boolean doDelete(int idPropostaEvento, String emailBand) throws SQLException {

		Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String deleteSQL = "DELETE FROM " + InteressamentoModelDAO.TABLE_NAME + " WHERE idPropostaEvento = ?"
        		+ " AND emailBandInteressata = ?";
        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idPropostaEvento);
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
	public Interessamento doRetrieveByKey(int idPropostaEvento) throws SQLException {

		Connection connection = null;
        PreparedStatement preparedStatement = null;

        Interessamento interessamento = new Interessamento();

        String selectSQL = "SELECT * FROM " + InteressamentoModelDAO.TABLE_NAME + " WHERE idPropostaEvento = ?";

        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idPropostaEvento);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	
            	interessamento.setIdInteressamento(rs.getInt("idInteressamento"));
            	interessamento.setIdPropostaEvento(rs.getInt("idPropostaEvento"));
            	interessamento.setIdArtista(rs.getInt("idArtistaInteressato"));
            	interessamento.setIdBand(rs.getString("emailBandInteressata"));
            	
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                ConnectionDriverManager.releaseConnection(connection);
            }
        }
        return interessamento;
	}

	@Override
	public ArrayList<Interessamento> doRetrieveAll(String order) throws SQLException {

		Connection connection = null;
        PreparedStatement preparedStatement = null;

        ArrayList<Interessamento> listaInteressati = new ArrayList<Interessamento>();

        String selectSQL = "SELECT * FROM " + InteressamentoModelDAO.TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ConnectionDriverManager.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Interessamento interessamento = new Interessamento();
                
                interessamento.setIdInteressamento(rs.getInt("idInteressamento"));
            	interessamento.setIdPropostaEvento(rs.getInt("idPropostaEvento"));
            	interessamento.setIdArtista(rs.getInt("idArtistaInteressato"));
            	interessamento.setIdBand(rs.getString("emailBandInteressata"));
                
                listaInteressati.add(interessamento);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                ConnectionDriverManager.releaseConnection(connection);
            }
        }
        return listaInteressati;
	}

}
