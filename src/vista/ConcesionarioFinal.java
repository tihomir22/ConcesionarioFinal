/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.GestorFicheros;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Vehiculo;
import modelo.Vendedor;

/**
 *
 * @author sportak
 */
public class ConcesionarioFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestorFicheros gf = new GestorFicheros();
        ArrayList<Vehiculo> listaVehiculo = new ArrayList<>();
        ArrayList<Vendedor> listaVendedor = new ArrayList<>();
        Vendedor activo = null;
        Vehiculo activoVehi = null;
        String marca, modelo, matricula, potencia, color, codigo, nombre, categoria;
        Integer precio;
        File archivoVehiculo = new File("vehiculo.bat");
        File archivoVendedor = new File("vendedor.bat");

        Scanner teclado = new Scanner(System.in);
        int opcion = 999;
        do {
            mostrarMenu();
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1: {
                    listaVehiculo.clear();
                    listaVendedor.clear();
                    try {
                        listaVehiculo = gf.leerVehiculos(archivoVehiculo, listaVehiculo);
                    } catch (IOException ex) {
                        System.out.println("\u001B[0m"+"Fin de lectura "+"\u001B[31m");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        listaVendedor = gf.leerVendedores(archivoVendedor, listaVendedor);

                    } catch (IOException ex) {
                        System.out.println("\u001B[0m"+"Fin de lectura "+"\u001B[31m");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                System.out.println("Se han dado de alta " + listaVehiculo.size() + " Vehiculos");
                System.out.println("Se han dado de alta " + listaVendedor.size() + " Vendedores");
                break;

                case 2:
                    System.out.println("Elige : [1]Vehiculo [2]Vendedor");
                    opcion = teclado.nextInt();
                    teclado.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Marca");
                            marca = teclado.nextLine();
                            System.out.println("Modelo");
                            modelo = teclado.nextLine();
                            System.out.println("Matricula");
                            matricula = teclado.nextLine();
                            System.out.println("Potencia");
                            potencia = teclado.nextLine();
                            System.out.println("Color");
                            color = teclado.nextLine();
                            System.out.println("Precio");
                            precio = teclado.nextInt();
                            Vehiculo v = new Vehiculo(marca, modelo, matricula, potencia, color, precio);
                            listaVehiculo.add(v);
                            if (archivoVehiculo.exists()) {
                                try {
                                    gf.AñadirVehiculo(v, archivoVehiculo);
                                } catch (IOException ex) {
                                    Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    gf.AñadirVehiculo(v, archivoVendedor);
                                } catch (IOException ex) {
                                    Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                        case 2:
                            System.out.println("Codigo");
                            codigo = teclado.nextLine();
                            System.out.println("Nombre");
                            nombre = teclado.nextLine();
                            System.out.println("Categoria");
                            categoria = teclado.nextLine();
                            Vendedor ve = new Vendedor(codigo, nombre, categoria);
                            listaVendedor.add(ve);
                            if (archivoVendedor.exists()) {
                                try {
                                    gf.AñadirVendedor(ve, archivoVendedor);
                                } catch (IOException ex) {
                                    Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    gf.guardarVendedor(ve, archivoVendedor);
                                } catch (IOException ex) {
                                    Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                    }
                    break;

                case 3:
                    if (!listaVehiculo.isEmpty() && !listaVendedor.isEmpty()) {
                        System.out.println("LISTADO VENDEDORES");
                        gf.mostrarVendedoresArray(listaVendedor);
                        do {
                            System.out.println("Elige el vendedor que vende el vehiculo, introduzca un numero entre 0 " + (listaVendedor.size() - 1));
                            activo = gf.recuperarVendedorPorIndice(teclado.nextInt(), listaVendedor);
                            if (activo == null) {
                                System.out.println("Debes introducir un numero entre 0 y " + (listaVendedor.size() - 1));
                            }
                        } while (activo == null);
                        System.out.println("LISTADO VEHICULOS");
                        gf.mostrarVehiculosArray(listaVehiculo);
                        do {
                            System.out.println("Elige el vehiculo vendido por el vendedor" + activo.getNombre() + "  introduzca un numero entre 0 " + (listaVehiculo.size() - 1));
                            activoVehi = gf.recuperarVehiculoPorIndice(teclado.nextInt(), listaVehiculo);
                            if (activoVehi == null) {
                                System.out.println("Debes introducir un numero entre 0 y " + (listaVehiculo.size() - 1));
                            }
                        } while (activoVehi == null);
                        activo.realizarVenta(activoVehi);
                    } else {
                        System.out.println("Se deben cargar los objetos de los archivos primero, opcion 1");
                    }
                    break;

                case 4: {
                    try {
                        gf.actualizarBasesDeDatos(listaVehiculo, archivoVehiculo, archivoVendedor, listaVendedor);
                    } catch (IOException ex) {
                        Logger.getLogger(ConcesionarioFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }

        } while (opcion != 0);
    }

    public static void mostrarMenu() {
        System.out.println("1.-Cargar los objetos de los archivos");
        System.out.println("2.-Dar de alta vendedor/vehiculo");
        System.out.println("3.-Realizar venta de un vehiculo por un vendedor");
        System.out.println("4.-Actualizar base de datos y guardar cambios");
    }

}
