package cl.ccla.to;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class RecargaTPPRequest {

    @XmlElement(name = "token", required = true)
    private String token;
    @XmlElement(name = "hash", required = true)
    private String hash;
    @XmlElement(name = "numero_documento", required = true)
    private String numeroDocumento;
    @XmlElement(name = "numero_contrato", required = true)
    private int numeroContrato;
    @XmlElement(name = "monto", required = true)
    private int monto;
    @XmlElement(name = "tipo", required = true)
    private String tipo;
    @XmlElement(name = "canal", required = true)
    private String canal;
    @XmlElement(name = "fechaTransaccion", required = true)
    private Date fechaTransaccion;
    @XmlElement(name = "nombre_usuario", required = true)
    private String nombreUsuario;
    @XmlElement(name = "id_transaccion", required = true)
    private String idTransaccion;
    @XmlElement(name = "email_destino", required = true)
    private String emailDestino;
    @XmlElement(name = "sistema", required = true)
    private String sistema;
    
    public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

     public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public int getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(int numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	@Override
	public String toString() {
		return "\ntoken=" + token + " \nhash=" + hash + " \nnumeroDocumento=" + numeroDocumento + " \nnumeroContrato=" + numeroContrato
				+ " \nmonto=" + monto + " \ntipo=" + tipo + " \ncanal=" + canal + " \nFecha Transaccion=" + fechaTransaccion + " \nnombreUsuario="
				+ nombreUsuario + " \nidTransaccion=" + idTransaccion + " \nemailDestino=" + emailDestino +" \nsistema=" + sistema+ "\n";
	}
   

}
