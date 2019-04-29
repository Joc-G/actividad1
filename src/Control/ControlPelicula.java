/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Peliculas;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author admin-sala3
 */
public class ControlPelicula {

    public boolean InsertarPelicula(Peliculas peli) {

        boolean t = false;

//        String insertarSQL = "INSERT INTO film VALUES ("+peli.getFilm_id()+", '"+peli.getTitle()+"', '"+peli.getDescription()+"', "+
//                peli.getRelease_year()+", "+idLenguaje+", "+idLenguajeOriginal+", "+peli.getRental_duration()+", "+peli.getRental_rate()+", "+peli.getLength()+", "+
//                peli.getReplacement_cost()+", '"+peli.getRating()+"', '"+peli.getSpecial_features()+"', "+peli.getLast_update()+")";
//        String insertarSQL = "CALL insertarPelicula ('"+peli.getTitle()+"', '"+peli.getDescription()+"', "+
//            peli.getRelease_year()+", "+idLenguaje+", "+idLenguajeOriginal+", "+peli.getRental_duration()+", "+peli.getRental_rate()+", "+peli.getLength()+", "+
//            peli.getReplacement_cost()+", '"+peli.getRating()+"', '"+peli.getSpecial_features()+"', "+peli.getLast_update()+")";
        String insertarSQL = "call insertarPelicula(?,?,?,?,?,?,?,?,?,?,?,?)";

        t = peli.insertarPelicula(peli, insertarSQL);

        return t;

    }

    public Peliculas BuscarPelicula(int idFilm) {

        ResultSet rset = null;
        Peliculas P = new Peliculas();
        Peliculas peli = null;

        String sql = "SELECT title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update FROM film WHERE film_id = " + idFilm;

        rset = P.traerPelicula(sql);
        String title = "";

        try {
            if (rset.next()) {

                title = rset.getString("title");
                String description = String.valueOf(rset.getString("description"));
                int release_year = rset.getShort("release_year");
                int language_id = rset.getInt("language_id");
                int original_language_id = rset.getInt("original_language_id");
                int rental_duration = rset.getInt("rental_duration");
                double rental_rate = rset.getDouble("rental_rate");
                int length = rset.getInt("length");
                double replacement_cost = rset.getDouble("replacement_cost");
                String rating = rset.getString("rating");
                String special_features = rset.getString("special_features");
                String last_update = rset.getString("last_update");

                peli = new Peliculas(title, description, release_year, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features, last_update);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos" + ex, "Error", JOptionPane.ERROR_MESSAGE);
            peli = null;

        }

        return peli;
    }
    
    public boolean modificarPelicula(Peliculas peli, int idFilm)
    {
        boolean t = false;
        
        Peliculas P = new Peliculas();
        
        String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, original_language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ?, last_update = ? WHERE film_id = "+idFilm;
        
        t = P.modificarPelicula(peli, sql);
        
        return t;
    }   

    public LinkedList<String> peliculaPorCliente(int idCliente) {
        
        LinkedList<String> peliculasCliente = new LinkedList<>();
        
        ResultSet rs = null;
        Peliculas peli = new Peliculas();
        
        String sql = "select film.title, film.description, film.release_year from film "
                + "inner join inventory on inventory.film_id = film.film_id "
                + "inner join rental on rental.inventory_id = inventory.inventory_id "
                + "inner join customer on customer.customer_id = rental.customer_id "
                + "where customer.customer_id ="+ idCliente;
        
        rs = peli.peliculaPorCliente(sql);
        
        try {

            while (rs.next()) {

                String titulo = rs.getString("title");
                String descripcion = rs.getString("description");
                String estreno = rs.getString("release_year");

                String datos = titulo + "//" + descripcion + "//" + estreno;

                peliculasCliente.add(datos);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos: " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return peliculasCliente;
    }
    
    public LinkedList<String> peliculaPorActor(int idActor) {
        
        LinkedList<String> peliculasActor = new LinkedList<>();
        
        ResultSet rs = null;
        Peliculas peli = new Peliculas();
        
        String sql = "select f.title, f.description, f.release_year from actor a\n" +
                    "inner join film_actor as fa on fa.actor_id = a.actor_id\n" +
                    "inner join film as f on f.film_id = fa.film_id\n" +
                    "where a.actor_id ="+ idActor;
        
        rs = peli.peliculaPorActor(sql);
        
        try {

            while (rs.next()) {

                String titulo = rs.getString("title");
                String descripcion = rs.getString("description");
                String estreno = rs.getString("release_year");

                String datos = titulo + "//" + descripcion + "//" + estreno;

                peliculasActor.add(datos);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos: " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return peliculasActor;
    }

    public LinkedList<String> peliculaPorCategoria(int pkCate) {

        LinkedList<String> peliculasCate = new LinkedList<>();
        
        ResultSet rs = null;
        Peliculas peli = new Peliculas();
        
        String sql = "select f.title, f.description, f.release_year from category c\n" +
                    "inner join film_category as fc on fc.category_id = c.category_id\n" +
                    "inner join film as f on f.film_id = fc.film_id\n" +
                    "where c.category_id ="+ pkCate;
        
        rs = peli.peliculaPorCate(sql);
        
        try {

            while (rs.next()) {

                String titulo = rs.getString("title");
                String descripcion = rs.getString("description");
                String estreno = rs.getString("release_year");

                String datos = titulo + "//" + descripcion + "//" + estreno;

                peliculasCate.add(datos);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos: " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return peliculasCate;
    }

    public LinkedList<String> peliculaPorFechas(String inicioF, String finalF) {

        LinkedList<String> peliculasFecha = new LinkedList<>();
        
        ResultSet rs = null;
        Peliculas peli = new Peliculas();
        
        String sql = "select concat(cu.first_name, \" \", cu.last_name) as names, r.rental_date,f.title, f.description, f.release_year from film f\n" +
                    "inner join inventory as i on i.film_id = f.film_id\n" +
                    "inner join rental as r on r.inventory_id = i.inventory_id\n" +
                    "inner join customer as cu on cu.customer_id = r.customer_id\n" +
                    "where r.rental_date between '"+ inicioF +"' and '"+ finalF +"'";
        
        rs = peli.peliculaPorFecha(sql);
        
        try {

            while (rs.next()) {

                String nombres = rs.getString("names");
                String devolucion = rs.getString("rental_date");
                String titulo = rs.getString("title");
                String descripcion = rs.getString("description");
                String estreno = rs.getString("release_year");

                String datos = nombres+"//"+devolucion+"//"+titulo + "//" + descripcion + "//" + estreno;

                peliculasFecha.add(datos);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos: " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }
        
        return peliculasFecha;
    }
}
