/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sportak
 */
public class Vehiculo implements Serializable {

    private String marca;
    private String modelo;
    private String matricula;
    private String potencia;
    private String color;
    private Integer precio;

    public Vehiculo(String marca, String modelo, String matricula, String potencia, String color, Integer precio) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.potencia = potencia;
        this.color = color;
        this.precio = precio;

    }

    Vehiculo() {
        super();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.marca);
        oos.writeObject(this.modelo);
        oos.writeObject(this.matricula);
        oos.writeObject(this.potencia);
        oos.writeObject(this.color);
        oos.writeObject(encriptarPrecio(this.precio));
        System.out.println("ESCRITO OBJETO CON INFORMACIÓN");
        System.out.println(this.toString());
        System.out.println("");
        System.out.println("ENCRIPTADO ****************************************");
        System.out.println(this.marca + "\t" + this.modelo + "\t" + this.matricula + "\t" + this.potencia + "\t" + this.color + "\t" + encriptarPrecio(this.precio));
        System.out.println("");
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.marca = (String) ois.readObject();
        this.modelo = (String) ois.readObject();
        this.matricula = (String) ois.readObject();
        this.potencia = (String) ois.readObject();
        this.color = (String) ois.readObject();
        String recuperacion = (String) ois.readObject();
        this.precio = desencriptarPrecio(recuperacion);
        System.out.println("LEIDO OBJETO CON INFORMACIÓN DESENCRIPTADA");
        System.out.println(this.toString());
        System.out.println("");
        
    }

    public String encriptarPrecio(Integer precio) {
        char[] caracteres = precio.toString().toCharArray();
        String res = "";
        for (int i = 0; i < caracteres.length; i++) {
            res = res + (int) caracteres[i];
        }
        //System.out.println(res);
        return res;

    }

    public Integer desencriptarPrecio(String precio) {
        ArrayList<String> precioTroceado = new ArrayList<>();
        int slow = 0;
        int fast = 2;
        String res = "";
        //separacion del string en trocidos decimales
        while (fast <= precio.length()) {

            //precioTroceado.add(precio.substring(slow, fast));
            res = res + (char) Integer.parseInt(precio.substring(slow, fast));
            slow += 2;
            fast += 2;
            //System.out.println(res);
        }
        //Traduccion
        return Integer.parseInt(res.trim());

    }

    @Override
    public String toString() {
        return "Vehiculo{" + "marca=" + marca + ", modelo=" + modelo + ", matricula=" + matricula + ", potencia=" + potencia + ", color=" + color + ", precio=" + precio + '}';
    }

}
