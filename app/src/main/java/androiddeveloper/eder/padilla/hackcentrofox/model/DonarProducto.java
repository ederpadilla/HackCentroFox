package androiddeveloper.eder.padilla.hackcentrofox.model;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class DonarProducto {

    String organizacion;

    String donacion;

    String concepto;

    String fechaDeEntrega;

    String tipoDePaqueteria;

    String id;

    public DonarProducto() {
    }

    public DonarProducto(String organizacion, String donacion, String concepto, String fechaDeEntrega, String tipoDePaqueteria, String id) {
        this.organizacion = organizacion;
        this.donacion = donacion;
        this.concepto = concepto;
        this.fechaDeEntrega = fechaDeEntrega;
        this.tipoDePaqueteria = tipoDePaqueteria;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getDonacion() {
        return donacion;
    }

    public void setDonacion(String donacion) {
        this.donacion = donacion;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public String getTipoDePaqueteria() {
        return tipoDePaqueteria;
    }

    public void setTipoDePaqueteria(String tipoDePaqueteria) {
        this.tipoDePaqueteria = tipoDePaqueteria;
    }

    @Override
    public String toString() {
        return "DonarProducto{" +
                "organizacion='" + organizacion + '\'' +
                ", donacion='" + donacion + '\'' +
                ", concepto='" + concepto + '\'' +
                ", fechaDeEntrega='" + fechaDeEntrega + '\'' +
                ", tipoDePaqueteria='" + tipoDePaqueteria + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
