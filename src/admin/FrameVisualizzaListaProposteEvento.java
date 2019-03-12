package admin;

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

import model.PropostaEventoModel;
import modelDAO.PropostaEventoModelDAO;
import pojo.Evento;
import pojo.Indirizzo;
import pojo.PropostaEvento;

public class FrameVisualizzaListaProposteEvento extends JFrame{

	public FrameVisualizzaListaProposteEvento(){

		modelPropostaEvento = new PropostaEventoModelDAO();

		JPanel first = this.panelTitoloProposteEvento();
		JScrollPane second = this.panelListaProposteEvento();

		this.add(first);
		this.add(second);

		this.createTheFrame();
	}

	public void createTheFrame(){
		this.setTitle("LISTA PROPOSTE EVENTO");
		this.setSize(1300, 700);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}


	public JPanel panelTitoloProposteEvento(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		titoloPropostaEvento = new JLabel("LISTA PROPOSTE EVENTO");
		panel.add(titoloPropostaEvento);
		return panel;
	}

	public JScrollPane panelListaProposteEvento(){

		JPanel panel = new JPanel();

		ArrayList<PropostaEvento> listaProposteEvento = this.generaListaProposteEvento();

		panel.setLayout(new GridLayout(listaProposteEvento.size(), 1));
		JScrollPane scroll = new JScrollPane(panel);

		for(PropostaEvento p: listaProposteEvento){

			JPanel panelNomeLocale = new JPanel();
			panelNomeLocale.setLayout(new GridLayout(1, 2));

			JPanel panelNomePropostaEvento = new JPanel();
			panelNomePropostaEvento.setLayout(new GridLayout(1, 2));

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


			JPanel panelPropostaEvento = new JPanel();
			panelPropostaEvento.setLayout(new GridLayout(13, 2));
			panelPropostaEvento.setBorder(new TitledBorder(new EtchedBorder(), "Proposta Evento"));

			JPanel panelIdNascosto = new JPanel();
			JTextField textId = new JTextField(10);
			textId.setText(String.valueOf(p.getIdPropostaEvento()));
			panelIdNascosto.add(textId);
			panelIdNascosto.setVisible(false);


			JLabel labelNomeLocale = new JLabel("Nome Locale: ");
			panelNomeLocale.add(labelNomeLocale);
			JTextField textNomeLocale = new JTextField(20);
			textNomeLocale.setText(p.getNomeLocale());
			textNomeLocale.setEditable(false);
			panelNomeLocale.add(textNomeLocale);

			JLabel labelNome = new JLabel("Nome Proposta Evento: ");
			panelNomePropostaEvento.add(labelNome);
			JTextField textNome = new JTextField(20);
			textNome.setText(p.getNomePropostaEvento());
			textNome.setEditable(false);
			panelNomePropostaEvento.add(textNome);

			JLabel labelData = new JLabel("Data Proposta Evento: ");
			panelData.add(labelData);
			JTextField textData = new JTextField(20);
			textData.setText(p.getDataPropostaEvento());
			textData.setEditable(false);
			panelData.add(textData);

			JLabel labelOra = new JLabel("Ora Prpoosta Evento: ");
			panelOra.add(labelOra);
			JTextField textOra = new JTextField(20);
			textOra.setText(p.getOrarioPropostaEvento());
			textOra.setEditable(false);
			panelOra.add(textOra);

			Indirizzo indirizzoEvento = p.getIndirizzo();
			String viaE = indirizzoEvento.getVia();
			String cittaE = indirizzoEvento.getCitta();
			String provinciaE = indirizzoEvento.getProvincia();
			String regioneE = indirizzoEvento.getRegione();

			JLabel labelVia = new JLabel("Via Proposta Evento: ");
			panelVia.add(labelVia);
			JTextField textVia = new JTextField(20);
			textVia.setText(viaE);
			textVia.setEditable(false);
			panelVia.add(textVia);

			JLabel labelCitta = new JLabel("Citta' Proposta Evento: ");
			panelCitta.add(labelCitta);
			JTextField textCitta = new JTextField(20);
			textCitta.setText(cittaE);
			textCitta.setEditable(false);
			panelCitta.add(textCitta);

			JLabel labelProvincia = new JLabel("Provincia Proposata Evento: ");
			panelProvincia.add(labelProvincia);
			JTextField textProvincia = new JTextField(20);
			textProvincia.setText(provinciaE);
			textProvincia.setEditable(false);
			panelProvincia.add(textProvincia);

			JLabel labelRegione = new JLabel("Regione Proposta Evento: ");
			panelRegione.add(labelRegione);
			JTextField textRegione = new JTextField(20);
			textRegione.setText(regioneE);
			textRegione.setEditable(false);
			panelRegione.add(textRegione);



			JLabel labelGeneriRichiesti = new JLabel("Generi Richiesti Proposta Evento: ");
			panelGeneri.add(labelGeneriRichiesti);
			JTextField textGeneriRichiesti = new JTextField(20);
			ArrayList<String> generiRichiesti = p.getGeneriRichiesti();
			String generi = "";
			for(String g: generiRichiesti){
				generi+=g;
				generi+=", ";
				textGeneriRichiesti.setText(generi);
			}
			textGeneriRichiesti.setEditable(false);
			panelGeneri.add(textGeneriRichiesti);

			JLabel labelDescrizione = new JLabel("Descrizione Proposta Evento: ");
			panelDescrizione.add(labelDescrizione);
			JTextField textDescrizione = new JTextField(20);
			textDescrizione.setText(p.getDescrizione());
			textDescrizione.setEditable(false);
			panelDescrizione.add(textDescrizione);			

			JLabel labelEmailPromotore = new JLabel("Promotore Proposta Evento: ");
			panelEmailPromotore.add(labelEmailPromotore);
			JTextField textEmailPromotore = new JTextField(20);
			textEmailPromotore.setText(p.getEmailPromotore());
			textEmailPromotore.setEditable(false);
			panelEmailPromotore.add(textEmailPromotore);	



			JPanel buttonPanel = new JPanel();
			JButton bottoneElimina = new JButton("ELIMINA");
			buttonPanel.add(bottoneElimina);
			class EliminaUtente implements ActionListener{
				public void actionPerformed(ActionEvent event){

					try {
						modelPropostaEvento.doDelete(p.getIdPropostaEvento());
						System.out.println("ELIMINATO");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			ActionListener eliminaUtente = new EliminaUtente();
			bottoneElimina.addActionListener(eliminaUtente);

			panelPropostaEvento.add(panelNomePropostaEvento);
			panelPropostaEvento.add(panelEmailPromotore);
			panelPropostaEvento.add(panelNomeLocale);
			panelPropostaEvento.add(panelVia);
			panelPropostaEvento.add(panelCitta);
			panelPropostaEvento.add(panelProvincia);
			panelPropostaEvento.add(panelRegione);
			panelPropostaEvento.add(panelData);
			panelPropostaEvento.add(panelOra);
			panelPropostaEvento.add(panelDescrizione);
			panelPropostaEvento.add(panelGeneri);
			panelPropostaEvento.add(buttonPanel);
			panelPropostaEvento.add(panelIdNascosto);

			panel.add(panelPropostaEvento);

		}


		return scroll;
	}



	public ArrayList<PropostaEvento> generaListaProposteEvento(){
		ArrayList<PropostaEvento> listaProposteEvento = new ArrayList<PropostaEvento>();
		try {
			listaProposteEvento = modelPropostaEvento.doRetrieveAll("idPropostaEvento");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return listaProposteEvento;
	}



	private PropostaEventoModel modelPropostaEvento;
	private JLabel titoloPropostaEvento;

}
