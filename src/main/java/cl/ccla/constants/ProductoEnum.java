package cl.ccla.constants;

public enum ProductoEnum {

	LIC_MEDICA(2, "Licencia Medica"), CREDITO(1, "Credito"), BENEFICIOS(3, "Beneficio"), AHORRO(4, "Ahorro");

	private Integer codigo;
	private String descripcion;

	ProductoEnum(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
