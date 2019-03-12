package modelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionDriverManager {
	

	    private static List<Connection> freeDBConnections;
	    static {
	        freeDBConnections = new LinkedList<Connection>();
	        try {
	            Class.forName("com.mysql.jdbc.cj.musicland");
	        } catch (ClassNotFoundException e) {
	            System.out.println("DB driver:"+ e.getMessage());
	        }
	    }

	    private static synchronized Connection createDBConnection() throws SQLException {
	        Connection newConnection = null;
	      //String ip = "3306"; //localhost
			//String port = "8080";
			//String db = "com.mysql.jdbc.cj.musicland";
			String username = "root";
			String password = "root";

			newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicland?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", 
					username, password);

	        newConnection.setAutoCommit(false);
	        return newConnection;
	    }


	    public static synchronized Connection getConnection() throws SQLException {
	        Connection connection;

	        if (!freeDBConnections.isEmpty()) {
	            connection = (Connection) freeDBConnections.get(0);
	            freeDBConnections.remove(0);

	            try {
	                if (connection.isClosed())
	                    connection = getConnection();
	            } catch (SQLException e) {
	                connection.close();
	                connection = getConnection();
	            }
	        } else {
	            connection = createDBConnection();
	        }

	        return connection;
	    }

	    public static synchronized void releaseConnection(Connection connection) throws SQLException {
	        if(connection != null) freeDBConnections.add(connection);
	    }

}
