
package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class EleveDAO extends DAO<Eleve> {

    public EleveDAO() {
    	
        try {
            search = this.connect.prepareStatement("SELECT * FROM Eleve WHERE matricule = ? AND anneScolaire = ?");
            insert = this.connect.prepareStatement("INSERT INTO Eleve (matricule,nom,prenom,dateInscription,montantPaye,montantRestant,anneScolaire,statut,idClasse) VALUES (?,?,?,?,?,?,?,?,?)");
            update = this.connect.prepareStatement("UPDATE Eleve SET nom = ?, prenom = ?, dateInscription = ?,montantPaye = ?, montantRestant = ?, statut = ?, idClasse = ? WHERE matricule = ? AND anneScolaire = ?");
            delete = this.connect.prepareStatement("DELETE FROM Eleve WHERE matricule = ? AND anneScolaire = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM Eleve WHERE anneScolaire = ? AND statut = 1");
            affiche1 = this.connect.prepareStatement("SELECT * FROM Eleve WHERE anneScolaire = ? AND statut = 1 AND montantRestant = 0");
            affiche2 = this.connect.prepareStatement("SELECT * FROM Eleve WHERE anneScolaire = ? AND statut = 1 AND montantRestant > 0");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de eleveDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }

    }

    @Override
    public Eleve find(int id) {
        return null;
        
    }

    public Eleve find(String matricule, String annee) {
        Eleve eleve = null;
        try {
            search.setString(1, matricule);
            search.setString(2, annee);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                ClasseDAO classeDAO = new ClasseDAO();
                Classe classe = classeDAO.find(result.getInt(9));
                eleve = new Eleve(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5),
                        result.getInt(6), result.getString(7), result.getInt(8), classe);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de l'eleve. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return eleve;
    }
    
    @Override
    public int create(Eleve obj) {
        try {
            insert.setString(1, obj.getMatricule());
            insert.setString(2, obj.getNom());
            insert.setString(3, obj.getPrenom());
            insert.setString(4, obj.getDateInscription());
            insert.setInt(5, obj.getMontantPaye());
            insert.setInt(6, obj.getMontantRestant());
            insert.setString(7, obj.getAnneeScolaire());
            insert.setInt(8, obj.getStatut());
            insert.setInt(9, obj.getClasse().getIdClasse());

            insert.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'un eleve. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(Eleve obj) {
        try {
            update.setString(1, obj.getNom());
            update.setString(2, obj.getPrenom());
            update.setString(3, obj.getDateInscription());
            update.setInt(4, obj.getMontantPaye());
            update.setInt(5, obj.getMontantRestant());
            update.setInt(6, obj.getStatut());
            update.setInt(7, obj.getClasse().getIdClasse());
            update.setString(8, obj.getMatricule());
            update.setString(9, obj.getAnneeScolaire());
            update.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'un eleve. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Eleve obj) {
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
            System.out.println("Erreur lors de la fermeture de eleveDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Eleve> findAll(String anneeScolaire) {
        ArrayList<Eleve> listEleves = new ArrayList<Eleve>();
        try {
            Classe classe;
            ClasseDAO classeDAO = new ClasseDAO();
            affiche.setString(1, anneeScolaire);
            ResultSet result = affiche.executeQuery();
            while (result.next()) {
                classe = classeDAO.find(result.getInt(9));
                listEleves.add(new Eleve(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getInt(5), result.getInt(6), result.getString(7), result.getInt(8), classe));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listEleves;
    }
    
    public ArrayList<Eleve> findAll1(String anneeScolaire) {
		ArrayList<Eleve> listEleves = new ArrayList<Eleve>();
        try {
            Classe classe;
            ClasseDAO classeDAO = new ClasseDAO();
            affiche1.setString(1, anneeScolaire);
            ResultSet result = affiche1.executeQuery();
            while (result.next()) {
                classe = classeDAO.find(result.getInt(9));
                listEleves.add(new Eleve(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getInt(5), result.getInt(6), result.getString(7), result.getInt(8), classe));
            }

            result.close();
            this.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listEleves;
	}

	public ArrayList<Eleve> findAll2(String anneeScolaire) {
		ArrayList<Eleve> listEleves = new ArrayList<Eleve>();
        try {
            Classe classe;
            ClasseDAO classeDAO = new ClasseDAO();
            affiche2.setString(1, anneeScolaire);
            ResultSet result = affiche2.executeQuery();
            while (result.next()) {
                classe = classeDAO.find(result.getInt(9));
                listEleves.add(new Eleve(result.getString(1), result.getString(2), result.getString(3), result.getString(4),
                        result.getInt(5), result.getInt(6), result.getString(7), result.getInt(8), classe));
            }

            result.close();
            this.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listEleves;
	}
    /*public static void main(String args[]) {
        EleveDAO eleveDAO = new EleveDAO();
        ClasseDAO classeDAO = new ClasseDAO();
        Classe classe = classeDAO.find(8);
        int id = eleveDAO.create(new Eleve("CM23","KENFACK","Cyrille", "26/08/2020", 6, 4, "2020/2021", 1, classe));
        System.out.println(id);
    }*/
}
