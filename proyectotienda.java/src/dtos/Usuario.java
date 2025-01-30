package dtos;

import java.sql.Timestamp;

public class Usuario {

    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String direccion;
    private String contrasena; 
    private Timestamp fechaRegistro;
    private String rol;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(Long id, String nombre, String apellidos, String correo, String direccion, String contrasena, Timestamp fechaRegistro, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasena() {
        return contrasena; 
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", contrasena='" + contrasena + '\'' + 
                ", fechaRegistro=" + fechaRegistro +
                ", rol='" + rol + '\'' +
                '}';
    }
}
