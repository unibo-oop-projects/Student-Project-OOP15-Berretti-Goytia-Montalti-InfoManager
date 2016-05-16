package com.infoMng.controller;

import java.sql.SQLException;
import java.util.List;

/**
 * classe che si occupa delle interrogazioni al database
 * permette di eseguire ricerche o caricare l'intera tabella
 * @author mattiaberretti
 *
 */
public interface MBQuery {

	/***
	 * crea una query di ricerca sulla base di una tabella
	 * @param nomeTabella
	 * nome della tabella da cui eseguire le ricerche
	 * @return
	 * 	un oggetto usato per la ricerca dei dati all'interno di una tabella
	 */
	public static MBQuery queryDaTabella(String nomeTabella){
		return new Query(nomeTabella);
	}
	
	/***
	 * crea una query di ricerca sulla base di una tabella
	 * @param nomeTabella
	 * nome della tabella da cui eseguire le ricerche
	 * @param condizione
	 * condizione da inserire immediatamente all'interno della query 
	 * 	N.B.
	 * 		è scritta in linguaggio sql
	 * @return
	 * 	un oggetto usato per la ricerca dei dati all'interno di una tabella
	 */
	public static MBQuery queryDaTabella(String nomeTabella, String condizione){
		return new Query(nomeTabella, condizione);
	}
	
	/**
	 * ricerca gli oggetti che hanno un valore uguale ad un elemento prestabilito
	 * @param key
	 * nome della colonna per il confronto
	 * @param value
	 * valore per il confronto
	 */
	void whereEqualTo(String key, Object value);

	/***
	 * ricerca gli oggetti che hanno un valore diverso ad un elemento prestabilito
	 * @param key
	 * nome della colonna per il confronto
	 * @param value
	 * valore per il confronto
	 */
	void whereNotEqualTo(String key, Object value);

	/**
	 * ricerca i valori che hanno il valore scelto non NULL
	 * @param key
	 * colonna da controllare
	 */
	void whereKeyExists(String key);


	/**
	 * ricerca i valori che hanno il valore scelto a NULL
	 * @param key
	 * colonna da controllare
	 */
	void whereKeyNotExists(String key);

	/**
	 * ottiene l'elenco degli oggetti della tabella corrispondenti ai criteri
	 * @return
	 * una lista di MBOggetto contenente tutti i dati e i metodi per il salvataggio ed eliminazione
	 * @throws SQLException
	 * in caso si verifichi un'errore nella ricerca 
	 * N.B.
	 * 	Può derivare da condizioni errate
	 */
	List<Oggetto> find() throws SQLException;

	/**
	 * ottiene il primo elemento della tabella corrispondente all'elenco
	 * @return
	 * una lista di MBOggetto contenente tutti i dati e i metodi per il salvataggio ed eliminazione
	 * @throws SQLException
	 * in caso si verifichi un'errore nella ricerca 
	 * N.B.
	 * 	Può derivare da condizioni errate
	 */
	MBOggetto getFirst() throws SQLException;

}