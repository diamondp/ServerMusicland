package pojo;

import java.util.ArrayList;

public class PropostaEvento {
	 @Override
	public String toString() {
		return "PropostaEvento [idPropostaEvento=" + idPropostaEvento + ", nomePropostaEvento=" + nomePropostaEvento
				+ ", dataPropostaEvento=" + dataPropostaEvento + ", orarioPropostaEvento=" + orarioPropostaEvento
				+ ", indirizzo=" + indirizzo + ", nomeLocale=" + nomeLocale + ", generiRichiesti=" + generiRichiesti
				+ ", descrizione=" + descrizione + ", emailPromotore=" + emailPromotore + ", artistiInteressati="
				+ artistiInteressati + ", bandInteressate=" + bandInteressate + "]";
	}

	public PropostaEvento(){

	    }

	    public int getIdPropostaEvento() {
	        return idPropostaEvento;
	    }

	    public void setIdPropostaEvento(int idPropostaEvento) {
	        this.idPropostaEvento = idPropostaEvento;
	    }

	    public String getNomePropostaEvento() {
	        return nomePropostaEvento;
	    }

	    public void setNomePropostaEvento(String nomePropostaEvento) {
	        this.nomePropostaEvento = nomePropostaEvento;
	    }

	    public String getDataPropostaEvento() {
	        return dataPropostaEvento;
	    }

	    public void setDataPropostaEvento(String dataEvento) {
	        this.dataPropostaEvento = dataEvento;
	    }

	    public String getOrarioPropostaEvento() {
	        return orarioPropostaEvento;
	    }

	    public void setOrarioPropostaEvento(String orarioEvento) {
	        this.orarioPropostaEvento = orarioEvento;
	    }

	    public Indirizzo getIndirizzo() {
	        return indirizzo;
	    }

	    public void setIndirizzo(Indirizzo indirizzo) {
	        this.indirizzo = indirizzo;
	    }

	    public String getNomeLocale() {
	        return nomeLocale;
	    }

	    public void setNomeLocale(String nomeLocale) {
	        this.nomeLocale = nomeLocale;
	    }

	    public ArrayList<String> getGeneriRichiesti() {
	        return generiRichiesti;
	    }

	    public void setGeneriRichiesti(ArrayList<String> generiRichiesti) {
	        this.generiRichiesti = generiRichiesti;
	    }

	    public String getDescrizione() {
	        return descrizione;
	    }

	    public void setDescrizione(String descrizione) {
	        this.descrizione = descrizione;
	    }

	    public String getEmailPromotore() {
	        return emailPromotore;
	    }

	    public void setEmailPromotore(String emailPromotore) {
	        this.emailPromotore = emailPromotore;
	    }
	    
	    public ArrayList<String> getListaArtistiInteressati(){
	    	return this.artistiInteressati;
	    }
	    
	    public void setListaArtistiInteressati(ArrayList<String> lista){
	    	this.artistiInteressati = lista;
	    }
	    
	    public void setListaBandInteressate(ArrayList<String> lista){
	    	this.bandInteressate = lista;
	    }
	    
	    public ArrayList<String> getListaBandInteressate(){
	    	return  this.bandInteressate;
	    }

	    private int idPropostaEvento;
	    private String nomePropostaEvento;
	    private String dataPropostaEvento;
	    private String orarioPropostaEvento;
	    private Indirizzo indirizzo;
	    private String nomeLocale;
	    private ArrayList<String> generiRichiesti;
	    private String descrizione;
	    private String emailPromotore; //classe Promotore?no
	    private ArrayList<String> artistiInteressati;
	    private ArrayList<String> bandInteressate;
	    /*
	     * DOVEREBBE AVERE DUE ARRAYLIST DI BAND E ARTISTI INTERESSATI
	     */
}
