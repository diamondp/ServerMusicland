package connessione;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnessioneTest {

	public static OutputStreamWriter writer;  
	public static BufferedReader	reader;
	static Socket client;
	String linea;
	private String risposta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		Connessione server = new Connessione();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	
	}

	@Test
	void testConnessione() {
		try {
			client = new Socket("localhost", 500);
			System.out.println("Connessione eseguita..");
		
		
		writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
		JSONObject richiesta = new JSONObject();
		richiesta.put("tipometodo", "login");
		richiesta.put("email", "artista1@gmail.com");
		richiesta.put("password", "artista1");
		
		writer.write(richiesta.toString());
		
		writer.flush();
		reader = new  BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
		assertNotNull(reader);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
