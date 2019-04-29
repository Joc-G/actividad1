/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Categoria;
import Modelo.Leguaje;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario1
 */
public class ControlCategoria {
    
    public LinkedList<Categoria> LeerCategorias() {

        LinkedList<Categoria> categorias = new LinkedList<>();
        Categoria le = new Categoria();

        Categoria enviarLenguajes = null;
        ResultSet rset;

        String sql = "SELECT * FROM category";

        rset = le.TraerCategorias(sql);

        try {

            while (rset.next()) {

                int category_id =  Integer.valueOf(rset.getString("category_id"));
                String name = String.valueOf(rset.getString("name"));
                String last_update = String.valueOf(rset.getString("last_update"));
                
                enviarLenguajes = new Categoria( category_id,name, last_update);

                categorias.add(enviarLenguajes);

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Fallo en la lectura de datos" + ex, "Error", JOptionPane.ERROR_MESSAGE);

        }

        return categorias;

    }
    
}
