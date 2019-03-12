package model;
import pojo.Artista;
import pojo.Evento;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ArtistaModel {
	public void doSave(Artista artista) throws SQLException; 
    public int doUpdate(Artista artista) throws SQLException;
    public void doDelete(String emailArtista) throws SQLException;
    public Artista doRetrieveByKey(String emailArtista) throws SQLException;
    public ArrayList<Artista> doRetrieveAll(String order) throws SQLException;
    //metodo da fare in PartecipazioneModel in correlazione con Artista
    public ArrayList<Evento> doRetrieveAllEventiArtista(String emailArtista) throws SQLException;
}
