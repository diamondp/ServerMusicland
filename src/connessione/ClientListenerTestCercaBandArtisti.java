package connessione;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientListenerTestCercaBandArtisti {

	JSONObject richiesta;
	ClientListener c;
	JSONArray risposta;
	
	@BeforeEach
	void setUp() throws Exception {
		
		// cercaba = cerca Band e Artisti;
		richiesta = new JSONObject();
		richiesta.put("tipometodo", "cercaba");
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
