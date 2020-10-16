package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class ScolariteIndividuelDAO extends DAO<ScolariteIndividuel> {
    
    public ScolariteIndividuelDAO() {
        // TODO Auto-generated constructor stub
        try {
            search = this.connect.prepareStatement("SELECT * FROM ScolariteIndividuel WHERE idScolarite_Indiv = ?");
            insert = this.connect.prepareStatement("INSERT INTO ScolariteIndividuel (date,montantPaye,montantRestant,anneeScolaire,statut,matricule) VALUES (?,?,?,?,?,?)");
            update = this.connect.prepareStatement("UPDATE ScolariteIndividuel SET date = ?, montantPaye = ?, montantRestant = ?,anneeScolaire = ?, statut = ?, matricule = ? WHERE idScolarite_Indiv = ?");
            delete = this.connect.prepareStatement("DELETE FROM ScolariteIndividuel WHERE idScolarite_Indiv = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM ScolariteIndividuel WHERE anneeScolaire = ? AND statut = 1 ORDER BY idScolarite_Indiv DESC");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de scolariteIndividuelDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }

    }

    @Override
    public ScolariteIndividuel find(int id) {
        ScolariteIndividuel scolariteIndividuel = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                EleveDAO eleveDAO = new EleveDAO();
                Eleve eleve = eleveDAO.find(result.getString(7), result.getString(5));
                scolariteIndividuel = new ScolariteIndividuel(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getString(5),
                        result.getInt(6), eleve);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la scolariteIndividuel. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return scolariteIndividuel;
    }

    @Override
    public int create(ScolariteIndividuel obj) {
        try {
            insert.setString(1, obj.getDate());
            insert.setInt(2, obj.getMontantPaye());
            insert.setInt(3, obj.getMontantRestant());
            insert.setString(4, obj.getAnneeScolaire());
            insert.setInt(5, obj.getStatut());
            insert.setString(6, obj.getEleve().getMatricule());

            insert.executeUpdate();

            ArrayList<ScolariteIndividuel> list = this.findAll(obj.getAnneeScolaire());
            ScolariteIndividuel scolariteIndividuel = list.get(list.size() - 1);

            int id = scolariteIndividuel.getIdScolariteIndiv();

            obj.setIdScolariteIndiv(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une scolariteIndividuel. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(ScolariteIndividuel obj) {
        try {
            update.setString(1, obj.getDate());
            update.setInt(2, obj.getMontantPaye());
            update.setInt(3, obj.getMontantRestant());
            update.setString(4, obj.getAnneeScolaire());
            update.setInt(5, obj.getStatut());
            update.setString(6, obj.getEleve().getMatricule());
            update.setInt(7, obj.getIdScolariteIndiv());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une scolariteIndividuel. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ScolariteIndividuel obj) {
        try {
            obj.setStatut(0);
            update.setString(1, obj.getDate());
            update.setInt(2, obj.getMontantPaye());
            update.setInt(3, obj.getMontantRestant());
            update.setString(4, obj.getAnneeScolaire());
            update.setInt(5, obj.getStatut());
            update.setString(6, obj.getEleve().getMatricule());
            update.setInt(7, obj.getIdScolariteIndiv());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une scolariteIndividuel. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void close() {
        try {
            insert.close();
            search.close();
            update.close();
            affiche.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de scolariteIndividuelDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ScolariteIndividuel> findAll(String anneeScolaire) {
        ArrayList<ScolariteIndividuel> listScolariteIndividuels = new ArrayList<ScolariteIndividuel>();
        try {
            affiche.setString(1, anneeScolaire);
            ResultSet result = affiche.executeQuery();
            EleveDAO eleveDAO = new EleveDAO();
            Eleve eleve;
            while (result.next()) {
                eleve = eleveDAO.find(result.getString(7), result.getString(5));
                listScolariteIndividuels.add(new ScolariteIndividuel(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4),
                        result.getString(5), result.getInt(6), eleve));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des scolariteIndividuels. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listScolariteIndividuels;
    }
    
    /*public static void main(String args[]) {
        ScolariteIndividuelDAO scolariteIndividuelDAO = new ScolariteIndividuelDAO();
        EleveDAO eleveDAO = new EleveDAO();
        Eleve eleve ;
        //int id = scolariteIndividuelDAO.create(new ScolariteIndividuel("28/08/2020", 60000,"2020/2021",eleve));
        ScolariteIndividuel scolariteIndividuel = scolariteIndividuelDAO.find(2);
        eleve = scolariteIndividuel.getEleve();
        scolariteIndividuel.setMontantPaye(500000);
        boolean test = scolariteIndividuelDAO.update(scolariteIndividuel);
        System.out.println(test);
    }*/
}
