package androiddeveloper.eder.padilla.hackcentrofox.model;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class VenderProducto {

    String id;

    String estado;

    String imagen;

    String fechaDeCaducidad;

    String categoria;

    String nombre;

    String costo;

    String price;

    String lugar;

    String tipoDePaqueteria;

    String lugarDeDestino;

    String fechaDeEntrega;

    public VenderProducto(String id, String estado, String imagen, String fechaDeCaducidad, String categoria, String nombre, String costo, String price, String lugar, String tipoDePaqueteria, String lugarDeDestino, String fechaDeEntrega) {
        this.id = id;
        this.estado = estado;
        this.imagen = imagen;
        this.fechaDeCaducidad = fechaDeCaducidad;
        this.categoria = categoria;
        this.nombre = nombre;
        this.costo = costo;
        this.price = price;
        this.lugar = lugar;
        this.tipoDePaqueteria = tipoDePaqueteria;
        this.lugarDeDestino = lugarDeDestino;
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public String getTipoDePaqueteria() {
        return tipoDePaqueteria;
    }

    public void setTipoDePaqueteria(String tipoDePaqueteria) {
        this.tipoDePaqueteria = tipoDePaqueteria;
    }

    public String getLugarDeDestino() {
        return lugarDeDestino;
    }

    public void setLugarDeDestino(String lugarDeDestino) {
        this.lugarDeDestino = lugarDeDestino;
    }

    public VenderProducto() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFechaDeCaducidad(String fechaDeCaducidad) {
        this.fechaDeCaducidad = fechaDeCaducidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFechaDeCaducidad() {
        return fechaDeCaducidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCosto() {
        return costo;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    @Override
    public String toString() {
        return "VenderProducto{" +
                "id='" + id + '\'' +
                ", estado='" + estado + '\'' +
                ", imagen='" + imagen + '\'' +
                ", fechaDeCaducidad='" + fechaDeCaducidad + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", costo='" + costo + '\'' +
                ", price='" + price + '\'' +
                ", lugar='" + lugar + '\'' +
                ", tipoDePaqueteria='" + tipoDePaqueteria + '\'' +
                ", lugarDeDestino='" + lugarDeDestino + '\'' +
                ", fechaDeEntrega='" + fechaDeEntrega + '\'' +
                '}';
    }
}
