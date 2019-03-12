package pojo;

import java.util.ArrayList;

public class Promotore extends Utente {
	 public Promotore(){

	    }


	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getCognome() {
	        return cognome;
	    }

	    @Override
		public String toString() {
			return "Promotore [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", age=" + age
					+ ", mieiEventi=" + mieiEventi + ", mieProposte=" + mieProposte + "]";
		}


		public void setCognome(String cognome) {
	        this.cognome = cognome;
	    }

	    public String getSesso() {
	        return sesso;
	    }

	    public void setSesso(String sesso) {
	        this.sesso = sesso;
	    }

	    public int getAge() {
	        return age;
	    }

	    public void setAge(int age) {
	        this.age = age;
	    }

	    public ArrayList<Evento> getMieiEventi() {
	        return mieiEventi;
	    }

	    public void setMieiEventi(ArrayList<Evento> mieiEventi) {
	        this.mieiEventi = mieiEventi;
	    }

	    public ArrayList<PropostaEvento> getMieProposte() {
	        return mieProposte;
	    }

	    public void setMieProposte(ArrayList<PropostaEvento> mieProposte) {
	        this.mieProposte = mieProposte;
	    }

	    public void setArrayEventiPromotore(ArrayList<Evento> eventi) {
	    	this.mieiEventi=eventi;
	    	
	    	
	    }
	    private String nome;
	    private String cognome;
	    private String sesso;
	    private int age;
	    private ArrayList<Evento> mieiEventi;
	    private ArrayList<PropostaEvento> mieProposte;
}
