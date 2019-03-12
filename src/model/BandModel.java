package model;
import pojo.Band;
import pojo.Evento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BandModel {
	public void doSave(Band band) throws SQLException;
	public int doUpdate(Band band) throws SQLException;
	public boolean doDelete(String idBand) throws SQLException;
	public Band doRetrieveByKey(String emailBand) throws SQLException;
	public ArrayList<Band> doRetrieveAll(String order) throws SQLException;
	public ArrayList<Evento> doRetrieveAllEventiBand(String emailBand) throws SQLException;
}
