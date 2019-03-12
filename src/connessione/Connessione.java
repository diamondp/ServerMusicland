package connessione;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import admin.FrameVisualizzaListaEventi;
import admin.FrameVisualizzaListaPromotori;
import admin.FrameVisualizzaListaProposteEvento;
import admin.FrameVisualizzaListaArtisti;
import admin.FrameVisualizzaListaBand;


public class Connessione extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel contentPane;
	private static ServerSocket server;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("yuuuuuu");
					Connessione frame = new Connessione();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


		/**
		 * Create the frame.
		 */
	}

	public Connessione() {
		
		
		JPanel first = this.createMenuMusic();
		this.add(first, BorderLayout.NORTH);
		JPanel second = this.createMenuServer();
		this.add(second, BorderLayout.CENTER);
		this.createTheFrame();
		
		
		  th =new Thread(){


			@Override
			public void run(){

				try {
					server = new ServerSocket(5000);
					System.out.println("server in attesa");
						
				}catch (IOException e) {
					
					e.printStackTrace();
				}
				
				while (true ) {
					
					try {
						Socket client = server.accept();
						System.out.println("si è connesso un client " + 
								client.getInetAddress() 	+ ":"+
								+ client.getPort());
			
							
						BufferedReader	reader = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
				
							String line = reader.readLine();
							System.out.println("LINNNNEA "+line);

							JSONObject jsonOb = new JSONObject(line);
							System.out.println(jsonOb.toString());
							ClientListener list = new ClientListener(jsonOb);
							JSONArray risposta = list.trovaMetodo();
							
							System.out.println(risposta.toString());
							OutputStreamWriter writer =  new OutputStreamWriter(client.getOutputStream(), "UTF-8"); 
							writer.write(risposta.toString());
						
							writer.flush();
							
							client.close();
							
						} catch (IOException | JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				
				} 
				


				


}
		};

		th.start();	}
	
	
	

	public void createTheFrame(){
		this.setTitle("MENU");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel createMenuMusic(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		buttonVisualizzaListaArtisti = new JButton("Visualizza Artisti");
		buttonVisualizzaListaBand = new JButton("Visualizza Band");
		buttonVisualizzaListaPromotori = new JButton("Visualizza Promotori");
		buttonVisualizzaProposteEvento = new JButton("Visualizza Proposte Evento");
		buttonVisualizzaEventi = new JButton("Visualizza Eventi");
		
		class MenuArtisti implements ActionListener{
			public void actionPerformed(ActionEvent event){
				frameArtisti = new FrameVisualizzaListaArtisti();
				frameArtisti.setVisible(true);
			}
		}
		apriListaArtisti = new MenuArtisti();
		buttonVisualizzaListaArtisti.addActionListener(apriListaArtisti);
		panel.add(buttonVisualizzaListaArtisti);
		
		class MenuBand implements ActionListener{
			public void actionPerformed(ActionEvent event){
				frameBand = new FrameVisualizzaListaBand();
				frameBand.setVisible(true);
			}
		}
		apriListaBand = new MenuBand();
		buttonVisualizzaListaBand.addActionListener(apriListaBand);
		panel.add(buttonVisualizzaListaBand);
		
		class MenuPromotore implements ActionListener{
			public void actionPerformed(ActionEvent event){
				framePromotori = new FrameVisualizzaListaPromotori();
				framePromotori.setVisible(true);
			}
		}
		apriListaPromotori = new MenuPromotore();
		buttonVisualizzaListaPromotori.addActionListener(apriListaPromotori);
		panel.add(buttonVisualizzaListaPromotori);
		
		class MenuEventi implements ActionListener{
			public void actionPerformed(ActionEvent event){
				frameEventi = new FrameVisualizzaListaEventi();
				frameEventi.setVisible(true);
			}
		}
		apriListaEventi = new MenuEventi();
		buttonVisualizzaEventi.addActionListener(apriListaEventi);
		panel.add(buttonVisualizzaEventi);
		
		class MenuProposte implements ActionListener{
			public void actionPerformed(ActionEvent event){
				frameProposte = new FrameVisualizzaListaProposteEvento();
				frameProposte.setVisible(true);
			}
		}
		apriListaProposteEvento = new MenuProposte();
		buttonVisualizzaProposteEvento.addActionListener(apriListaProposteEvento);
		panel.add(buttonVisualizzaProposteEvento);
		
		return panel;
	}
	
	private JPanel createMenuServer(){
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(1, 2));
		
	
		buttonStopServer = new JButton("Stop Server");
		
		
		
		
		class StopServer implements ActionListener{
			public void actionPerformed(ActionEvent event){
				
				
				try {
					
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
			/*		
				
					try {
						writer.close();
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
				*/
				
				System.out.println("close server...");
			}
		}
		stopServer = new StopServer();
		buttonStopServer.addActionListener(stopServer);
		panel.add(buttonStopServer);
		
		return panel;
	}
	
	
	private JButton buttonVisualizzaListaArtisti;
	private JButton buttonVisualizzaListaBand;
	private JButton buttonVisualizzaListaPromotori;
	//private JButton buttonVisualizzaUtenti;
	private JButton buttonVisualizzaProposteEvento;
	private JButton buttonVisualizzaEventi;
	
	private JButton buttonStartServer;
	private JButton buttonStopServer;
	
	private ActionListener apriListaArtisti;
	private ActionListener apriListaBand;
	private ActionListener apriListaPromotori;
	private ActionListener apriListaEventi;
	private ActionListener apriListaProposteEvento;
	
	private ActionListener startServer;
	private ActionListener stopServer;
	
	private FrameVisualizzaListaArtisti frameArtisti;
	private FrameVisualizzaListaBand frameBand;
	private FrameVisualizzaListaPromotori framePromotori;
	private FrameVisualizzaListaEventi frameEventi;
	private FrameVisualizzaListaProposteEvento frameProposte;
	private Thread th;

	
}

