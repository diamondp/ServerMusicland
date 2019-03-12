package pojo;

import java.util.ArrayList;

public class Evento {
	
	

	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", nomeEvento=" + nomeEvento + ", dataEvento=" + dataEvento
				+ ", orarioEvento=" + orarioEvento + ", indirizzo=" + indirizzo + ", nomeLocale=" + nomeLocale
				+ ", generiSuonati=" + generiSuonati + ", descrizione=" + descrizione + ", bandPartecipanti="
				+ bandPartecipanti + ", artistiPartecipanti=" + artistiPartecipanti + ", emailPromotore="
				+ emailPromotore + "]";
	}

	public Evento(){

	    }

	    public int getIdEvento() {
	        return idEvento;
	    }

	    public void setIdEvento(int idEvento) {
	        this.idEvento = idEvento;
	    }

	    public String getNomeEvento() {
	        return nomeEvento;
	    }

	    public void setNomeEvento(String nomeEvento) {
	        this.nomeEvento = nomeEvento;
	    }

	    public String getDataEvento() {
	        return dataEvento;
	    }

	    public void setDataEvento(String dataEvento) {
	        this.dataEvento = dataEvento;
	    }

	    public String getOrarioEvento() {
	        return orarioEvento;
	    }

	    public void setOrarioEvento(String orarioEvento) {
	        this.orarioEvento = orarioEvento;
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

	    public ArrayList<String> getGeneriSuonati() {
	        return generiSuonati;
	    }

	    public void setGeneriSuonati(ArrayList<String> generiSuonati) {
	        this.generiSuonati = generiSuonati;
	    }

	    public String getDescrizione() {
	        return descrizione;
	    }

	    public void setDescrizione(String descrizione) {
	        this.descrizione = descrizione;
	    }

	    public ArrayList<String> getBandPartecipanti() {
	        return bandPartecipanti;
	    }

	    public void setBandPartecipanti(ArrayList<String> bandPartecipanti) {
	        this.bandPartecipanti = bandPartecipanti;
	    }

	    public ArrayList<String> getArtistiPartecipanti() {
	        return artistiPartecipanti;
	    }

	    public void setArtistiPartecipanti(ArrayList<String> artistiPartecipanti) {
	        this.artistiPartecipanti = artistiPartecipanti;
	    }

	    public String getEmailPromotore() {
	        return emailPromotore;
	    }

	    public void setEmailPromotore(String emailPromotore) {
	        this.emailPromotore = emailPromotore;
	    }

	    private int idEvento;
	    private String nomeEvento;
	    private String dataEvento;
	    private String orarioEvento;
	    private Indirizzo indirizzo;
	    private String nomeLocale;
	    private ArrayList<String> generiSuonati;
	    private String descrizione;
	    private ArrayList<String> bandPartecipanti;
	    private ArrayList<String> artistiPartecipanti;
	    private String emailPromotore; //classe Promotore?NO
}
