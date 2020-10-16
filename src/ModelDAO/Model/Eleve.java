
package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class Eleve {
    
    private String matricule;
    private String nom;
    private String prenom;
    private String dateInscription;
    private int montantPaye;
    private int montantRestant;
    private String anneeScolaire;
    private int statut = 1;
    private Classe classe;

    public Eleve (String matricule, String nom, String prenom, String dateInscription, int montantPaye, int montantRestant, 
            String anneeScolaire, int statut, Classe classe) {
        super();
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = dateInscription;
        this.montantPaye = montantPaye;
        this.montantRestant = montantRestant;
        this.anneeScolaire = anneeScolaire;
        if (statut == 0)
            this.statut = statut;
        this.classe = classe;
    }
    /**
     * @return the matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
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
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the dateInscription
     */
    public String getDateInscription() {
        return dateInscription;
    }

    /**
     * @param dateInscription the dateInscription to set
     */
    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
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
}
