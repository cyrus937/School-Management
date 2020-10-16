package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class Classe {
    
    private Integer idClasse = null;
    private String nom;
    private int nbEleve = 0;
    private int pension;
    private int montantRecu;
    private int montantRestant;
    private String anneeScolaire;

    public Classe (String nom, int nbEleve, int pension, int montantRecu, int montantRestant, String anneeScolaire)
    {
        this.nom = nom;
        this.nbEleve = nbEleve;
        this.pension = pension;
        this.montantRecu = montantRecu;
        this.montantRestant = montantRestant;
        this.anneeScolaire = anneeScolaire;
    }
    
    public Classe (int idClasse, String nom, int nbEleve, int pension, int montantRecu, int montantRestant, String anneeScolaire)
    {
        this(nom,nbEleve,pension,montantRecu,montantRestant,anneeScolaire);
        this.idClasse = idClasse;
    }
    /**
     * @return the idClasse
     */
    public Integer getIdClasse() {
        return idClasse;
    }

    /**
     * @param idClasse the idClasse to set
     */
    public void setIdClasse(Integer idClasse) {
        this.idClasse = idClasse;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the nbEleve
     */
    public int getNbEleve() {
        return nbEleve;
    }

    /**
     * @param nbEleve the nbEleve to set
     */
    public void setNbEleve(int nbEleve) {
        this.nbEleve = nbEleve;
    }

    /**
     * @return the pension
     */
    public int getPension() {
        return pension;
    }

    /**
     * @param pension the pension to set
     */
    public void setPension(int pension) {
        this.pension = pension;
    }

    /**
     * @return the montantRecu
     */
    public int getMontantRecu() {
        return montantRecu;
    }

    /**
     * @param montantRecu the montantRecu to set
     */
    public void setMontantRecu(int montantRecu) {
        this.montantRecu = montantRecu;
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
    
}
