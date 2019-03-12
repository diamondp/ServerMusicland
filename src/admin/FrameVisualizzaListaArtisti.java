package admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.ArtistaModel;
import model.BandModel;
import model.PromotoreModel;
import modelDAO.ArtistaModelDAO;
import modelDAO.BandModelDAO;
import modelDAO.PromotoreModelDAO;
import pojo.*;

public class FrameVisualizzaListaArtisti extends JFrame{


	public FrameVisualizzaListaArtisti(){

		modelArtista = new ArtistaModelDAO();
		
		JPanel first = this.panelTitoloArtisti();
		
		JScrollPane second = this.panelListaArtisti();
		
		this.add(first, BorderLayout.NORTH);
		this.add(second, BorderLayout.CENTER);
		
		this.createTheFrame();
	}

	public void createTheFrame(){
		this.setTitle("LISTA ARTISTI");
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}


	public JPanel panelTitoloArtisti(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		labelTitoloArtisti = new JLabel("LISTA ARTISTI");
		panel.add(labelTitoloArtisti);
		return panel;
	}
	
	public JScrollPane panelListaArtisti(){
		
		JPanel panel = new JPanel();
		
		ArrayList<Artista> listaArtisti = this.generaListaArtisti();
		
		panel.setLayout(new GridLayout(listaArtisti.size(), 1));
		JScrollPane scroll = new JScrollPane(panel);
		
		for(Artista a: listaArtisti){
			
			JPanel panelEmail = new JPanel();
			panelEmail.setLayout(new GridLayout(1, 2));
			
			JPanel panelNome = new JPanel();
			panelNome.setLayout(new GridLayout(1, 2));
			
			JPanel panelCognome = new JPanel();
			panelCognome.setLayout(new GridLayout(1, 2));
			
			JPanel panelEta = new JPanel();
			panelEta.setLayout(new GridLayout(1, 2));
			
			JPanel panelNickname = new JPanel();
			panelNickname.setLayout(new GridLayout(1, 2));
			
			JPanel panelBiografia = new JPanel();
			panelBiografia.setLayout(new GridLayout(1, 2));
			
			JPanel panelSesso = new JPanel();
			panelSesso.setLayout(new GridLayout(1, 2));
			
			JPanel panelLuogo = new JPanel();
			panelLuogo.setLayout(new GridLayout(1, 2));
			
			JPanel panelGeneri = new JPanel();
			panelGeneri.setLayout(new GridLayout(1, 2));
			
			JPanel panelStrumenti = new JPanel();
			panelStrumenti.setLayout(new GridLayout(1, 2));
			
			JPanel panelTelefono = new JPanel();
			panelTelefono.setLayout(new GridLayout(1, 2));
			
			JPanel panelContattoSocial = new JPanel();
			panelContattoSocial.setLayout(new GridLayout(1, 2));
			
			JPanel panelArtista = new JPanel();
			panelArtista.setLayout(new GridLayout(14, 2));
			panelArtista.setBorder(new TitledBorder(new EtchedBorder(), "Artista"));
			
			JPanel panelIdNascosto = new JPanel();
			JTextField textId = new JTextField(10);
			textId.setText(String.valueOf(a.getIdArtista()));
			//int idArtista = a.getIdArtista();
			panelIdNascosto.add(textId);
			panelIdNascosto.setVisible(false);
			
			
			JLabel labelEmail = new JLabel("Email Artista: ");
			panelEmail.add(labelEmail);
			JTextField textEmail = new JTextField(20);
			textEmail.setText(a.getEmail());
			textEmail.setEditable(false);
			panelEmail.add(textEmail);
			
			JLabel labelNome = new JLabel("Nome Artista: ");
			panelNome.add(labelNome);
			JTextField textNome = new JTextField(20);
			textNome.setText(a.getNome());
			textNome.setEditable(false);
			panelNome.add(textNome);
			
			JLabel labelCognome = new JLabel("Cognome Artista: ");
			panelCognome.add(labelCognome);
			JTextField textCognome = new JTextField(20);
			textCognome.setText(a.getCognome());
			textCognome.setEditable(false);
			panelCognome.add(textCognome);
			
			JLabel labelEta = new JLabel("Eta' Artista: ");
			panelEta.add(labelEta);
			JTextField textEta = new JTextField(20);
			textEta.setText(String.valueOf(a.getAge()));
			textEta.setEditable(false);
			panelEta.add(textEta);
			
			JLabel labelNickname = new JLabel("Nickname Artista: ");
			panelNickname.add(labelNickname);
			JTextField textNickname = new JTextField(20);
			textNickname.setText(a.getNickname());
			textNickname.setEditable(false);
			panelNickname.add(textNickname);
			
			JLabel labelBiografia = new JLabel("Biografia Artista: ");
			panelBiografia.add(labelBiografia);
			JTextField textBiografia = new JTextField(20);
			textBiografia.setText(a.getBiografia());
			textBiografia.setEditable(false);
			panelBiografia.add(textBiografia);
			
			JLabel labelSesso = new JLabel("Sesso Artista: ");
			panelSesso.add(labelSesso);
			JTextField textSesso = new JTextField(20);
			textSesso.setText(a.getSesso());
			textSesso.setEditable(false);
			panelSesso.add(textSesso);
			
			JLabel labelLuogo = new JLabel("Luogo Artista: ");
			panelLuogo.add(labelLuogo);
			JTextField textLuogo = new JTextField(20);
			textLuogo.setText(a.getLuogo());
			textLuogo.setEditable(false);
			panelLuogo.add(textLuogo);
			
			JLabel labelGeneriSuonati = new JLabel("Generi Suonati Artista: ");
			panelGeneri.add(labelGeneriSuonati);
			JTextField textGeneriSuonati = new JTextField(20);
			ArrayList<String> generiSuonati = a.getGeneriMusicali();
			String generi = "";
			for(String g: generiSuonati){
				generi+=g;
				generi+=", ";
				textGeneriSuonati.setText(generi);
			}
			textGeneriSuonati.setEditable(false);
			panelGeneri.add(textGeneriSuonati);
			
			JLabel labelStrumentiSuonati = new JLabel("Strumenti Suonati Artista: ");
			panelStrumenti.add(labelStrumentiSuonati);
			JTextField textStrumentiSuonati = new JTextField(20);
			ArrayList<String> strumentiSuonati = a.getStrumentiSuonati();
			String strumenti = "";
			for(String s: strumentiSuonati){
				strumenti+=s;
				strumenti+=", ";
				textStrumentiSuonati.setText(strumenti);
			}
			textStrumentiSuonati.setEditable(false);
			panelStrumenti.add(textStrumentiSuonati);
			
			
			JLabel labelTelefono = new JLabel("Telefono Artista: ");
			panelTelefono.add(labelTelefono);
			JTextField textTelefono = new JTextField(20);
			textTelefono.setText(a.getNumeroTelefono());
			textTelefono.setEditable(false);
			panelTelefono.add(textTelefono);
			
			JLabel labelContattoSocial = new JLabel("Contatto Social Artista: ");
			panelContattoSocial.add(labelContattoSocial);
			JTextField textContattoSocial = new JTextField(20);
			textContattoSocial.setText(a.getLinkSocial());
			textContattoSocial.setEditable(false);
			panelContattoSocial.add(textContattoSocial);
			
			JPanel buttonPanel = new JPanel();
			JButton bottoneElimina = new JButton("ELIMINA");
			buttonPanel.add(bottoneElimina);
			class EliminaUtente implements ActionListener{
				public void actionPerformed(ActionEvent event){
					//int idArtista = a.getIdArtista();
					try {
						//int idArt = Integer.parseInt(textId.getText());
						modelArtista.doDelete(textEmail.getText());
						System.out.println("ELIMINATO");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ActionListener eliminaUtente = new EliminaUtente();
			bottoneElimina.addActionListener(eliminaUtente);
			
			panelArtista.add(panelEmail);
			panelArtista.add(panelNome);
			panelArtista.add(panelCognome);
			panelArtista.add(panelEta);
			panelArtista.add(panelNickname);
			panelArtista.add(panelBiografia);
			panelArtista.add(panelSesso);
			panelArtista.add(panelLuogo);
			panelArtista.add(panelGeneri);
			panelArtista.add(panelStrumenti);
			panelArtista.add(panelTelefono);
			panelArtista.add(panelContattoSocial);
			panelArtista.add(buttonPanel);
			panelArtista.add(panelIdNascosto);
			
			panel.add(panelArtista);
			
		}
		 
		
		return scroll;
	}



	public ArrayList<Artista> generaListaArtisti(){

		ArrayList<Artista> listaArtisti = new ArrayList<Artista>();

		try {
			listaArtisti = modelArtista.doRetrieveAll("idArtista");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaArtisti;

	}
	
	
	private ArtistaModel modelArtista;
	
	

	private JLabel labelTitoloArtisti;
	
	



}
