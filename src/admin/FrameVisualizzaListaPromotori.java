package admin;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.PromotoreModel;
import modelDAO.PromotoreModelDAO;
import pojo.Artista;
import pojo.Promotore;

public class FrameVisualizzaListaPromotori extends JFrame {

	
	public FrameVisualizzaListaPromotori(){
		
		modelPromotore = new PromotoreModelDAO();
		
		JPanel first = this.panelTitoloPromotori();
		
		JScrollPane second = this.panelListaPromotori();
		
		this.add(first, BorderLayout.NORTH);
		this.add(second, BorderLayout.CENTER);
		
		this.createTheFrame();
	}
	
	public void createTheFrame(){
		this.setTitle("LISTA PROMOTORI");
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public JPanel panelTitoloPromotori(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		labelTitoloPromotori = new JLabel("LISTA PROMOTORI");
		panel.add(labelTitoloPromotori);
		return panel;
	}
	
public JScrollPane panelListaPromotori(){
		
		JPanel panel = new JPanel();
		
		ArrayList<Promotore> listaPromotori = this.generaListaPromotori();
		
		panel.setLayout(new GridLayout(listaPromotori.size(), 1));
		JScrollPane scroll = new JScrollPane(panel);
		
		for(Promotore p: listaPromotori){
			
			JPanel panelEmail = new JPanel();
			panelEmail.setLayout(new GridLayout(1, 2));
			
			JPanel panelNome = new JPanel();
			panelNome.setLayout(new GridLayout(1, 2));
			
			JPanel panelCognome = new JPanel();
			panelCognome.setLayout(new GridLayout(1, 2));
			
			JPanel panelEta = new JPanel();
			panelEta.setLayout(new GridLayout(1, 2));
			
			
			
			JPanel panelBiografia = new JPanel();
			panelBiografia.setLayout(new GridLayout(1, 2));
			
			JPanel panelSesso = new JPanel();
			panelSesso.setLayout(new GridLayout(1, 2));
			
			JPanel panelLuogo = new JPanel();
			panelLuogo.setLayout(new GridLayout(1, 2));
			
			
			JPanel panelTelefono = new JPanel();
			panelTelefono.setLayout(new GridLayout(1, 2));
			
			JPanel panelContattoSocial = new JPanel();
			panelContattoSocial.setLayout(new GridLayout(1, 2));
			
			
			
			JPanel panelPromotore = new JPanel();
			panelPromotore.setLayout(new GridLayout(10, 2));
			panelPromotore.setBorder(new TitledBorder(new EtchedBorder(), "Promotore"));
			
			
			
			
			JLabel labelEmail = new JLabel("Email Promotore: ");
			panelEmail.add(labelEmail);
			JTextField textEmail = new JTextField(20);
			textEmail.setText(p.getEmail());
			textEmail.setEditable(false);
			panelEmail.add(textEmail);
			
			JLabel labelNome = new JLabel("Nome Promotore: ");
			panelNome.add(labelNome);
			JTextField textNome = new JTextField(20);
			textNome.setText(p.getNome());
			textNome.setEditable(false);
			panelNome.add(textNome);
			
			JLabel labelCognome = new JLabel("Cognome Promotore: ");
			panelCognome.add(labelCognome);
			JTextField textCognome = new JTextField(20);
			textCognome.setText(p.getCognome());
			textCognome.setEditable(false);
			panelCognome.add(textCognome);
			
			JLabel labelEta = new JLabel("Eta' Promotore: ");
			panelEta.add(labelEta);
			JTextField textEta = new JTextField(20);
			textEta.setText(String.valueOf(p.getAge()));
			textEta.setEditable(false);
			panelEta.add(textEta);
			
			
			JLabel labelBiografia = new JLabel("Biografia Promotore: ");
			panelBiografia.add(labelBiografia);
			JTextField textBiografia = new JTextField(20);
			textBiografia.setText(p.getBiografia());
			textBiografia.setEditable(false);
			panelBiografia.add(textBiografia);
			
			JLabel labelSesso = new JLabel("Sesso Promotore: ");
			panelSesso.add(labelSesso);
			JTextField textSesso = new JTextField(20);
			textSesso.setText(p.getSesso());
			textSesso.setEditable(false);
			panelSesso.add(textSesso);
			
			JLabel labelLuogo = new JLabel("Luogo Promotore: ");
			panelLuogo.add(labelLuogo);
			JTextField textLuogo = new JTextField(20);
			textLuogo.setText(p.getLuogo());
			textLuogo.setEditable(false);
			panelLuogo.add(textLuogo);
			
			
			JLabel labelTelefono = new JLabel("Telefono Promotore: ");
			panelTelefono.add(labelTelefono);
			JTextField textTelefono = new JTextField(20);
			textTelefono.setText(p.getNumeroTelefono());
			textTelefono.setEditable(false);
			panelTelefono.add(textTelefono);
			
			JLabel labelContattoSocial = new JLabel("Contatto Social Promotore: ");
			panelContattoSocial.add(labelContattoSocial);
			JTextField textContattoSocial = new JTextField(20);
			textContattoSocial.setText(p.getLinkSocial());
			textContattoSocial.setEditable(false);
			panelContattoSocial.add(textContattoSocial);
			
			JPanel buttonPanel = new JPanel();
			JButton bottoneElimina = new JButton("ELIMINA");
			buttonPanel.add(bottoneElimina);
			class EliminaUtente implements ActionListener{
				public void actionPerformed(ActionEvent event){
					//int idArtista = a.getIdArtista();
					try {
						modelPromotore.doDelete(textEmail.getText());
						System.out.println("ELIMINATO");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ActionListener eliminaUtente = new EliminaUtente();
			bottoneElimina.addActionListener(eliminaUtente);
			
			panelPromotore.add(panelEmail);
			panelPromotore.add(panelNome);
			panelPromotore.add(panelCognome);
			panelPromotore.add(panelEta);
			
			panelPromotore.add(panelBiografia);
			panelPromotore.add(panelSesso);
			panelPromotore.add(panelLuogo);
			
			panelPromotore.add(panelTelefono);
			panelPromotore.add(panelContattoSocial);
			panelPromotore.add(buttonPanel);
			
			panel.add(panelPromotore);
			
		}
		 
		
		return scroll;
	}


	
	
	
	public ArrayList<Promotore> generaListaPromotori(){
		ArrayList<Promotore> listaPromotori = new ArrayList<Promotore>();
		
		try {
			listaPromotori = modelPromotore.doRetrieveAll("emailPromotore");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPromotori;
	}
	
	
	
	private JLabel labelTitoloPromotori;
	
	private PromotoreModel modelPromotore;
}
