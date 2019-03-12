package pojo;

public class Indirizzo {
	 public Indirizzo(String via, String citta, String provincia, String regione) {
	        this.via = via;
	        this.citta = citta;
	        this.provincia = provincia;
	        this.regione = regione;
	    }

	    @Override
	public String toString() {
		return "Indirizzo via: " + via + ", citta: " + citta + ", provincia : " + provincia + ", regione :" + regione + "";
	}

		public String getVia() {
	        return via;
	    }

	    public void setVia(String via) {
	        this.via = via;
	    }

	    public String getCitta() {
	        return citta;
	    }

	    public void setCitta(String citta) {
	        this.citta = citta;
	    }

	    public String getProvincia() {
	        return provincia;
	    }

	    public void setProvincia(String provincia) {
	        this.provincia = provincia;
	    }

	    public String getRegione() {
	        return regione;
	    }

	    public void setRegione(String regione) {
	        this.regione = regione;
	    }

	    private String via;
	    private String citta;
	    private String provincia;
	    private String regione;
}
