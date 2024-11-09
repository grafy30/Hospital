  package Hospital.Modelo;

import java.util.Date;

/**
 * @author Wilson
 */
public class CDoctor {
    public int id_Doctor;
    public String Nombre;
    public Date Fecha_Nacimiento;
    public String Telefono;
    public String Direccion;
    public byte [] Foto;
    public int Id_Usuario;
    public String Especialidad;

    public CDoctor(){
        
    }
            
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

    public CDoctor(int id_Doctor, String Nombre, Date Fecha_Nacimiento, String Telefono, String Direccion, int Id_Usuario, String Especialidad, byte[] Foto) {
        this.id_Doctor = id_Doctor;
        this.Nombre = Nombre;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Id_Usuario = Id_Usuario;
        this.Especialidad = Especialidad;
        this.Foto = Foto;
    }
    
}
