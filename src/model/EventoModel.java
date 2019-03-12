package model;
import pojo.Evento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EventoModel {
	public void doSave(Evento evento) throws SQLException; //aggiungere eccezioni
    public int doUpdate(Evento evento) throws SQLException;
    public boolean doDelete(int idEvento) throws SQLException;
    public Evento doRetrieveByKey(int idEvento) throws SQLException;
    public ArrayList<Evento> doRetrieveAll(String order) throws SQLException;
}
