package admin;

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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.EventoModel;
import modelDAO.EventoModelDAO;
import pojo.Artista;
import pojo.Evento;
import pojo.Indirizzo;

public class FrameVisualizzaListaEventi extends JFrame{

	public FrameVisualizzaListaEventi(){

		modelEvento = new EventoModelDAO();

		JPanel first = this.panelTitoloEventi();
		JScrollPane second = this.panelListaEventi();

		this.add(first);
		this.add(second);

		this.createTheFrame();
	}

	public void createTheFrame(){
		this.setTitle("LISTA EVENTI");
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}


	public JPanel panelTitoloEventi(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		labelTitoloEventi = new JLabel("LISTA EVENTI");
		panel.add(labelTitoloEventi);
		return panel;
	}

	public JScrollPane panelListaEventi(){

		JPanel panel = new JPanel();

		ArrayList<Evento> listaEventi = this.generaListaEventi();

		panel.setLayout(new GridLayout(listaEventi.size(), 1));
		JScrollPane scroll = new JScrollPane(panel);

		for(Evento e: listaEventi){

			JPanel panelNomeLocale = new JPanel();
			panelNomeLocale.setLayout(new GridLayout(1, 2));

			JPanel panelNomeEvento = new JPanel();
			panelNomeEvento.setLayout(new GridLayout(1, 2));

			JPanel panelData = new JPanel();
			panelData.setLayout(new GridLayout(1, 2));

			JPanel panelOra = new JPanel();
			panelOra.setLayout(new GridLayout(1, 2));

			JPanel panelVia = new JPanel();
			panelVia.setLayout(new GridLayout(1, 2));

			JPanel panelCitta = new JPanel();
			panelCitta.setLayout(new GridLayout(1, 2));

			JPanel panelProvincia = new JPanel();
			panelProvincia.setLayout(new GridLayout(1, 2));

			JPanel panelRegione = new JPanel();
			panelRegione.setLayout(new GridLayout(1, 2));

			JPanel panelGeneri = new JPanel();
			panelGeneri.setLayout(new GridLayout(1, 2));

			JPanel panelDescrizione = new JPanel();
			panelDescrizione.setLayout(new GridLayout(1, 2));

			JPanel panelEmailPromotore = new JPanel();
			panelEmailPromotore.setLayout(new GridLayout(1, 2));

			
			JPanel panelEvento = new JPanel();
			panelEvento.setLayout(new GridLayout(13, 2));
			panelEvento.setBorder(new TitledBorder(new EtchedBorder(), "Evento"));

			JPanel panelIdNascosto = new JPanel();
			JTextField textId = new JTextField(10);
			textId.setText(String.valueOf(e.getIdEvento()));
			panelIdNascosto.add(textId);
			panelIdNascosto.setVisible(false);


			JLabel labelNomeLocale = new JLabel("Nome Locale: ");
			panelNomeLocale.add(labelNomeLocale);
			JTextField textNomeLocale = new JTextField(20);
			textNomeLocale.setText(e.getNomeLocale());
			textNomeLocale.setEditable(false);
			panelNomeLocale.add(textNomeLocale);

			JLabel labelNome = new JLabel("Nome Evento: ");
			panelNomeEvento.add(labelNome);
			JTextField textNome = new JTextField(20);
			textNome.setText(e.getNomeEvento());
			textNome.setEditable(false);
			panelNomeEvento.add(textNome);

			JLabel labelData = new JLabel("Data Evento: ");
			panelData.add(labelData);
			JTextField textData = new JTextField(20);
			textData.setText(e.getDataEvento());
			textData.setEditable(false);
			panelData.add(textData);

			JLabel labelOra = new JLabel("Ora Evento: ");
			panelOra.add(labelOra);
			JTextField textOra = new JTextField(20);
			textOra.setText(e.getOrarioEvento());
			textOra.setEditable(false);
			panelOra.add(textOra);

			Indirizzo indirizzoEvento = e.getIndirizzo();
			String viaE = indirizzoEvento.getVia();
			String cittaE = indirizzoEvento.getCitta();
			String provinciaE = indirizzoEvento.getProvincia();
			String regioneE = indirizzoEvento.getRegione();
			
			JLabel labelVia = new JLabel("Via Evento: ");
			panelVia.add(labelVia);
			JTextField textVia = new JTextField(20);
			textVia.setText(viaE);
			textVia.setEditable(false);
			panelVia.add(textVia);
			
			JLabel labelCitta = new JLabel("Citta' Evento: ");
			panelCitta.add(labelCitta);
			JTextField textCitta = new JTextField(20);
			textCitta.setText(cittaE);
			textCitta.setEditable(false);
			panelCitta.add(textCitta);
			
			JLabel labelProvincia = new JLabel("Provincia Evento: ");
			panelProvincia.add(labelProvincia);
			JTextField textProvincia = new JTextField(20);
			textProvincia.setText(provinciaE);
			textProvincia.setEditable(false);
			panelProvincia.add(textProvincia);
			
			JLabel labelRegione = new JLabel("Regione Evento: ");
			panelRegione.add(labelRegione);
			JTextField textRegione = new JTextField(20);
			textRegione.setText(regioneE);
			textRegione.setEditable(false);
			panelRegione.add(textRegione);

			

			JLabel labelGeneriSuonati = new JLabel("Generi Suonati Evento: ");
			panelGeneri.add(labelGeneriSuonati);
			JTextField textGeneriSuonati = new JTextField(20);
			ArrayList<String> generiSuonati = e.getGeneriSuonati();
			String generi = "";
			for(String g: generiSuonati){
				generi+=g;
				generi+=", ";
				textGeneriSuonati.setText(generi);
			}
			textGeneriSuonati.setEditable(false);
			panelGeneri.add(textGeneriSuonati);
			
			JLabel labelDescrizione = new JLabel("Descrizione Evento: ");
			panelDescrizione.add(labelDescrizione);
			JTextField textDescrizione = new JTextField(20);
			textDescrizione.setText(e.getDescrizione());
			textDescrizione.setEditable(false);
			panelDescrizione.add(textDescrizione);			

			JLabel labelEmailPromotore = new JLabel("Promotore Evento: ");
			panelEmailPromotore.add(labelEmailPromotore);
			JTextField textEmailPromotore = new JTextField(20);
			textEmailPromotore.setText(e.getEmailPromotore());
			textEmailPromotore.setEditable(false);
			panelEmailPromotore.add(textEmailPromotore);	
			


			JPanel buttonPanel = new JPanel();
			JButton bottoneElimina = new JButton("ELIMINA");
			buttonPanel.add(bottoneElimina);
			class EliminaUtente implements ActionListener{
				public void actionPerformed(ActionEvent event){
					
					try {
						System.out.println(e.getIdEvento());
						modelEvento.doDelete(e.getIdEvento());
						System.out.println("ELIMINATO");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ActionListener eliminaUtente = new EliminaUtente();
			bottoneElimina.addActionListener(eliminaUtente);

			panelEvento.add(panelNomeEvento);
			panelEvento.add(panelEmailPromotore);
			panelEvento.add(panelNomeLocale);
			panelEvento.add(panelVia);
			panelEvento.add(panelCitta);
			panelEvento.add(panelProvincia);
			panelEvento.add(panelRegione);
			panelEvento.add(panelData);
			panelEvento.add(panelOra);
			panelEvento.add(panelDescrizione);
			panelEvento.add(panelGeneri);
			panelEvento.add(buttonPanel);
			panelEvento.add(panelIdNascosto);

			panel.add(panelEvento);

		}


		return scroll;
	}



	public ArrayList<Evento> generaListaEventi(){
		ArrayList<Evento> listaEventi = new ArrayList<Evento>();
		try {
			listaEventi = modelEvento.doRetrieveAll("idEvento");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaEventi;
	}

	private EventoModel modelEvento;
	private JLabel labelTitoloEventi;

}
