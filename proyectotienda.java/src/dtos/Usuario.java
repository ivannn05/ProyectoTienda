package dtos;

import java.sql.Timestamp;

public class Usuario {

    private Long id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String direccion;
	private String telefono;
    private String contrasena;
    private Timestamp fechaRegistro;
    private String rol;
    private String token; // Nuevo campo para el token
    private Timestamp fechaToken; // Nuevo campo para la fecha de caducidad del token

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros, incluyendo los nuevos campos token y fechaToken
    public Usuario(Long id, String nombre, String apellidos, String correo,String telefono, String direccion, String contrasena,
                   Timestamp fechaRegistro, String rol, String token, Timestamp fechaToken) {
        this.id = id;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.token = token;
        this.fechaToken = fechaToken;
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
    public String getTelefono() {
  		return telefono;
  	}

  	public void setTelefono(String telefono) {
  		this.telefono = telefono;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getFechaToken() {
        return fechaToken;
    }

    public void setFechaToken(Timestamp fechaToken) {
        this.fechaToken = fechaToken;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                 ", telefono='" + telefono + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", rol='" + rol + '\'' +
                ", token='" + token + '\'' + // Mostrar el token
                ", fechaToken=" + fechaToken +
                '}';
    }
}
