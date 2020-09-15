package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "articulo")
@Audited
public class Articulo implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "denominacion")
	private String denominacion;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precio")
	private int precio;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "arrticulo_categoria",
				joinColumns = @JoinColumn(name = "articulo_id"),
				inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categoria = new ArrayList<Categoria>();
	
	
	@OneToMany(mappedBy ="articulo",cascade = CascadeType.PERSIST)
	private List<DetalleFactura> detalle_factura = new ArrayList<DetalleFactura>();
	
	
	public Articulo() {
		
	}
	
	public Articulo(String denominacion, int cantidad, int precio) {
		
		this.denominacion = denominacion;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	public List<DetalleFactura> getDetalle_factura() {
		return detalle_factura;
	}

	public void setDetalle_factura(List<DetalleFactura> detalle_factura) {
		this.detalle_factura = detalle_factura;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	

}
