/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sportak
 */
public class Vendedor implements Serializable {

    private String codigo;
    private String nombre;
    private String categoria;
    private ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    public Vendedor(String codigo, String nombre, String categoria) {
        super();
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public Vehiculo buscarVehiculo(Vehiculo v) {
        for (int i = 0; i < this.listaVehiculos.size(); i++) {
            if (this.listaVehiculos.get(i).equals(v)) {
                return this.listaVehiculos.get(i);
            }
        }
        return null;
    }

    public void realizarVenta(Vehiculo v) {
        if (this.buscarVehiculo(v) == null) {
            this.listaVehiculos.add(v);
            System.out.println("Vendido con exito y con esto el vendedor ha sumado " + this.listaVehiculos.size() + "vehiculos ");
        } else {
            System.out.println("No se pudo añadir, ya fue vendido por este vendedor");
        }

    }

    @Override
    public String toString() {
        return "Vendedor{" + "codigo=" + codigo + ", nombre=" + nombre + ", categoria=" + categoria + ", num Vehiculos vendidos=" + listaVehiculos.size() + '}';
    }

}
