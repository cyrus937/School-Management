/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author public
 */
public class ClasseDAO extends DAO<Classe> {

    public ClasseDAO() {
        
        try {
            search = this.connect.prepareStatement("SELECT * FROM Classe WHERE idClasse = ?");
            insert = this.connect.prepareStatement("INSERT INTO Classe (nom,nbEleve,pension,montantRecu,montantRestant,anneeScolaire) VALUES (?,?,?,?,?,?)");
            update = this.connect.prepareStatement("UPDATE Classe SET nom = ?, nbEleve = ?, pension = ?,montantRecu = ?, montantRestant = ?, anneeScolaire = ? WHERE idClasse = ?");
            delete = this.connect.prepareStatement("DELETE FROM Classe WHERE idClasse = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM Classe WHERE anneeScolaire = ?");
            affiche1 = this.connect.prepareStatement("SELECT * FROM Classe WHERE anneeScolaire = ? AND montantRestant = 0");
            affiche2 = this.connect.prepareStatement("SELECT * FROM Classe WHERE anneeScolaire = ? AND montantRestant > 0");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de classeDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public Classe find(int id) {
        Classe classe = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                classe = new Classe(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4), result.getInt(5),
                        result.getInt(6), result.getString(7));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la classe. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return classe;
    }

    @Override
    public int create(Classe obj) {
        try {
            insert.setString(1, obj.getNom());
            insert.setInt(2, obj.getNbEleve());
            insert.setInt(3, obj.getPension());
            insert.setInt(4, obj.getMontantRecu());
            insert.setInt(5, obj.getMontantRestant());
            insert.setString(6, obj.getAnneeScolaire());

            insert.executeUpdate();

            ArrayList<Classe> list = this.findAll(obj.getAnneeScolaire());
            Classe classe = list.get(list.size() - 1);

            int id = classe.getIdClasse();

            obj.setIdClasse(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une classe. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(Classe obj) {
        try {
            update.setString(1, obj.getNom());
            update.setInt(2, obj.getNbEleve());
            update.setInt(3, obj.getPension());
            update.setInt(4, obj.getMontantRecu());
            update.setInt(5, obj.getMontantRestant());
            update.setString(6, obj.getAnneeScolaire());
            update.setInt(7, obj.getIdClasse());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une classe. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Classe obj) {
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
            System.out.println("Erreur lors de la fermeture de classeDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Classe> findAll(String anneeScolaire) {
        ArrayList<Classe> listClasses = new ArrayList<Classe>();
        try {
            affiche.setString(1, anneeScolaire);
            ResultSet result = affiche.executeQuery();
            while (result.next()) {
                listClasses.add(new Classe(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getInt(6), result.getString(7)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listClasses;
    }
    
    public ArrayList<Classe> findAll_1(String anneeScolaire) {
        ArrayList<Classe> listClasses = new ArrayList<Classe>();
        try {
            affiche1.setString(1, anneeScolaire);
            ResultSet result = affiche1.executeQuery();
            while (result.next()) {
                listClasses.add(new Classe(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getInt(6), result.getString(7)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listClasses;
    }

    public ArrayList<Classe> findAll_2(String anneeScolaire) {
        ArrayList<Classe> listClasses = new ArrayList<Classe>();
        try {
            affiche2.setString(1, anneeScolaire);
            ResultSet result = affiche2.executeQuery();
            while (result.next()) {
                listClasses.add(new Classe(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4),
                        result.getInt(5), result.getInt(6), result.getString(7)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des classes. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listClasses;
    }
    
    
    /*public static void main(String args[]) {
        ClasseDAO classeDAO = new ClasseDAO();
        int id = classeDAO.create(new Classe("CM1", 8, 6, 4, 12, "2020/2021"));
        System.out.println(id);
    }*/
}
