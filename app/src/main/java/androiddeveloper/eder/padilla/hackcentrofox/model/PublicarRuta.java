package androiddeveloper.eder.padilla.hackcentrofox.model;

/**
 * Created by ederpadilla on 20/05/17.
 */

public class PublicarRuta {

    String id;

    String direccionSalida;

    String direccionLlegada;

    String fechaSalida;

    String fechaLlegada;

    String horaSalida;

    String horaLlegada;

    String espacio;

    String costo;

    String estado;

    public PublicarRuta() {
    }

    public PublicarRuta(String id, String direccionSalida, String direccionLlegada, String fechaSalida, String fechaLlegada, String horaSalida, String horaLlegada, String espacio, String costo, String estado) {
        this.id = id;
        this.direccionSalida = direccionSalida;
        this.direccionLlegada = direccionLlegada;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.espacio = espacio;
        this.costo = costo;
        this.estado = estado;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDireccionSalida(String direccionSalida) {
        this.direccionSalida = direccionSalida;
    }

    public void setDireccionLlegada(String direccionLlegada) {
        this.direccionLlegada = direccionLlegada;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getDireccionSalida() {
        return direccionSalida;
    }

    public String getDireccionLlegada() {
        return direccionLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getEspacio() {
        return espacio;
    }

    public String getCosto() {
        return costo;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "PublicarRuta{" +
                "id='" + id + '\'' +
                ", direccionSalida='" + direccionSalida + '\'' +
                ", direccionLlegada='" + direccionLlegada + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", fechaLlegada='" + fechaLlegada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", horaLlegada='" + horaLlegada + '\'' +
                ", espacio='" + espacio + '\'' +
                ", costo='" + costo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
