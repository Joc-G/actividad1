/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.ConnectBD;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author admin-sala3
 */
public class Leguaje {

    private int idLenguaje;
    private String name;
    private String last_update;

    public Leguaje() {
    }

    public Leguaje(int idLenguaje, String name, String last_update) {
        this.idLenguaje = idLenguaje;
        this.name = name;
        this.last_update = last_update;
    }

    public int getIdLenguaje() {
        return idLenguaje;
    }

    public void setIdLenguaje(int idLenguaje) {
        this.idLenguaje = idLenguaje;
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
        return "Leguaje{"+ ", name=" + name + ", last_update=" + last_update + '}';
    }

    public ResultSet TraerLenguaje(String LeerSQL) {

        ResultSet rset = null;

        ConnectBD CBD = new ConnectBD();

        if (CBD.crearConexion()) {

            try {
                rset = CBD.getSt().executeQuery(LeerSQL);

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

        return rset;

    }

}
