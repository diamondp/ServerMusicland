package pojo;

public class Interessamento {
	public Interessamento(){

    }
	
	public int getIdInteressamento(){
		return this.idInteressamento;
	}
	
	public void setIdInteressamento(int idInteressamento){
		this.idInteressamento = idInteressamento;
	}

    public int getIdPropostaEvento() {
        return idPropostaEvento;
    }

    public void setIdPropostaEvento(int idPropostaEvento) {
        this.idPropostaEvento = idPropostaEvento;
    }

    public int getIdArtista() {
        return idArtistaInteressato;
    }

    public void setIdArtista(int idArtista) {
        this.idArtistaInteressato = idArtista;
    }

    public String getIdBand() {
        return emailBandInteressata;
    }

    public void setIdBand(String idBand) {
        this.emailBandInteressata = idBand;
    }

    private int idInteressamento;
    private int idPropostaEvento;
    private int idArtistaInteressato;
    private String emailBandInteressata; //email band
}
