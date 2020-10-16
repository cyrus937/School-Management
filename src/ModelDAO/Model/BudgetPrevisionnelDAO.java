package ModelDAO.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class BudgetPrevisionnelDAO extends DAO<BudgetPrevisionnel> {
    
	PreparedStatement all;
	
    public BudgetPrevisionnelDAO() {
        // TODO Auto-generated constructor stub
        try {
        	research = this.connect.prepareStatement("SELECT * FROM BudgetPrevisionnel WHERE anneeScolaire = ? AND status = 1");
            search = this.connect.prepareStatement("SELECT * FROM BudgetPrevisionnel WHERE idBudget_Prev = ?");
            insert = this.connect.prepareStatement("INSERT INTO BudgetPrevisionnel (anneeScolaire,status) VALUES (?,?)");
            update = this.connect.prepareStatement("UPDATE BudgetPrevisionnel SET anneeScolaire = ?, status = ? WHERE idBudget_Prev = ?");
            delete = this.connect.prepareStatement("DELETE FROM BudgetPrevisionnel WHERE idBudget_Prev = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM BudgetPrevisionnel WHERE anneeScolaire = ? AND status = 1");
            last = this.connect.prepareStatement("SELECT max(idBudget_Prev) FROM BudgetPrevisionnel");
                    
            all = this.connect.prepareStatement("SELECT * FROM BudgetPrevisionnel");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de budgetPrevisionnelDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    public int Max(){
    	try{
    		ResultSet result = last.executeQuery();
    		if (!result.next()){
    			result.close();
    			return 0;
    		}
    		else{
    			return result.getInt(1);
    		}
    	}catch (SQLException ex)
    	{
    		System.out.println("Erreur lors de la recherche de la derniere insertion. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
            return -1;
    	}
    	
    }
    
    @Override
    public BudgetPrevisionnel find(int id) {
        BudgetPrevisionnel budgetPrevisionnel = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                budgetPrevisionnel = new BudgetPrevisionnel(result.getInt(1), result.getString(2), result.getInt(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la budgetPrevisionnel. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return budgetPrevisionnel;
    }
    
    public BudgetPrevisionnel find(String anneeScolaire) {
        BudgetPrevisionnel b = null;
        try {
            research.setString(1, anneeScolaire);
            ResultSet result = research.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                b = new BudgetPrevisionnel(result.getInt(1), result.getString(2), result.getInt(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche  de l'identifiant du budget previsionnel. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return b;
    }

    @Override
    public int create(BudgetPrevisionnel obj) {
        try {
            insert.setString(1, obj.getAnneeScolaire());
            insert.setInt(2, obj.getStatut());

            insert.executeUpdate();

            ArrayList<BudgetPrevisionnel> list = this.findAll(obj.getAnneeScolaire());
            BudgetPrevisionnel budgetPrevisionnel = list.get(list.size() - 1);

            int id = budgetPrevisionnel.getIdBudgetPrev();

            obj.setIdBudgetPrev(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une budgetPrevisionnel. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(BudgetPrevisionnel obj) {
        try {
            update.setString(1, obj.getAnneeScolaire());
            update.setInt(2, obj.getStatut());
            update.setInt(3, obj.getIdBudgetPrev());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une budgetPrevisionnel. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(BudgetPrevisionnel obj) {
        try {
            obj.setStatut(0);
            update.setString(1, obj.getAnneeScolaire());
            update.setInt(2, obj.getStatut());
            update.setInt(3, obj.getIdBudgetPrev());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une budgetPrevisionnel. Code erreur : " + e.getErrorCode());
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
            System.out.println("Erreur lors de la fermeture de budgetPrevisionnelDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<BudgetPrevisionnel> findAll(String anneeScolaire) {
        ArrayList<BudgetPrevisionnel> listBudgetPrevisionnels = new ArrayList<BudgetPrevisionnel>();
        try {
            affiche.setString(1, anneeScolaire);
            ResultSet result = affiche.executeQuery();
            while (result.next()) {
                listBudgetPrevisionnels.add(new BudgetPrevisionnel(result.getInt(1), result.getString(2), result.getInt(3)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listBudgetPrevisionnels;
    }
    
    public ArrayList<BudgetPrevisionnel> findAll_2() {
        ArrayList<BudgetPrevisionnel> listBudgetPrevisionnels = new ArrayList<BudgetPrevisionnel>();
        try {
            ResultSet result = all.executeQuery();
            while (result.next()) {
                listBudgetPrevisionnels.add(new BudgetPrevisionnel(result.getInt(1), result.getString(2), result.getInt(3)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listBudgetPrevisionnels;
    }
    
    public boolean searchAnnee(String anneeScolaire) {
        
        try {
            research.setString(1, anneeScolaire);
            ResultSet result = research.executeQuery();
            if (!result.next()) {
                result.close();
                return false;
            } else {
                result.close();
                return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche  de l'identifiant du budget previsionnel. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return true;
    }
    
    /*public static void main(String args[]) {
        BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
        //int id = budgetPrevisionnelDAO.create(new BudgetPrevisionnel("2020/2021", 1));
        BudgetPrevisionnel budgetPrevisionnel = budgetPrevisionnelDAO.find(1);
        //budgetPrevisionnel.setAnneeScolaire("2019/2020");
        boolean test = budgetPrevisionnelDAO.delete(budgetPrevisionnel);
        System.out.println(test);
    }*/
}
