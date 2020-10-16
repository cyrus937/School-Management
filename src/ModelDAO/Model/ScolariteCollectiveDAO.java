package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class ScolariteCollectiveDAO extends DAO<ScolariteCollective>{
    
    public ScolariteCollectiveDAO() {
        // TODO Auto-generated constructor stub
        try {
            search = this.connect.prepareStatement("SELECT * FROM ScolariteCollective WHERE idScolariteCol = ?");
            insert = this.connect.prepareStatement("INSERT INTO ScolariteCollective (mois,montantPercu,anneeScolaire,idClasse) VALUES (?,?,?,?)");
            update = this.connect.prepareStatement("UPDATE ScolariteCollective SET mois = ?,montantPercu = ?, anneeScolaire = ?, idClasse = ? WHERE idScolariteCol = ?");
            delete = this.connect.prepareStatement("DELETE FROM ScolariteCollective WHERE idScolariteCol = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM ScolariteCollective WHERE anneeScolaire = ?");
            
            research = this.connect.prepareStatement("SELECT idScolariteCol, idClasse, mois, SUM(montantPercu) AS montantPercu FROM ScolariteCollective WHERE anneeScolaire= ? GROUP BY idClasse, mois");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de scolariteCollectiveDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ScolariteCollective find(int id) {
        ScolariteCollective scolariteCollective = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                ClasseDAO classeDAO = new ClasseDAO();
                Classe classe = classeDAO.find(result.getInt(5));
                scolariteCollective = new ScolariteCollective(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), classe);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la scolariteCollective. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return scolariteCollective;
    }

    @Override
    public int create(ScolariteCollective obj) {
        try {
            insert.setString(1, obj.getMois());
            insert.setInt(2, obj.getMontantPercu());
            insert.setString(3, obj.getAnneeScolaire());
            insert.setInt(4, obj.getClasse().getIdClasse());

            insert.executeUpdate();

            ArrayList<ScolariteCollective> list = this.findAll(obj.getAnneeScolaire());
            ScolariteCollective scolariteCollective = list.get(list.size() - 1);

            int id = scolariteCollective.getIdScolariteCol();

            obj.setIdScolariteCol(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une scolariteCollective. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(ScolariteCollective obj) {
        try {
            update.setString(1, obj.getMois());
            update.setInt(2, obj.getMontantPercu());
            update.setString(3, obj.getAnneeScolaire());
            update.setInt(4, obj.getClasse().getIdClasse());
            update.setInt(5, obj.getIdScolariteCol());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une scolariteCollective. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ScolariteCollective obj) {
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
            System.out.println("Erreur lors de la fermeture de scolariteCollectiveDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ScolariteCollective> findAll(String anneeScolaire) {
        ArrayList<ScolariteCollective> listScolariteCollectives = new ArrayList<ScolariteCollective>();
        try {
            affiche.setString(1, anneeScolaire);
            ResultSet result = affiche.executeQuery();
            ClasseDAO classeDAO = new ClasseDAO();
            Classe classe;
            while (result.next()) {
                classe = classeDAO.find(result.getInt(5));
                listScolariteCollectives.add(new ScolariteCollective(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), classe));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des scolariteCollectives. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listScolariteCollectives;
    }
    
    
    public ArrayList<ScolariteCollective> findAll_2(String anneeScolaire) {
        ArrayList<ScolariteCollective> listScolariteCollectives = new ArrayList<ScolariteCollective>();
        try {
            research.setString(1, anneeScolaire);
            ResultSet result = research.executeQuery();
            ClasseDAO classeDAO = new ClasseDAO();
            Classe classe;
            while (result.next()) {
                classe = classeDAO.find(result.getInt(2));
                listScolariteCollectives.add(new ScolariteCollective(result.getInt(1), result.getString(3), result.getInt(4), anneeScolaire, classe));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des scolariteCollectives. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listScolariteCollectives;
    }
    
    /*public static void main(String args[]) {
        ScolariteCollectiveDAO scolariteCollectiveDAO = new ScolariteCollectiveDAO();
        ClasseDAO classeDAO = new ClasseDAO();
        Classe classe  ;
        //int id = scolariteCollectiveDAO.create(new ScolariteCollective("Octobre", 60000,"2020/2021",classe));
        ScolariteCollective scolariteCollective = scolariteCollectiveDAO.find(1);
        classe = scolariteCollective.getClasse();
        scolariteCollective.setMontantPercu(500000);
        boolean test = scolariteCollectiveDAO.update(scolariteCollective);
        System.out.println(test);
    }*/
}
