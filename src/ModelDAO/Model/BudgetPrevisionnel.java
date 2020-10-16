package ModelDAO.Model;
/**
 *
 * @author GiTech
 */
public class BudgetPrevisionnel {
    
    private Integer idBudgetPrev = null;
    private String anneeScolaire;
    private int statut = 1;
    
    public BudgetPrevisionnel (String anneeScolaire, int statut)
    {
        super();
        this.anneeScolaire = anneeScolaire;
        if (statut == 0)
            this.statut = statut;
    }
    
    public BudgetPrevisionnel (int idBudgetPrev, String anneeScolaire, int statut)
    {
        this(anneeScolaire, statut);
        this.idBudgetPrev = idBudgetPrev;
    }

    /**
     * @return the idBudgetPrev
     */
    public Integer getIdBudgetPrev() {
        return idBudgetPrev;
    }

    /**
     * @param idBudgetPrev the idBudgetPrev to set
     */
    public void setIdBudgetPrev(Integer idBudgetPrev) {
        this.idBudgetPrev = idBudgetPrev;
    }

    /**
     * @return the anneeScolaire
     */
    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    /**
     * @param anneeScolaire the anneeScolaire to set
     */
    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    /**
     * @return the statut
     */
    public int getStatut() {
        return statut;
    }

    /**
     * @param statut the statut to set
     */
    public void setStatut(int statut) {
        this.statut = statut;
    }
    
    public boolean equals(BudgetPrevisionnel b)
    {
    	if(this.anneeScolaire.equals(b.anneeScolaire) && this.statut == b.statut)
    		return true;
    	return false;
    }
    
    @Override
	public String toString() {
		return "BudgetPrevisionnel [idBudgetPrev=" + idBudgetPrev + ", anneeScolaire=" + anneeScolaire + ", statut="
				+ statut + "]";
	}
    
}
