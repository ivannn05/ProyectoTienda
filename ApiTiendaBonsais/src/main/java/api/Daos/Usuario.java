package api.Daos;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios", schema = "tiendabonsai") // Asegúrate de que coincide con el esquema y nombre exacto
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "correo", length = 100, nullable = false, unique = true)
    private String correo;

    @Column(name = "direccion", length = 200, nullable = false)
    private String direccion;

    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "fecha_registro", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fechaRegistro;

    @Column(name = "rol", length = 50, nullable = false)
    private String rol; // Nueva columna rol

    @Column(name = "token", length = 255) // Columna para el token (VARCHAR)
    private String token;

    @Column(name = "fecha_token") // Columna para la fecha del token (TIMESTAMP)
    private Timestamp fechaToken;

    @Column(name = "telefono", length = 15) // Columna para el teléfono (VARCHAR)
    private String telefono;

    @Column(name = "foto_usu") // Columna para la foto de usuario (BYTEA)
    private byte[] fotoUsu;

    // Constructor vacío requerido por JPA
    public Usuario() {
    }

    // Constructor con parámetros (incluyendo los nuevos campos)
    public Usuario(String nombre, String apellidos, String correo, String direccion, String contrasena, 
                   Timestamp fechaRegistro, String rol, String token, Timestamp fechaToken, String telefono, byte[] fotoUsu) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
        this.token = token; // Inicializar el campo token
        this.fechaToken = fechaToken; // Inicializar el campo fecha_token
        this.telefono = telefono; // Inicializar el campo telefono
        this.fotoUsu = fotoUsu; // Inicializar el campo fotoUsu
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte[] getFotoUsu() {
        return fotoUsu;
    }

    public void setFotoUsu(byte[] fotoUsu) {
        this.fotoUsu = fotoUsu;
    }

    // Método toString (opcional)
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
                ", token='" + token + '\'' + // Mostrar el token
                ", fechaToken=" + fechaToken +
                ", telefono='" + telefono + '\'' + // Mostrar el teléfono
                ", fotoUsu=" + (fotoUsu != null ? fotoUsu.length : 0) + " bytes" + // Mostrar el tamaño de la foto
                '}';
    }
}
