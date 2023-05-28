package edu.sv.proyecto.Entidades;

public class Entradas {

    private int id;
    private String NombreEntrada;
    private String DescripcionEntrada;
    private Double PrecioEntrada;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEntrada() {
        return NombreEntrada;
    }

    public void setNombreEntrada(String nombreEntrada) {
        NombreEntrada = nombreEntrada;
    }

    public String getDescripcionEntrada() {
        return DescripcionEntrada;
    }

    public void setDescripcionEntrada(String descripcionEntrada) {
        DescripcionEntrada = descripcionEntrada;
    }

    public Double getPrecioEntrada() {
        return PrecioEntrada;
    }

    public void setPrecioEntrada(Double precioEntrada) {
        PrecioEntrada = precioEntrada;
    }
}
