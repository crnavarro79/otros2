package cl.ccla.rest.response;

import java.util.List;

import cl.ccla.to.ProductoTO;

public class ProductoResponse extends DetalleStatus {

	private List<ProductoTO> productoTOs;

	public List<ProductoTO> getProductoTOs() {
		return productoTOs;
	}

	public void setProductoTOs(List<ProductoTO> productoTOs) {
		this.productoTOs = productoTOs;
	}

	@Override
	public String toString() {
		return "ProductoResponse [productoTOs=" + productoTOs + ", toString()=" + super.toString() + "]";
	}


}
