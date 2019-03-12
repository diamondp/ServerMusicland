package connessione;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientListenerTestListaEventi {


	JSONObject richiesta;
	ClientListener c;
	JSONArray risposta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// cercaevento = lista eventi pubblicati ;
		richiesta = new JSONObject();
		richiesta.put("tipometodo", "cercaevento");
		c = new ClientListener(richiesta);
	}

	@AfterEach
	void tearDown() throws Exception {

		System.out.println(risposta.toString());
		
	}

	@Test
	void testTrovaMetodo() {
		//fail("Not yet implemented");
		
		
		 risposta = c.trovaMetodo();
		assertNotNull(risposta);
	}

}
