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

import model.BandModel;
import modelDAO.BandModelDAO;
import pojo.Artista;
import pojo.Band;

public class FrameVisualizzaListaBand extends JFrame{

	public FrameVisualizzaListaBand(){
		modelBand = new BandModelDAO();

		JPanel first = this.panelTitoloBand();
		JScrollPane second = this.panelListaBand();

		this.add(first, BorderLayout.NORTH);
		this.add(second, BorderLayout.CENTER);

		this.createTheFrame();
	}

	public void createTheFrame(){
		this.setTitle("LISTA BAND");
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public JPanel panelTitoloBand(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		labelTitoloBand = new JLabel("LISTA BAND");
		panel.add(labelTitoloBand);
		return panel;
	}

	public JScrollPane panelListaBand(){
		JPanel panel = new JPanel();

		ArrayList<Band> listaBand = this.generaListaBand();

		panel.setLayout(new GridLayout(listaBand.size(), 1));
		JScrollPane scroll = new JScrollPane(panel);

		for(Band b: listaBand){

			JPanel panelEmail = new JPanel();
			panelEmail.setLayout(new GridLayout(1, 2));

			JPanel panelNomeBand = new JPanel();
			panelNomeBand.setLayout(new GridLayout(1, 2));

			
			JPanel panelBiografia = new JPanel();
			panelBiografia.setLayout(new GridLayout(1, 2));

			
			JPanel panelLuogo = new JPanel();
			panelLuogo.setLayout(new GridLayout(1, 2));

			JPanel panelGeneri = new JPanel();
			panelGeneri.setLayout(new GridLayout(1, 2));

			

			JPanel panelTelefono = new JPanel();
			panelTelefono.setLayout(new GridLayout(1, 2));

			JPanel panelContattoSocial = new JPanel();
			panelContattoSocial.setLayout(new GridLayout(1, 2));

			JPanel panelBand = new JPanel();
			panelBand.setLayout(new GridLayout(9, 2));
			panelBand.setBorder(new TitledBorder(new EtchedBorder(), "Band"));


			JLabel labelEmail = new JLabel("Email Band: ");
			panelEmail.add(labelEmail);
			JTextField textEmail = new JTextField(20);
			textEmail.setText(b.getEmail());
			textEmail.setEditable(false);
			panelEmail.add(textEmail);

			JLabel labelNomeBand = new JLabel("Nome Band: ");
			panelNomeBand.add(labelNomeBand);
			JTextField textNomeBand = new JTextField(20);
			textNomeBand.setText(b.getNomeBand());
			textNomeBand.setEditable(false);
			panelNomeBand.add(textNomeBand);

			
			

			JLabel labelBiografia = new JLabel("Biografia Band: ");
			panelBiografia.add(labelBiografia);
			JTextField textBiografia = new JTextField(20);
			textBiografia.setText(b.getBiografia());
			textBiografia.setEditable(false);
			panelBiografia.add(textBiografia);

			

			JLabel labelLuogo = new JLabel("Luogo Band: ");
			panelLuogo.add(labelLuogo);
			JTextField textLuogo = new JTextField(20);
			textLuogo.setText(b.getLuogo());
			textLuogo.setEditable(false);
			panelLuogo.add(textLuogo);

			JLabel labelGeneriSuonati = new JLabel("Generi Suonati Band: ");
			panelGeneri.add(labelGeneriSuonati);
			JTextField textGeneriSuonati = new JTextField(20);
			ArrayList<String> generiSuonati = b.getGeneriMusicali();
			String generi = "";
			for(String g: generiSuonati){
				generi+=g;
				generi+=", ";
				textGeneriSuonati.setText(generi);
			}
			textGeneriSuonati.setEditable(false);
			panelGeneri.add(textGeneriSuonati);

			

			JLabel labelTelefono = new JLabel("Telefono Band: ");
			panelTelefono.add(labelTelefono);
			JTextField textTelefono = new JTextField(20);
			textTelefono.setText(b.getNumeroTelefono());
			textTelefono.setEditable(false);
			panelTelefono.add(textTelefono);

			JLabel labelContattoSocial = new JLabel("Contatto Social Band: ");
			panelContattoSocial.add(labelContattoSocial);
			JTextField textContattoSocial = new JTextField(20);
			textContattoSocial.setText(b.getLinkSocial());
			textContattoSocial.setEditable(false);
			panelContattoSocial.add(textContattoSocial);

			JPanel buttonPanel = new JPanel();
			JButton bottoneElimina = new JButton("ELIMINA");
			buttonPanel.add(bottoneElimina);
			class EliminaUtente implements ActionListener{
				public void actionPerformed(ActionEvent event){
					//
					try {
						modelBand.doDelete(textEmail.getText());
						System.out.println("ELIMINATO");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ActionListener eliminaUtente = new EliminaUtente();
			bottoneElimina.addActionListener(eliminaUtente);

			panelBand.add(panelEmail);
			panelBand.add(panelNomeBand);
			
			panelBand.add(panelBiografia);
			
			panelBand.add(panelLuogo);
			panelBand.add(panelGeneri);
			
			panelBand.add(panelTelefono);
			panelBand.add(panelContattoSocial);
			panelBand.add(buttonPanel);
			//panelBand.add(panelIdNascosto);

			panel.add(panelBand);

		}


		return scroll;
	}

	
	public ArrayList<Band> generaListaBand(){
		ArrayList<Band> listaBand = new ArrayList<Band>();
		
		try {
			listaBand = modelBand.doRetrieveAll("emailBand");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return listaBand;
	}


	private BandModel modelBand;


	private JLabel labelTitoloBand;

}
