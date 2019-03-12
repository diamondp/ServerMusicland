package clientTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.mysql.cj.xdevapi.JsonArray;

public class ClientTest {

	static Socket client;
	
	
	
	
	
	
	public static void main(String[] args) {
	
		System.out.println("Hey, eccomi..");
		try {
			client = new Socket("localhost", 5000);
			System.out.println("Connessione eseguita..");
			
			
			writer = new OutputStreamWriter(client.getOutputStream(), "UTF-8");
			
			
			
			JSONObject richiesta = new JSONObject();
			richiesta.put("tipometodo", "cercaba");
			
			writer.write(richiesta.toString());
			writer.flush();
			
			reader = new  BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
			//System.out.println("LINNNNEA "+ reader.toString());
			//JsonArray jarray = (JsonArray) new JSONTokener (reader).nextValue();

			//System.out.println(jarray.toString());
			
			
			
		
			
			
			client.close();
			
			
			System.out.println("..");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				
				writer.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		

	}

	
	public static OutputStreamWriter writer;  
	public static BufferedReader	reader;  
	
	private static final String LOGIN="login";
	private static final String LOGOUT="logout";
	private static final String MODIFICA_PROFILO="modificaP";
	private static final String CERCA_B_A ="cercaba";
	private static final String CERCA_P = "cercap";
	private static final String LISTA_ARTISTI="listaArtisti";
	private static final String LISTA_Band="listaBand";
	private static final String LISTA_PROMOTORI="listaPromotori";
	private static final String PUBBLICA_E = "pubblicae";
	private static final String PUBBLICA_PE = "pubblicape";
	private static final String CERCA_EVENTO = "cercaevento";
	private static final String CERCA_PEVENTO = "cercapevento";
	private static final String MODIFICA_EVENTO = "modificaevento";
	private static final String MODIFICA_PEVENTO = "modificapevento";
	private static final String ELIMINA_EVENTO = "eliminaevento";
	private static final String ELIMINA_PEVENTO = "eliminapevento";
	private static final String REG_BAND="reg_band";
	private static final String REG_ARTISTA="reg_artista";
	private static final String REG_PROMOTORE="reg_promotore";
}
