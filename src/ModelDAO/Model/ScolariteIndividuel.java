package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class ScolariteIndividuel {
    
    private Integer idScolariteIndiv = null;
    private String date;
    private int montantPaye;
    private int montantRestant = 0;
    private String anneeScolaire;
    private int statut = 1;
    private Eleve eleve;
    
    public ScolariteIndividuel (String date, int montantPaye, int montantRestant, String anneeScolaire, int statut, Eleve eleve)
    {
        super();
        this.date = date;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.anneeScolaire = anneeScolaire;
        this.statut = statut;
        this.eleve = eleve;
    }
    
    public ScolariteIndividuel (int idScolariteIndiv, String date, int montantPaye, int montantRestant, String anneeScolaire, int statut, Eleve eleve)
    {
        this(date, montantPaye, montantRestant, anneeScolaire, statut, eleve);
        this.idScolariteIndiv = idScolariteIndiv;
    }

    ScolariteIndividuel(String date, int montantPaye, String anneeScolaire, Eleve eleve) {
        super();
        this.date = date;
        this.montantPaye = montantPaye;
        this.anneeScolaire = anneeScolaire;
        this.eleve = eleve;
    }

    /**
     * @return the idScolariteIndiv
     */
    public Integer getIdScolariteIndiv() {
        return idScolariteIndiv;
    }

    /**
     * @param idScolariteIndiv the idScolariteIndiv to set
     */
    public void setIdScolariteIndiv(Integer idScolariteIndiv) {
        this.idScolariteIndiv = idScolariteIndiv;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the montantPaye
     */
    public int getMontantPaye() {
        return montantPaye;
    }

    /**
     * @param montantPaye the montantPaye to set
     */
    public void setMontantPaye(int montantPaye) {
        this.montantPaye = montantPaye;
    }

    /**
     * @return the montantRestant
     */
    public int getMontantRestant() {
        return montantRestant;
    }

    /**
     * @param montantRestant the montantRestant to set
     */
    public void setMontantRestant(int montantRestant) {
        this.montantRestant = montantRestant;
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

    /**
     * @return the eleve
     */
    public Eleve getEleve() {
        return eleve;
    }

    /**
     * @param eleve the eleve to set
     */
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
    
    public String toString(){
        return this.date;
    }
}
