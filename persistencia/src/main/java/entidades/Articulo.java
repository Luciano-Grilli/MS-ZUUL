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

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;



@Entity
@Table(name = "articulo")
@Data
@Builder
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
	@Default
	private List<DetalleFactura> detalle_factura = new ArrayList<DetalleFactura>();
	
	
	
	
	

}
