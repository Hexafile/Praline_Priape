package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.List;

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

import fr.iutinfo.skeleton.common.dto.MessageDto;

@Path("/msg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	final static Logger logger=LoggerFactory.getLogger(SocieteResource.class);
	private static MessageDao dao=getDbi().open(MessageDao.class);
	
	public MessageResource() throws SQLException {
		if(!tableExist("msgs")) {
			logger.debug("Create table msgs");
			dao.createConversationTable();
			dao.insert(new Message(0,"lol"));
		}
	}
	
	@POST
	public MessageDto createMessage(MessageDto dto) {
		Message msg=new Message();
		msg.initFromDto(dto);
		int idConv=dao.insert(msg);
		dto.setIdConv(idConv);
		return dto;
	}
	
	@GET
	@Path("/{name}") 
	public MessageDto getMessage(@PathParam("writer") int id) {
		Message msg=dao.findById(id);
		if(msg==null)
			throw new WebApplicationException(404);
		return msg.convertToDto();
	}
	
	@GET
	public List<Message> all() {
		return dao.all();
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteMessage(@PathParam("id") int id) {
		dao.delete(id);
	}
}
