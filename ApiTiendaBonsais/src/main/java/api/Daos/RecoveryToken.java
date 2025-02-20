package api.Daos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RecoveryToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String correo;
    private String token;
    private long expirationTime;
    
    
    
    
    public RecoveryToken() {
		super();
	}
	public RecoveryToken(Long id, String correo, String token, long expirationTime) {
		super();
		this.id = id;
		this.correo = correo;
		this.token = token;
		this.expirationTime = expirationTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

    
    // Getters and setters
}