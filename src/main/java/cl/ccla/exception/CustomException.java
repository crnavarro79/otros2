package cl.ccla.exception;


public class CustomException extends Exception {

    /**
     * Serialización por defecto.
     */
    private static final long serialVersionUID = 1L;
    
    private String code;

    public CustomException(String message, String code) {
        super(message);
        this.code = code;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}
