package admin;



import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.ArtistaModel;
import model.EventoModel;
import model.PromotoreModel;
import model.PropostaEventoModel;
import modelDAO.ArtistaModelDAO;
import modelDAO.EventoModelDAO;
import modelDAO.PromotoreModelDAO;
import modelDAO.PropostaEventoModelDAO;
import pojo.Artista;
import pojo.Evento;
import pojo.Promotore;
import pojo.PropostaEvento;



public class AdminIntro {
	public static void main(String[] args) {
	
		

		/*
		Artista a= new Artista();
		a.setEmail("ccc");
		a.setCognome("aaaa");
		a.setNickname("bbbbb");
		a.setNome("aaaa");
		JSONObject tipometodo=new JSONObject();
		tipometodo.put("tipometodo", "inserimento");
		JSONArray a_risposta = new JSONArray();
		a_risposta.put(tipometodo);
		a_risposta.put(a);
		System.out.println(a.toString());
		tipometodo=(JSONObject) a_risposta.get(0);
		System.out.println(tipometodo.get("tipometodo").toString());
		*/
		
		
		//Promotore p= new Promotore();
		//PromotoreModel pa = new PromotoreModelDAO();
		/*
		try {
			
			p= pa.doRetrieveByKey("promotore1@gmail.com");
			//p.setArrayEventiPromotore(pa.doRetrieveEventiPromotore("promotore@gmail.com"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(p.toString() );
		//System.out.println(p.getMieiEventi() +" " + p.getMieiEventi().size() );
		*/
		//try {
			
			//EventoModel d = new EventoModelDAO();
			//System.out.println( (pa.doRetrieveEventiPromotore("promotore@gmail.com")).toString() );
			//System.out.println(d.doRetrieveAll(""));
			//System.out.println(pa.doRetrieveEventiPromotore("promotore1@gmail.com"));
			//System.out.println(pa.doRetrieveProposteEventoPromotore("promotore1@gmail.com"));
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		
	
		/*
		try {
			ArrayList<Evento> e = pa.doRetrieveEventiPromotore("promotore1@gmail.com");
			ArrayList<PropostaEvento> pe= pa.doRetrieveProposteEventoPromotore("promotore1@gmail.com");
			System.out.println(e.toString());
			System.out.println(pe.toString());
			
			
			Artista ne= new Artista();
			ArtistaModel a = new ArtistaModelDAO();
			
			ne=a.doRetrieveByKey("artista1@gmail.com");
			e=a.doRetrieveAllEventiArtista("artista1@gmail.com");
			System.out.println(ne.toString());
			System.out.println(e.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		*/
		
	/*	
		
		ArrayList<Evento> eventi = new ArrayList<Evento>();
		EventoModel e = new EventoModelDAO();
		try {
			eventi= e.doRetrieveAll("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (Evento ee : eventi ) {
			System.out.println(ee.toString() + "\n");
			System.out.println(eventi.size());
		}
		
		
		
	
		ArrayList<PropostaEvento> peventi = new ArrayList<PropostaEvento>();
		PropostaEventoModel pm = new PropostaEventoModelDAO();
		try {
			peventi= pm.doRetrieveAll("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (PropostaEvento ee : peventi ) {
			System.out.println(ee.toString() + "\n");
			System.out.println(peventi.size());
		}
		
		
		*/
	
		
		
		JFrame frame = new FrameAccessoAdmin();
		frame.setVisible(true);
		
	}	
	
}
