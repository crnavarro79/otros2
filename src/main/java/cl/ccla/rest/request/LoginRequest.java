package cl.ccla.rest.request;

public class LoginRequest {
	
	private String usuario;
	
	private String password;
	
	private String token;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
    public String toString() {
        return "\nusuario=" + usuario + " \npassword=" + password + " \ntoken=" + token;
    }
	
	
}
