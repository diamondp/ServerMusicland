package connessione;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import model.ArtistaModel;
import model.BandModel;
import model.EventoModel;
import model.PromotoreModel;
import model.PropostaEventoModel;
import modelDAO.ArtistaModelDAO;
import modelDAO.BandModelDAO;
import modelDAO.EventoModelDAO;
import modelDAO.PromotoreModelDAO;
import modelDAO.PropostaEventoModelDAO;
import pojo.Artista;
import pojo.Band;
import pojo.Evento;
import pojo.Promotore;
import pojo.PropostaEvento;

public class ClientListener {
	
	
	

	
	public ClientListener(JSONObject line) {
		
		richiesta=line;
		
		
		}


	
	public JSONArray trovaMetodo() {
	
		String a = (String) richiesta.get("tipometodo");
		System.out.println(a.toString());
		
		
		switch ( a ) {
		case LOGIN:
			System.out.println("SONO IN LOGIN CLIENTLISTENER");
			String mail= (String) richiesta.get("email");
			String passw= (String) richiesta.get("password");
			
			tipoProfilo = new JSONObject(); 
			tipoProfilo.put("tipoprofilo", "artista");
			
			try {
				modelartista = new ArtistaModelDAO();
				 artista = modelartista.doRetrieveByKey(mail);
				 eventiArtista = modelartista.doRetrieveAllEventiArtista(mail);
				 
				 if(artista.isBand()) artista.setEmail(null);
				 
				 	if(artista.getEmail() == null ) 
					 {
					 
					 tipoProfilo.put("tipoprofilo", "band");
					 modelband = new BandModelDAO();
					 band = modelband.doRetrieveByKey(mail);
					 eventiBand = modelband.doRetrieveAllEventiBand(mail);
					 
					 }else {
						 
						 // è stato trovato l'artista con quella mail, ora procediamo nel controllare
						 // se la password è uguale.
						if(artista.getPassword().equals(passw)) {
							
							a_risposta = new JSONArray();
							a_risposta.put(tipoProfilo);
							
							
							//JSONObject eventiA = modelartista.doRetrieveAllEventiArtista(mail);
							JSONObject artista1= new JSONObject(artista);
							JSONArray eventiA = new JSONArray(eventiArtista);
						
							a_risposta.put(artista1);
							a_risposta.put(eventiA);
							
							System.out.println(a_risposta.toString());
							System.out.println(a_risposta.get(2));
							//System.out.println(a_risposta.get(3).toString());
							return a_risposta;
							
							
							//creare json e mandarlo indietro alla classe che ti ha chiamato.(connessione)
							
							
							
																}else {
																	
																	// password sbagliata.
																	a_risposta = new JSONArray();
																	tipoProfilo.put("tipoprofilo", "passwordErrata");
																	a_risposta.put(tipoProfilo);
																	System.out.println(a_risposta.toString());
																	return a_risposta;
																}
												
						 
					 		}
				 
				 	if(band.getEmail()==null) {
				 		


				 		tipoProfilo.put("tipoprofilo", "promotore");
						 modelpromotore = new PromotoreModelDAO();
						  promotore = modelpromotore.doRetrieveByKey(mail);
						  eventiPromotore = modelpromotore.doRetrieveEventiPromotore(mail);
						  propostePromotore = modelpromotore.doRetrieveProposteEventoPromotore(mail);
				 		
				 		
				 		
				 	}else {
				 		
				 		
				 	// è stata trovata la band con quella mail, ora procediamo nel controllare
						 // se la password è uguale.
						if(band.getPassword().equals(passw)) {
							
							
							//creare json e mandarlo indietro alla classe che ti ha chiamato.(connessione)
							
							a_risposta = new JSONArray();
							
							a_risposta.put(tipoProfilo);
							
							
							JSONObject band1= new JSONObject(band);
							JSONArray eventiB= new JSONArray(eventiBand);
							a_risposta.put(band1);
							a_risposta.put(eventiB);
							
							System.out.println(a_risposta.toString());
							return a_risposta;
							
																}else {
																	
																	// password sbagliata.
																	a_risposta = new JSONArray();
																	tipoProfilo.put("tipoprofilo", "passwordErrata");
																	a_risposta.put(tipoProfilo);
																	System.out.println(a_risposta.toString());
																	return a_risposta;
																}
				 		
				 		
				 	} if(promotore.getEmail()==null) {
				 		
				 		a_risposta = new JSONArray();
				 		tipoProfilo.put("tipoprofilo", "datiNonTrovati");
						a_risposta.put(tipoProfilo);
						System.out.println(a_risposta.toString());
						return a_risposta;
				 		//creare json rispostaNonTrovata e mandare la risposta alla classe connessione;
				 		
				 	}else {
				 		
				 		
				 			if(promotore.getPassword().equals(passw)) {
							
							
				 				a_risposta = new JSONArray();
								a_risposta.put(tipoProfilo);
								JSONObject promotore1= new JSONObject(promotore);
								JSONArray eventiP = new JSONArray(eventiPromotore);
								JSONArray peventi = new JSONArray(propostePromotore);
								
								a_risposta.put(promotore1);
								a_risposta.put(eventiP);
								a_risposta.put(peventi);
								System.out.println(a_risposta.toString());
								return a_risposta;
							
							
																}else {
																	
																	// password sbagliata.
																	a_risposta = new JSONArray();		
																	tipoProfilo.put("tipoprofilo", "passwordErrata");
																	a_risposta.put(tipoProfilo);
																	System.out.println(a_risposta.toString());
																	return a_risposta;
																}
				 		
				 		
				 	}
					
					
					
					
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			break;

		case LOGOUT:
			
			a_risposta = new JSONArray();
			JSONObject rs = new JSONObject();
			rs.put("tipometodo", "logout");
			a_risposta.put(rs);
			return a_risposta;
			
			
		case MODIFICA_PROFILO:
			
			
			break;
			
			
		case CERCA_B_A:
			System.out.println("Sono in cerca band e artisti , richiesta :"+a.toString() );
			ArrayList<Artista> listaArtisti = new ArrayList<Artista>();
			ArtistaModel modelArtista = new ArtistaModelDAO();
			ArrayList<Band> listaBand = new ArrayList<Band>();
			BandModel modelBand = new BandModelDAO();
			
			try {
				listaArtisti= modelArtista.doRetrieveAll("idArtista");
				listaBand=modelBand.doRetrieveAll("emailBand");
				a_risposta = new JSONArray();
				JSONObject rw = new JSONObject();
				rw.put("tipometodo", "cercaba");
				a_risposta.put(rw);
				JSONArray listaArtisti1= new JSONArray(listaArtisti);
				a_risposta.put(listaArtisti1);
				JSONArray listaBand1= new JSONArray(listaBand);
				a_risposta.put(listaBand1);
				return a_risposta;
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
			
			
			
		case CERCA_P:
			
			ArrayList<Promotore> listaPromotore= new ArrayList<Promotore>();
			PromotoreModel modelpromotore = new PromotoreModelDAO();
			
			try {
				listaPromotore= modelpromotore.doRetrieveAll("emailPromotore");
				a_risposta = new JSONArray();
				JSONObject rp = new JSONObject();
				rp.put("tipometodo", "listaPromotore");
				a_risposta.put(rp);
				JSONArray listaPromotore1= new JSONArray(listaPromotore);
				a_risposta.put(listaPromotore1);
				return a_risposta;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			
		case CERCA_EVENTO:
			EventoModel modelEvento = new EventoModelDAO();
			ArrayList<Evento> listaEventi = new ArrayList<Evento>();
			try {
				listaEventi = modelEvento.doRetrieveAll("idEvento");
				a_risposta = new JSONArray();
				JSONObject re = new JSONObject();
				re.put("tipometodo", "listaEventi");
				a_risposta.put(re);
				JSONArray listaEventi1 = new JSONArray(listaEventi);
				a_risposta.put(listaEventi1);
				return a_risposta;
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			break;
			
			
		case CERCA_PEVENTO:
			
			ArrayList<PropostaEvento> listaProposteEvento = new ArrayList<PropostaEvento>();
			PropostaEventoModel modelPropostaEvento = new PropostaEventoModelDAO();
			try {
				listaProposteEvento = modelPropostaEvento.doRetrieveAll("idPropostaEvento");
				a_risposta = new JSONArray();
				JSONObject rpe = new JSONObject();
				rpe.put("tipometodo", "listaProposteEvento");
				a_risposta.put(rpe);
				JSONArray listaProposteEvento1 = new JSONArray(listaProposteEvento);
				a_risposta.put(listaProposteEvento1);
				return a_risposta;			
			} catch (SQLException e) {

				e.printStackTrace();
			}
			break;
			
			
		case PUBBLICA_E:
			
			
			break;
			
			
		case PUBBLICA_PE:
			
			
			break;
			
			
			
		case MODIFICA_EVENTO:
			
			
			break;
			
			
		case MODIFICA_PEVENTO:
			
			
			
			break;
			
			
			
			
		case ELIMINA_EVENTO:
			
			break;
			
			
			
		case ELIMINA_PEVENTO:
			
			
		break;
		
		
		case REG_ARTISTA:
			
			break;
			
		case REG_BAND:
			
			break;
			
			
		case REG_PROMOTORE:
			
			
			break;
			
			
		case LISTA_Band:
			
			
			

			ArrayList<Band> listaNband= new ArrayList<Band>();
			BandModel modelNband = new BandModelDAO();
			
			try {
				listaNband= modelNband.doRetrieveAll("");
				a_risposta = new JSONArray();
				JSONObject rp = new JSONObject();
				
				String email = "";
				String nome = "";
				
				
				
				for(int i = 0 ; i<listaNband.size(); i++) {
				email=listaNband.get(i).getEmail();
				nome=listaNband.get(i).getNomeBand();
				
				
					
				rp.put("NBand",i);
				rp.put("nome", nome);
				rp.put("email", email);
				
				a_risposta.put(rp);
				
				}
				
				return a_risposta;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			
			
			
			
			
		case LISTA_ARTISTI:

			
			

			ArrayList<Artista> listaNartisti= new ArrayList<Artista>();
			ArtistaModel modelNartisti = new ArtistaModelDAO();
			
			try {
				listaNartisti= modelNartisti.doRetrieveAll("");
				a_risposta = new JSONArray();
				JSONObject rp = new JSONObject();
				
				String email = "";
				String nome = "";
				
				
				
				for(int i = 0 ; i<listaNartisti.size(); i++) {
				email=listaNartisti.get(i).getEmail();
				nome= listaNartisti.get(i).getNickname();
				
					
				rp.put("NArtista",i);
				rp.put("nome", nome);
				
				rp.put("email", email);
				
				a_risposta.put(rp);
				
				}
				
				return a_risposta;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
			
			
			
		case LISTA_PROMOTORI:
			
			
			

			ArrayList<Promotore> listaNpromo= new ArrayList<Promotore>();
			PromotoreModel modelNpromotore = new PromotoreModelDAO();
			
			try {
				listaNpromo= modelNpromotore.doRetrieveAll("emailPromotore");
				a_risposta = new JSONArray();
				JSONObject rp = new JSONObject();
				
				String email = "";
				String nome = "";
				String cognome="";
				
				
				for(int i = 0 ; i<listaNpromo.size(); i++) {
				email=listaNpromo.get(i).getEmail();
				nome= listaNpromo.get(i).getNome();
				cognome=listaNpromo.get(i).getCognome();
					
				rp.put("NPromotore",i);
				rp.put("nome", nome);
				rp.put("cognome", cognome);
				rp.put("email", email);
				
				a_risposta.put(rp);
				
				}
				
				return a_risposta;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			break;
			
		default:
			
			break;
		}
		
		
		
		
		
		
		
		
		
		
		return null;
		
		
		
		
	}
	
	


private JSONObject richiesta;
private pojo.Artista artista;
private ArrayList<Evento> eventiArtista;
private ArrayList<Evento> eventiBand;
private ArrayList<Evento> eventiPromotore;

private ArrayList<PropostaEvento> propostePromotore;

private pojo.Band band;
private PromotoreModel modelpromotore;
private ArtistaModel modelartista;
private  BandModel modelband;
private PropostaEventoModel modelPropostaEvento;
private EventoModel modelEvento;
private pojo.Promotore promotore;
private JSONArray a_risposta;
private JSONObject tipoProfilo;






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