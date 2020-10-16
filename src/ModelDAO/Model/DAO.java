package ModelDAO.Model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public abstract class DAO<T> {

    public Connection connect = ConnexionBD.getInstance();

    protected PreparedStatement search;
    protected PreparedStatement insert;
    protected PreparedStatement update;
    protected PreparedStatement delete;
    protected PreparedStatement research;
    protected PreparedStatement affiche;
    protected PreparedStatement affiche1;
    protected PreparedStatement affiche2;
    protected PreparedStatement last;

    /*
	 * Permet de recuperer un objet via son ID
	 * @param id
	 * @return
     */
    public abstract T find(int id);

    /*
	 * Permet de creer un enregistrement dans la base de donnees
	 * par rapport a un objet
	 * @param obj
	 * @return la cle de l'objet cree
     */
    public abstract int create(T obj);

    /*
	 * Permet de mettre a jour les donnees d'une entree dans la base
	 * @param obj
     */
    public abstract boolean update(T obj);

    /*
	 * Permet la suppression d'une entree dans la base
	 * @param obj
     */
    public abstract boolean delete(T obj);

    /*
	 * Permet de fermer les requetes preparees
     */
    public abstract void close();

    /*
	 * permet d'obtenir le type de gestionnaire
	 * @param login, password
	 * @return String*/
    //public abstract String researchTypeGest(String login, String password);

    /*
	 * Permet de retourner une liste d'objet
	 * @return un arraylist
     */
    @SuppressWarnings("rawtypes")
	public abstract ArrayList findAll(String anneeScolaire);

    /*
	 * Permet de retourner une liste des Caissiers
	 * @return un arraylist
     */
}
