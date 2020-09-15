package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Articulo;
import entidades.Categoria;
import entidades.Cliente;
import entidades.DetalleFactura;
import entidades.Domicilio;
import entidades.Factura;

 
public class PersistanceApp {
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceAppPU");
		
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			
			Factura factura1 = new Factura();
			
			factura1.setNumero(12);
			factura1.setFecha("23/02/2020");
			
			Domicilio domicilio1 = new Domicilio("san martin",1922);
			
			Cliente cliente1 = new Cliente("juan","torrente",18223445);
			cliente1.setDomicilio(domicilio1);
			domicilio1.setCliente(cliente1);
			
			factura1.setCliente(cliente1);

			Categoria perecederos = new Categoria("perecederos");
			Categoria lacteos = new Categoria("lacteos");
			Categoria limpieza = new Categoria("limpieza");
			
			Articulo articulo1 = new Articulo("yogurt ser sabor frutilla",20,200);
			Articulo articulo2 = new Articulo("detergente Magistral",50,300);
			
			articulo1.getCategoria().add(perecederos);
			articulo1.getCategoria().add(lacteos);
			
			lacteos.getArticulos().add(articulo1);
			perecederos.getArticulos().add(articulo1);
			
			articulo2.getCategoria().add(limpieza);
			limpieza.getArticulos().add(articulo2);
			
			DetalleFactura detalle1 = new DetalleFactura();
			
			detalle1.setArticulo(articulo1);
			detalle1.setCantidad(2);
			detalle1.setSubTotal(40);
			
			
			articulo1.getDetalle_factura().add(detalle1);
			factura1.getDetalles().add(detalle1);
			detalle1.setFactura(factura1);
			
			DetalleFactura detalle2 = new DetalleFactura();
			detalle2.setArticulo(articulo2);
			detalle2.setCantidad(2);
			detalle2.setSubTotal(80);
			
			articulo2.getDetalle_factura().add(detalle2);
			factura1.getDetalles().add(detalle2);
			detalle2.setFactura(factura1);
			
			
			factura1.setTotal(120);
			
			em.persist(factura1);
			
			
			
			em.flush();
			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			
		}
		
		em.close();
		emf.close();
		
		
	}

}
