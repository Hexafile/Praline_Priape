package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.SocieteDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/societe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SocieteResource {
	
	final static Logger logger=LoggerFactory.getLogger(SocieteResource.class);
	private static SocieteDao dao=getDbi().open(SocieteDao.class);
	
	public SocieteResource() throws SQLException {
		if(!tableExist("societes")) {
			logger.debug("Create table societes");
			dao.createSocieteTable();
			dao.insert(new Societe(0,"Coca-Cola"));
		}
	}
	
	@POST
	public SocieteDto createSociete(SocieteDto dto) {
		Societe societe=new Societe();
		societe.initFromDto(dto);
		int sno=dao.insert(societe);
		dto.setSno(sno);
		return dto;
	}
	
	@GET
	@Path("/{name}") 
	public SocieteDto getSociete(@PathParam("name") String name) {
		Societe societe=dao.findByName(name);
		if(societe==null)
			throw new WebApplicationException(404);
		return societe.convertToDto();
	}
	
	@GET
	public List<Societe> all() {
		return dao.all();
	}
	
	@DELETE
	@Path("/{sno}")
	public void deleteSociete(@PathParam("sno") int sno) {
		dao.delete(sno);
	}
}
