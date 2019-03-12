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

class ConnessioneTestCerca {

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
		System.out.println(linea.toString());
	
	}

	@Test
	void testConnessione() throws Exception{
		try {
			client = new Socket("localhost", 5000);
			System.out.println("Connessione eseguita..");
		
		
		writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
		JSONObject richiesta = new JSONObject();
		richiesta.put("tipometodo", "cercap");
		
		
		writer.write(richiesta.toString());
		
		writer.flush();
		reader = new  BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
		
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			linea = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(reader);
	}
}
