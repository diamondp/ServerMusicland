package pojo;

public class Partecipazione {
	public Partecipazione(){

	}

	
	public int getIdPartecipazione(){
		return this.idPartecipazione;
	}
	
	public void setIdPartecipazione(int idPartecipazione){
		this.idPartecipazione = idPartecipazione;
	}


	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public int getIdArtistaPartecipante() {
		return idArtistaPartecipante;
	}

	public void setIdArtistaPartecipante(int idArtistaPartecipante) {
		this.idArtistaPartecipante = idArtistaPartecipante;
	}

	public String getIdBandPartecipante() {
		return emailBandPartecipante;
	}

	public void setIdBandPartecipante(String idBand) {
		this.emailBandPartecipante = emailBandPartecipante;
	}

	private int idPartecipazione;
	private int idEvento;
	private int idArtistaPartecipante;
	private String emailBandPartecipante; //email band
}
