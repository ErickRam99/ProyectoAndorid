package edu.sv.proyecto.Entidades;

public class Promociones {

    private int id;
    private String NombrePromocion;
    private String DescripcionPromocion;
    private Double PrecioPromo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePromocion() {
        return NombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        NombrePromocion = nombrePromocion;
    }

    public String getDescripcionPromocion() {
        return DescripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        DescripcionPromocion = descripcionPromocion;
    }

    public Double getPrecio() {
        return PrecioPromo;
    }

    public void setPrecio(Double precioPromo) {
        PrecioPromo = precioPromo;
    }


}
