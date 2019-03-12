package connessione;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.xdevapi.JsonArray;

class ClientListenerTestCercaPromotori {

	JSONObject richiesta;
	ClientListener c;
	JSONArray risposta;
	
	@BeforeEach
	void setUp() throws Exception {
		// cercap = cerca Promotore ;
	 richiesta = new JSONObject();
		richiesta.put("tipometodo", "cercap");
		c = new ClientListener(richiesta);
	}

	@AfterEach
	void tearDown() throws Exception {
		
		System.out.println(risposta.toString());
	}

	@Test
	void testTrovaMetodo() {
		 risposta = c.trovaMetodo();
		assertNotNull(risposta);
	}

}
