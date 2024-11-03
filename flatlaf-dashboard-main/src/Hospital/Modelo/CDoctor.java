package Hospital.Modelo;

import java.util.Date;

/**
 * @author Wilson
 */
public class CDoctor {
    private int id_Doctor;
    private String Nombre;
    private Date Fecha_Nacimiento;
    private String Telefono;
    private String Direccion;
    private byte [] Foto;
    private int Id_Usuario;
    private String Especialidad;

    public int getId_Doctor() {
        return id_Doctor;
    }

    public void setId_Doctor(int id_Doctor) {
        this.id_Doctor = id_Doctor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Date getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] Foto) {
        this.Foto = Foto;
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    public CDoctor(int id_Doctor, String Nombre, Date Fecha_Nacimiento, String Telefono, String Direccion, byte[] Foto, int Id_Usuario, String Especialidad) {
        this.id_Doctor = id_Doctor;
        this.Nombre = Nombre;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Foto = Foto;
        this.Id_Usuario = Id_Usuario;
        this.Especialidad = Especialidad;
    }
    
}
