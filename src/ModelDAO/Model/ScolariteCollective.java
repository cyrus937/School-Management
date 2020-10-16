package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class ScolariteCollective {
    
    private Integer idScolariteCol = null;
    private String mois;
    private int montantPercu;
    private String anneeScolaire;
    private Classe classe;
    
    public ScolariteCollective (String mois, int montantPercu, String anneeScolaire, Classe classe)
    {
        super();
        this.mois = mois;
        this.montantPercu = montantPercu;
        this.anneeScolaire = anneeScolaire;
        this.classe = classe;
    }
    
    public ScolariteCollective (int idScolariteCol, String mois, int montantPercu, String anneeScolaire, Classe classe)
    {
        this(mois, montantPercu, anneeScolaire, classe);
        this.idScolariteCol = idScolariteCol;
    }

    /**
     * @return the idScolariteCol
     */
    public Integer getIdScolariteCol() {
        return idScolariteCol;
    }

    /**
     * @param idScolariteCol the idScolariteCol to set
     */
    public void setIdScolariteCol(Integer idScolariteCol) {
        this.idScolariteCol = idScolariteCol;
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
     * @return the montantPercu
     */
    public int getMontantPercu() {
        return montantPercu;
    }

    /**
     * @param montantPercu the montantPercu to set
     */
    public void setMontantPercu(int montantPercu) {
        this.montantPercu = montantPercu;
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
     * @return the classe
     */
    public Classe getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public String toString()
    {
        return this.mois;
    }
}
