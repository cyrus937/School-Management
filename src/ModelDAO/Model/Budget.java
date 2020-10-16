package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class Budget {
    
    private String mois;
    private Rubrique rubrique;
    private BudgetPrevisionnel budgetPrevisionnel;
    private int montant;
    
    public Budget (String mois, Rubrique rubrique, BudgetPrevisionnel budgetPrevisionnel, int montant)
    {
        super();
        this.mois = mois;
        this.rubrique = rubrique;
        this.budgetPrevisionnel = budgetPrevisionnel;
        this.montant = montant;
    }

    /**
     * @return the mois
     */
    public String getMois() {
        return mois;
    }

    /**
     * @param mois the mois to set
     */
    public void setMois(String mois) {
        this.mois = mois;
    }

    /**
     * @return the rubrique
     */
    public Rubrique getRubrique() {
        return rubrique;
    }

    /**
     * @param rubrique the rubrique to set
     */
    public void setRubrique(Rubrique rubrique) {
        this.rubrique = rubrique;
    }

    /**
     * @return the budgetPrevisionnel
     */
    public BudgetPrevisionnel getBudgetPrevisionnel() {
        return budgetPrevisionnel;
    }

    /**
     * @param budgetPrevisionnel the budgetPrevisionnel to set
     */
    public void setBudgetPrevisionnel(BudgetPrevisionnel budgetPrevisionnel) {
        this.budgetPrevisionnel = budgetPrevisionnel;
    }

    /**
     * @return the montant
     */
    public int getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(int montant) {
        this.montant = montant;
    }
    
}
