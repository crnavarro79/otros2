package cl.ccla.rest.response;

public class DetalleStatus {
	
	private Status status;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DetalleStatus [status=" + status + ", toString()=" + super.toString() + "]";
	}

	
	
}
