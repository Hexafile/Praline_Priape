package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	
	final static Logger logger=LoggerFactory.getLogger(ProductResource.class);
	
	private static ProductDAO dao=getDbi().open(ProductDAO.class);
	
	public ProductResource() throws SQLException {
		if(!tableExist("products")) {
			logger.debug("Create table products");
			dao.createProductTable();
			dao.insert(new Product(0, "nom0", 1, "description0"));
		}
	}
	
	@POST
	public ProductDto createProduct(ProductDto dto) {
		Product product=new Product();
		product.initFromDto(dto);
		int id=dao.insert(product);
		dto.setId(id);
		return dto;
	}
	
	@GET
	@Path("/{name}")
	public ProductDto getProduct(@PathParam("name") String name) {
		Product product=dao.findByName(name);
		if(product==null) 
			throw new WebApplicationException(404);
		return product.convertToDto();
	}
	
	@GET
	public List<Product> getAllProducts() {
		return dao.all();
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteProduct(@PathParam("id") int id) {
		dao.delete(id);
	}
	
	
}
