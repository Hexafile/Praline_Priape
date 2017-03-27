package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.ProductDto;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
	final static Logger logger=LoggerFactory.getLogger(ProductResource.class);
	private static ProductDao dao=getDbi().open(ProductDao.class);
	private static FournitDao daoFournit=getDbi().open(FournitDao.class);
	private static SocieteDao daoSociete=getDbi().open(SocieteDao.class);
	
	public ProductResource() throws SQLException {
		if(!tableExist("products")) {
			logger.debug("Create table products");
			dao.createProductTable();
			dao.insert(new Product(0, "nom0", 2));
		}
		if(!tableExist("fournit")) {
			logger.debug("Create table fournit");
			daoFournit.createFournitTable();
			daoFournit.insert(new Fournit(0, 2));
		}
	}
	
	//Création comporte l'Id de la société pour Fournit
	@POST
	public ProductDto createProduct(ProductDto dto) {
		Product product=new Product();
		product.initFromDto(dto);
		int pno=dao.insert(product);
		dto.setId(pno);
		Fournit fournit=new Fournit(pno,dto.getTmpSociete());
		daoFournit.insert(fournit);
		return dto;
	}
	
	@GET
	@Path("/{id}")
	public ProductDto getProduct(@PathParam("id") int id) {
		Product product=dao.findById(id);
		if(product==null) 
			throw new WebApplicationException(404);
		List<Fournit> fournit = daoFournit.findBySociete(product.getId());
		List<String> societes = new ArrayList<>();
		for (Fournit f : fournit) {
			societes.add(daoSociete.findBySno(f.getSno()).getName()); 
		}
		product.setDealer(societes);
		return product.convertToDto();
	}
	
	@GET
	public List<ProductDto> getAllProducts() {
		return dao.all().stream().map(Product::convertToDto).collect(Collectors.toList());
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteProduct(@PathParam("id") int id) {
		dao.delete(id);
	}
	
	
}
