/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Vehiculo;
import modelo.Vendedor;

/**
 *
 * @author sportak
 */
public class GestorFicheros {

    public void guardarVehiculo(Vehiculo vh, File archivoDestino) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino));
        oos.writeObject(vh);
        oos.close();
    }

    public void AñadirVehiculo(Vehiculo vh, File archivoDestino) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new AppendTheObjectNow(new FileOutputStream(archivoDestino, true));
        oos.writeObject(vh);
        oos.close();
    }

    public void guardarVendedor(Vendedor vh, File archivoDestino) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDestino));
        oos.writeObject(vh);
        oos.close();
    }

    public void AñadirVendedor(Vendedor vh, File archivoDestino) throws FileNotFoundException, IOException {
        ObjectOutputStream oos = new AppendTheObjectNow(new FileOutputStream(archivoDestino, true));
        oos.writeObject(vh);
        oos.close();
    }

    public void verVehiculos(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Vehiculo ve = null;
        do {
            ve = (Vehiculo) ois.readObject();
            //System.out.println("Datos Vehiculo");
            //System.out.println(ve.toString());
        } while (ve != null);
        ois.close();
    }

    public ArrayList<Vehiculo> leerVehiculos(File f, ArrayList<Vehiculo> lista) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Vehiculo ve = null;
        do {
            ve = (Vehiculo) ois.readObject();
            if (ve != null) {
                lista.add(ve);
            }
        } while (ve != null);
        return lista;
    }

    public ArrayList<Vendedor> leerVendedores(File f, ArrayList<Vendedor> lista) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        Vendedor ven = null;
        do {
            ven = (Vendedor) ois.readObject();
            if (ven != null) {
                System.out.println("LEIDO VENDEDOR CON INFORMACIÓN");
                System.out.println(ven.toString());
                System.out.println("");
                lista.add(ven);
            }
        } while (ven != null);
        return lista;
    }

    public void mostrarVehiculosArray(ArrayList<Vehiculo> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "]" + " " + lista.get(i).toString());
        }
    }

    public void mostrarVendedoresArray(ArrayList<Vendedor> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("[" + i + "]" + " " + lista.get(i).toString());
        }
    }

    public Vendedor recuperarVendedorPorIndice(int indice, ArrayList<Vendedor> lista) {
        if (indice > (lista.size() - 1)) {
            return null;
        } else {
            return lista.get(indice);
        }
    }

    public Vehiculo recuperarVehiculoPorIndice(int indice, ArrayList<Vehiculo> lista) {
        if (indice > (lista.size() - 1)) {
            return null;
        } else {
            return lista.get(indice);
        }
    }

    public void actualizarBasesDeDatos(ArrayList<Vehiculo> listaVehiculos, File fichVehiculos, File fichVendedor, ArrayList<Vendedor> listaVendedor) throws FileNotFoundException, IOException {
        ObjectOutputStream oosVehiculo = new ObjectOutputStream(new FileOutputStream(fichVehiculos));
        for (int i = 0; i < listaVehiculos.size(); i++) {
            oosVehiculo.writeObject(listaVehiculos.get(i));
        }
        oosVehiculo.close();

        ObjectOutputStream oosVendedor = new ObjectOutputStream(new FileOutputStream(fichVendedor));
        for (int i = 0; i < listaVendedor.size(); i++) {
            oosVendedor.writeObject(listaVendedor.get(i));
        }
        oosVendedor.close();
        System.out.println("Actualizados exitosamente");
    }

}
