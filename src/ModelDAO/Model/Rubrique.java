package ModelDAO.Model;

/**
 *
 * @author GiTech
 */
public class Rubrique {
    
    private Integer idRubrique = null;
    private String nom;
    private String type;
    
    public Rubrique (String nom, String type)
    {
        super();
        this.nom = nom;
        this.type = type;
    }
    
    public Rubrique (int idRubrique, String nom, String type)
    {
        this(nom, type);
        this.idRubrique = idRubrique;
    }

    /**
     * @return the idRubrique
     */
    public Integer getIdRubrique() {
        return idRubrique;
    }

    /**
     * @param idRubrique the idRubrique to set
     */
    public void setIdRubrique(Integer idRubrique) {
        this.idRubrique = idRubrique;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public String toString()
    {
        return this.nom;
    }
    
    public boolean equals(Rubrique r)
    {
    	if (this.nom.equals(r.nom) && this.type.equals(r.type))
    		return true;
    	return false;
    }
}
