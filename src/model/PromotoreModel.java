package model;
import pojo.Promotore;
import pojo.Evento;
import pojo.PropostaEvento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PromotoreModel {
	public void doSave(Promotore promotore) throws SQLException; 
	public int doUpdate(Promotore promotore) throws SQLException;
	public boolean doDelete(String emailPromotore) throws SQLException;
	public Promotore doRetrieveByKey(String emailPromotore) throws SQLException;
	public ArrayList<Promotore> doRetrieveAll(String order) throws SQLException;
	public ArrayList<Evento> doRetrieveEventiPromotore(String emailPromotore) throws SQLException;
	public ArrayList<PropostaEvento> doRetrieveProposteEventoPromotore(String emailPromotore) throws SQLException;
	
}
