package api.Daos;

public class EmailTemplate {
	
	private String templateCorreo;
	private String templateNombre;
	
	public EmailTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailTemplate(String templateCorreo, String templateNombre) {
		super();
		this.templateCorreo = templateCorreo;
		this.templateNombre = templateNombre;
	}

	public String getTemplateCorreo() {
		return templateCorreo;
	}
	public void setTemplateCorreo(String templateCorreo) {
		this.templateCorreo = templateCorreo;
	}
	public String getTemplateNombre() {
		return templateNombre;
	}
	public void setTemplateNombre(String templateNombre) {
		this.templateNombre = templateNombre;
	}

}
