package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class BudgetDAO extends DAO<Budget> {

    public BudgetDAO() {
        try {
            search = this.connect.prepareStatement("SELECT * FROM Budget WHERE mois = ? AND idRubrique = ? AND idBudget_Prev = ?");
            insert = this.connect.prepareStatement("INSERT INTO Budget (mois,idRubrique,idBudget_Prev,montant) VALUES (?,?,?,?)");
            update = this.connect.prepareStatement("UPDATE Budget SET mois = ?, idRubrique = ?, idBudget_Prev = ?, montant = ? WHERE mois = ? AND idRubrique = ? AND idBudget_Prev = ?");
            delete = this.connect.prepareStatement("DELETE FROM Budget WHERE idBudget_Prev = ?");

            affiche = this.connect.prepareStatement("SELECT * FROM Budget WHERE idBudget_Prev = ? ORDER BY idRubrique ASC");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de budgetDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }

    }

    @Override
    public Budget find(int id) {
        /*Budget budget = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                budget = new Budget(result.getInt(1), result.getString(2), result.getString(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la donnee. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }*/
        return null;
    }

    public Budget find(String mois, Rubrique r, BudgetPrevisionnel b) {
        Budget budget = null;
        try {
            search.setString(1, mois);
            search.setInt(2, r.getIdRubrique());
            search.setInt(3, b.getIdBudgetPrev());
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                RubriqueDAO rubriqueDAO = new RubriqueDAO();
                Rubrique rubrique = rubriqueDAO.find(result.getInt(2));
                BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
                BudgetPrevisionnel budgetPrevisionnel = budgetPrevisionnelDAO.find(result.getInt(3));
                budget = new Budget(result.getString(1), rubrique, budgetPrevisionnel, result.getInt(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la donnee. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return budget;
    }

    @Override
    public int create(Budget obj) {
        try {
            insert.setString(1, obj.getMois());
            insert.setInt(2, obj.getRubrique().getIdRubrique());
            insert.setInt(3, obj.getBudgetPrevisionnel().getIdBudgetPrev());
            insert.setInt(4, obj.getMontant());

            insert.executeUpdate();

            return 1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une donnee. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(Budget obj) {
        try {
            update.setString(1, obj.getMois());
            update.setInt(2, obj.getRubrique().getIdRubrique());
            update.setInt(3, obj.getBudgetPrevisionnel().getIdBudgetPrev());
            update.setInt(4, obj.getMontant());
            update.setString(5, obj.getMois());
            update.setInt(6, obj.getRubrique().getIdRubrique());
            update.setInt(7, obj.getBudgetPrevisionnel().getIdBudgetPrev());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une donnee. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Budget obj) {
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
            System.out.println("Erreur lors de la fermeture de budgetDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Budget> findAll(String anneeScolaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Budget> findAll(int budgetPrev) {
        ArrayList<Budget> listBudget = new ArrayList<Budget>();
        try {
        	affiche.setInt(1, budgetPrev);
            ResultSet result = affiche.executeQuery();
            RubriqueDAO rubriqueDAO = new RubriqueDAO();
            Rubrique rubrique;
            BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
            BudgetPrevisionnel budgetPrevisionnel;
            while (result.next()) {
                rubrique = rubriqueDAO.find(result.getInt(2));
                budgetPrevisionnel = budgetPrevisionnelDAO.find(result.getInt(3));
                listBudget.add(new Budget(result.getString(1), rubrique, budgetPrevisionnel, result.getInt(4)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des donnees. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listBudget;
    }

	public void create(ArrayList<Budget> list) {
		try {
			for (Budget b : list)
			{
				insert.setString(1, b.getMois());
		        insert.setInt(2, b.getRubrique().getIdRubrique());
		        insert.setInt(3, b.getBudgetPrevisionnel().getIdBudgetPrev());
		        insert.setInt(4, b.getMontant());
		        insert.executeUpdate(); 
			}
		} catch (SQLException e) {
			System.out.println("Erreur lors de l'ajout des donnees. Code erreur : " + e.getErrorCode());
			e.printStackTrace();
		}
	}

    /*public static void main(String args[]) {
        BudgetDAO BbudgetDAO = new BudgetDAO();
        RubriqueDAO rubriqueDAO = new RubriqueDAO();
        Rubrique rubrique = rubriqueDAO.find(1);
        BudgetPrevisionnelDAO budgetPrevisionnelDAO = new BudgetPrevisionnelDAO();
        BudgetPrevisionnel budgetPrevisionnel = budgetPrevisionnelDAO.find(1);
        //int id = BbudgetDAO.create(new Budget("Octobre", rubrique, budgetPrevisionnel, 60500));
        Budget budget = BbudgetDAO.find("Octobre", rubrique, budgetPrevisionnel);
        budget.setMois("Novembre");
        budget.setMontant(55000);
        boolean test = BbudgetDAO.update(budget);
        System.out.println(test);
    }*/
}
