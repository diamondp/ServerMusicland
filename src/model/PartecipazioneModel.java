package model;
import pojo.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartecipazioneModel {
	public void doSave(Partecipazione partecipazione, int idEvento, int idArtista) throws SQLException;
    public void doSave(Partecipazione partecipazione, int idEvento, String emailBand) throws SQLException;
    public boolean doDelete(int idEvento, int idArtista) throws SQLException;
    public boolean doDelete(int idEvento, String emailBand) throws SQLException;
    public Partecipazione doRetrieveByKey(int idEvento) throws SQLException;
    public ArrayList<Partecipazione> doretrieveAll(String order) throws SQLException;

}
