package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;

public interface modelUsersI {
	public static Boolean isLogged(){
		return UserTmp.CurrentUser.isLogged();
	}
	
	public static modelUsersI getUtenteCorrente() throws NullPointerException {
		if(UserTmp.CurrentUser.isLogged()){
			return UserTmp.CurrentUser.getUtente();
		}
		else{
			throw new NullPointerException("Utente non loggato");
		}
	}
	
	Integer getID();

	String getName();

	String getLastName();

	String getUsername();

	String getPassword();

	String getMail();

	/***
	 * creazione di un nuovo utente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @return
	 * true se � stato creato l'utente altrimenti false
	 */

	boolean changePassword(String nome, String cognome, String mail, String username, String password,
			String newPassword);

	/***
	 * metodo per eliminare il propri account utente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @return
	 * true se � stata cancellata l'account altrimenti false
	 */
	boolean deleteUser(String nome, String cognome, String mail, String username, String password);
	
	/***
	 * metodo che ritorna un elenco di tutti gli utento
	 * @return
	 * una lista contenenti tutti gli utenti esistenti
	 */
	public static List<modelUsersI> usersList(){
		DataBaseSearch query = DataBaseSearch.queryDaTabella("Utenti");
		try {
			return query.find().stream()
					.map(e -> new modelUsers(e))
					.collect(Collectors.toList());
		} catch (SQLException e) {
			return new ArrayList<modelUsersI>();
		}
	}
	/***
	 * creazione di un nuovo utente
	 * @param nome
	 * @param cognome
	 * @param mail
	 * @param username
	 * @param password
	 * @return
	 * true se � stato creato l'utente altrimenti false
	 */
	public static boolean newUser(String nome, String cognome, String mail, String username, String password){
		modelUsers nuovo = new modelUsers(TableRow.oggettoDaTabella("Utenti"));
		nuovo.setName(nome);
		nuovo.setLastName(cognome);
		nuovo.setMail(mail);
		nuovo.setUsername(username);
		nuovo.setPassword(password);
		if(nuovo.oggetto.salva()){
			UserTmp.CurrentUser.setUtente(nuovo);
			return true;
		}
		return false;
	}
	/***
	 * controllo dell'accesso dell'utente
	 * @param username
	 * @param password
	 * @return
	 * true se l'untente esiste, altrimenti False
	 */
	public static boolean usersLogin(String username, String password){
		Optional<modelUsersI> tmp = modelUsersI.usersList().stream()
				.filter(e -> e.getUsername().equals(username))
				.filter(e -> e.getPassword().equals(password))
				.findFirst();
		
		if(tmp.isPresent()){
			UserTmp.CurrentUser.setUtente(tmp.get());
			return true;
		}
		else{
			return false;
		}
	}

}