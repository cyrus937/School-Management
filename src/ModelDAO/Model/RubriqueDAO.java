package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class RubriqueDAO extends DAO<Rubrique> {

    public RubriqueDAO() {
        // TODO Auto-generated constructor stub
        try {
        	research = this.connect.prepareStatement("SELECT * FROM Rubrique WHERE nom = ? AND type = ?");
            search = this.connect.prepareStatement("SELECT * FROM Rubrique WHERE idRubrique = ?");
            insert = this.connect.prepareStatement("INSERT INTO Rubrique (nom,type) VALUES (?,?)");
            update = this.connect.prepareStatement("UPDATE Rubrique SET nom = ?, type = ? WHERE idRubrique = ?");
            delete = this.connect.prepareStatement("DELETE FROM Rubrique WHERE idRubrique = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM Rubrique");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de rubriqueDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public Rubrique find(int id) {
        Rubrique rubrique = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                rubrique = new Rubrique(result.getInt(1), result.getString(2), result.getString(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la rubrique. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return rubrique;
    }

    public Rubrique find(String rubrique, String type) {
        Rubrique r = null;
        try {
            research.setString(1, rubrique);
            research.setString(2, type);
            ResultSet result = research.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                r = new Rubrique(result.getInt(1), result.getString(2), result.getString(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche  de l'identifiant de la rubrique. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return r;
    }
    
    @Override
    public int create(Rubrique obj) {
        try {
            insert.setString(1, obj.getNom());
            insert.setString(2, obj.getType());

            insert.executeUpdate();

            ArrayList<Rubrique> list = this.findAll();
            Rubrique rubrique = list.get(list.size() - 1);

            int id = rubrique.getIdRubrique();

            obj.setIdRubrique(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une rubrique. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(Rubrique obj) {
        try {
            update.setString(1, obj.getNom());
            update.setString(2, obj.getType());
            update.setInt(3, obj.getIdRubrique());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une rubrique. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Rubrique obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        try {
            insert.close();
            search.close();
            update.close();
            affiche.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de rubriqueDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Rubrique> findAll(String anneeScolaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Rubrique> findAll() {
        ArrayList<Rubrique> listRubrique = new ArrayList<Rubrique>();
        try {
            ResultSet result = affiche.executeQuery();
            while (result.next()) {
                listRubrique.add(new Rubrique(result.getInt(1), result.getString(2), result.getString(3)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des donnees. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listRubrique;
    }
    
    /*public static void main(String args[]) {
        RubriqueDAO rubriqueDAO = new RubriqueDAO();
        //int id = rubriqueDAO.create(new Rubrique("Salaire", "passif"));
        Rubrique rubrique = rubriqueDAO.find(1);
        rubrique.setNom("Rame de format");
        boolean test = rubriqueDAO.update(rubrique);
        System.out.println(test);
    }*/
}
