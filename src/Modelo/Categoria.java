/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ConnectBD;
import java.sql.ResultSet;

/**
 *
 * @author jose_dav.gomez
 */
public class Categoria {

    private int category_id;
    private String name;
    private String last_update;

    public Categoria() {
    }

    public Categoria(int category_id, String name, String last_update) {
        this.category_id = category_id;
        this.name = name;
        this.last_update = last_update;
    }    

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Categoria{" + "category_id=" + category_id + ", name=" + name + ", last_update=" + last_update + '}';
    }

    public ResultSet TraerCategorias(String sql) {

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
