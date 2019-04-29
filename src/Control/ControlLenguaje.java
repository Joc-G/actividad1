/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Leguaje;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author admin-sala3
 */
public class ControlLenguaje {
    
    public LinkedList<Leguaje> LeerLenguaje() {

        LinkedList<Leguaje> lenguaje = new LinkedList<>();

        Leguaje le = new Leguaje();

        Leguaje enviarLenguajes = null;

        ResultSet rset;

        String LeerSQL = "SELECT * FROM language";

        rset = le.TraerLenguaje(LeerSQL);

        try {

            while (rset.next()) {

                int idLenguaje =  Integer.valueOf(rset.getString("language_id"));
                String nombre = String.valueOf(rset.getString("name"));
                String last_update = String.valueOf(rset.getString("last_update"));
                
                enviarLenguajes = new Leguaje( idLenguaje,nombre, last_update);

                lenguaje.add(enviarLenguajes);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos" + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }

        return lenguaje;

    }
    
}
