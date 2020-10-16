/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAO.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GiTech
 */
public class DataDAO extends DAO<Data> {
    
	public PreparedStatement search_nom;
	
    public DataDAO() {
        try {
            search = this.connect.prepareStatement("SELECT * FROM Data WHERE idData = ?");
            insert = this.connect.prepareStatement("INSERT INTO Data (nom,valeur) VALUES (?,?)");
            update = this.connect.prepareStatement("UPDATE Data SET nom = ?, valeur = ? WHERE idData = ?");
            delete = this.connect.prepareStatement("DELETE FROM Data WHERE idData = ?");
            affiche = this.connect.prepareStatement("SELECT * FROM Data");
            
            search_nom = this.connect.prepareStatement("SELECT * FROM Data WHERE nom = ?");
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'instanciation de dataDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }

    }

    @Override
    public Data find(int id) {
        Data data = null;
        try {
            search.setInt(1, id);
            ResultSet result = search.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                data = new Data(result.getInt(1), result.getString(2), result.getString(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la donnee. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return data;
    }
    
    public Data find(String nom) {
        Data data = null;
        try {
            search_nom.setString(1, nom);
            ResultSet result = search_nom.executeQuery();
            if (!result.next()) {
                result.close();
                return null;
            } else {
                data = new Data(result.getInt(1), result.getString(2), result.getString(3));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche de la donnee. Code erreur : " + ex.getErrorCode());
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public int create(Data obj) {
        try {
            insert.setString(1, obj.getNom());
            insert.setString(2, obj.getValeur());

            insert.executeUpdate();

            ArrayList<Data> list = this.findAll();
            Data data = list.get(list.size() - 1);

            int id = data.getIdData();

            obj.setIdData(id);
            return id;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une donnee. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(Data obj) {
        try {
            update.setString(1, obj.getNom());
            update.setString(2, obj.getValeur());
            update.setInt(3, obj.getIdData());
            update.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une donnee. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Data obj) {
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
            System.out.println("Erreur lors de la fermeture de dataDAO. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Data> findAll(String anneeScolaire) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Data> findAll() {
        ArrayList<Data> listData = new ArrayList<Data>();
        try {
            ResultSet result = affiche.executeQuery();
            while (result.next()) {
                listData.add(new Data(result.getInt(1), result.getString(2), result.getString(3)));
            }

            result.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche des donnees. Code erreur : " + e.getErrorCode());
            e.printStackTrace();
        }
        return listData;
    }
    
    /*public static void main(String args[]) {
        DataDAO dataDAO = new DataDAO();
        //int id = dataDAO.create(new Data("ecole", "COSBIE"));
        Data data = dataDAO.find(1);
        data.setValeur("LBM");
        boolean test = dataDAO.update(data);
        System.out.println(test);
    }*/
}
