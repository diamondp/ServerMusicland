package pojo;

import java.util.ArrayList;

public class Artista extends Utente {
	
    public Artista(){
    	
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
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

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public ArrayList<String> getGeneriMusicali() {
        return generiSuonati;
    }

    public void setGeneriMusicali(ArrayList<String> generiMusicali) {
        this.generiSuonati = generiMusicali;
    }

    public ArrayList<String> getStrumentiSuonati() {
        return strumentiSuonati;
    }

    public void setStrumentiSuonati(ArrayList<String> strumentiSuonati) {
        this.strumentiSuonati = strumentiSuonati;
    }

    public ArrayList<Evento> getMieiEventi() {
        return mieiEventi;
    }

    public void setMieiEventi(ArrayList<Evento> mieiEventi) {
        this.mieiEventi = mieiEventi;
    }

    @Override
	public String toString() {
		return "Artista [idArtista=" + idArtista + ", nome=" + nome + ", cognome=" + cognome + ", nickname=" + nickname
				+ ", sesso=" + sesso + ", age=" + age + ", generiSuonati=" + generiSuonati + ", strumentiSuonati="
				+ strumentiSuonati + ", mieiEventi=" + mieiEventi + ", isBand=" + isBand + "]";
	}

	public boolean isBand() {
        return isBand;
    }

    public void setBand(boolean band) {
        isBand = band;
    }

    private int idArtista;
    private String nome;
    private String cognome;
    private String nickname;
    private String sesso;
    private int age;
    private ArrayList<String> generiSuonati; 
    private ArrayList<String> strumentiSuonati; 
    private ArrayList<Evento> mieiEventi;
    private boolean isBand;

}
