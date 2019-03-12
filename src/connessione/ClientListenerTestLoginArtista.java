package connessione;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientListenerTestLoginArtista {

	JSONObject richiesta;
	ClientListener c;
	JSONArray risposta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// cercaevento = lista eventi pubblicati ;
		richiesta = new JSONObject();
		richiesta.put("tipometodo", "login");
		richiesta.put("email", "artista1@gmail.com");
		richiesta.put("password", "artista1");
		c = new ClientListener(richiesta);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println(richiesta.get("tipometodo"));
		System.out.println("email: " + richiesta.get("email"));
		System.out.println("password: " + richiesta.get("password"));
		System.out.println(risposta.toString());
		
	}

	@Test
	void testTrovaMetodo() {
	
		 risposta = c.trovaMetodo();
		assertNotNull(risposta);
	}


}
