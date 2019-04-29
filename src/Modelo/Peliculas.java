/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ConnectBD;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author Julian
 */
public class Peliculas {

    private String title;
    private String description;
    private int release_year;
    private int language_id;
    private int original_language_id;
    private int rental_duration;
    private double rental_rate;
    private int length;
    private double replacement_cost;
    private String rating;
    private String special_features;
    private String last_update;

    public Peliculas() {
    }

    public Peliculas(String title, String description, int release_year, int language_id, int original_language_id, int rental_duration, double rental_rate, int length, double replacement_cost, String rating, String special_features, String last_update) {
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.language_id = language_id;
        this.original_language_id = original_language_id;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
        this.rating = rating;
        this.special_features = special_features;
        this.last_update = last_update;
    }

    public Peliculas(String title, int language_id, int rental_duration, double rental_rate, double replacement_cost, String last_update) {
        this.title = title;
        this.language_id = language_id;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
        this.last_update = last_update;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(int original_language_id) {
        this.original_language_id = original_language_id;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Peliculas{" + ", title=" + title + ", description=" + description + ", release_year=" + release_year + ", language_id=" + language_id + ", original_language_id=" + original_language_id + ", rental_duration=" + rental_duration + ", rental_rate=" + rental_rate + ", length=" + length + ", replacement_cost=" + replacement_cost + ", rating=" + rating + ", special_features=" + special_features + ", last_update=" + last_update + '}';
    }

    public boolean insertarPelicula(Peliculas peli, String insertarSQL) {

        ConnectBD CBD = new ConnectBD();
        PreparedStatement ps = null;
        boolean f = false;

        if (CBD.crearConexion()) {

            try {
                CBD.getConexion().setAutoCommit(false);
                ps = CBD.getConexion().prepareStatement(insertarSQL);

                ps.setString(1, peli.getTitle());

                if (peli.getDescription().equals("")) {
                    ps.setString(2, null);
                } else {
                    ps.setString(2, peli.getDescription());
                }

                if (peli.release_year == -1) {
                    ps.setString(3, null);
                } else {
                    ps.setShort(3, Short.parseShort(String.valueOf(peli.release_year)));
                }

                ps.setInt(4, peli.getLanguage_id());

                if (peli.getOriginal_language_id() == -1) {
                    ps.setString(5, null);
                } else {
                    ps.setInt(5, peli.getOriginal_language_id());
                }

                ps.setInt(6, peli.getRental_duration());
                ps.setDouble(7, peli.getRental_rate());

                if (peli.getLength() == -1) {
                    ps.setString(8, null);
                } else {
                    ps.setInt(8, peli.getLength());
                }

                ps.setDouble(9, peli.getReplacement_cost());

                if (peli.getRating().equals("---------------")) {
                    ps.setString(10, null);
                } else {
                    ps.setString(10, peli.getRating());
                }

                if (peli.getSpecial_features().equals("---------------")) {
                    ps.setString(11, null);
                } else {
                    ps.setString(11, peli.getSpecial_features());
                }

                ps.setTimestamp(12, Timestamp.valueOf(peli.getLast_update()));

                ps.executeUpdate();
                CBD.getConexion().commit();

                f = true;

            } catch (Exception ex) {

                ex.printStackTrace();
                f = false;

            }
        }

        return f;
    }

    public boolean modificarPelicula(Peliculas peli, String insertarSQL) {
        ConnectBD CBD = new ConnectBD();
        PreparedStatement ps = null;
        boolean f = false;

        if (CBD.crearConexion()) {

            try {
                CBD.getConexion().setAutoCommit(false);
                ps = CBD.getConexion().prepareStatement(insertarSQL);

                ps.setString(1, peli.getTitle());

                if (peli.getDescription().equals("")) {
                    ps.setString(2, null);
                } else {
                    ps.setString(2, peli.getDescription());
                }

                if (peli.release_year == -1) {
                    ps.setString(3, null);
                } else {
                    ps.setShort(3, Short.parseShort(String.valueOf(peli.release_year)));
                }

                ps.setInt(4, peli.getLanguage_id());

                if (peli.getOriginal_language_id() == -1) {
                    ps.setString(5, null);
                } else {
                    ps.setInt(5, peli.getOriginal_language_id());
                }

                ps.setInt(6, peli.getRental_duration());
                ps.setDouble(7, peli.getRental_rate());

                if (peli.getLength() == -1) {
                    ps.setString(8, null);
                } else {
                    ps.setInt(8, peli.getLength());
                }

                ps.setDouble(9, peli.getReplacement_cost());

                if (peli.getRating().equals("---------------")) {
                    ps.setString(10, null);
                } else {
                    ps.setString(10, peli.getRating());
                }

                if (peli.getSpecial_features().equals("---------------")) {
                    ps.setString(11, null);
                } else {
                    ps.setString(11, peli.getSpecial_features());
                }

                ps.setTimestamp(12, Timestamp.valueOf(peli.getLast_update()));

                ps.executeUpdate();
                CBD.getConexion().commit();

                f = true;

            } catch (Exception ex) {

                ex.printStackTrace();
                f = false;

            }
        }

        return f;
    }

    public ResultSet traerPelicula(String sql) {

        ResultSet rset = null;
        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {

                rset = CBD.getSt().executeQuery(sql);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;

    }

    public ResultSet peliculaPorCliente(String sql) {

        ResultSet rset = null;
        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {
                rset = CBD.getSt().executeQuery(sql);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;
    }

    public ResultSet peliculaPorActor(String sql) {
        
        ResultSet rset = null;
        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {
                rset = CBD.getSt().executeQuery(sql);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;
    }

    public ResultSet peliculaPorCate(String sql) {

        ResultSet rset = null;
        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {
                rset = CBD.getSt().executeQuery(sql);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;
    }

    public ResultSet peliculaPorFecha(String sql) {

        ResultSet rset = null;
        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {
                rset = CBD.getSt().executeQuery(sql);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;
    }

}
