package model;
import pojo.PropostaEvento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PropostaEventoModel {
	public void doSave(PropostaEvento propostaEvento) throws SQLException; //aggiungere eccezioni
	public int doUpdate(PropostaEvento propostaEvento) throws SQLException;
	public boolean doDelete(int idPropostaEvento) throws SQLException;
	public PropostaEvento doRetrieveByKey(int idEvento) throws SQLException;
	public ArrayList<PropostaEvento> doRetrieveAll(String order) throws SQLException;
}
