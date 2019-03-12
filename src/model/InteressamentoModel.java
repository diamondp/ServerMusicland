package model;
import pojo.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InteressamentoModel {
	public void doSave(Interessamento interessamento, int idPropostaEvento, int idArtista) throws SQLException; 
    public void doSave(Interessamento interessamento, int idPropostaEvento, String emailBand) throws SQLException;
    public boolean doDelete(int idPropostaEvento, int idArtista) throws SQLException;
    public boolean doDelete(int idPropostaEvento, String emailBand) throws SQLException;
    public Interessamento doRetrieveByKey(int idPropostaEvento) throws SQLException;
    public ArrayList<Interessamento> doRetrieveAll(String order) throws SQLException;

}
