package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class Data {

    private Integer idData = null;
    private String nom = null;
    private String valeur = null;

    public Data(String nom, String valeur) {
        super();
        this.nom = nom;
        this.valeur = valeur;
    }

    public Data(Integer idData, String nom, String valeur) {
        this(nom, valeur);
        this.idData = idData;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "nom: " + nom + "\n"
                + "valeur: " + valeur;
    }
}
