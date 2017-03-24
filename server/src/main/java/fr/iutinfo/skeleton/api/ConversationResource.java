package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.ConversationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
	
	final static Logger logger=LoggerFactory.getLogger(ConversationResource.class);
	
	private static ConversationDAO dao=getDbi().open(ConversationDAO.class);
	
	public ConversationResource() throws SQLException {
		if(!tableExist("conversation")) {
			logger.debug("Create table conversations");
			dao.createConversationTable();
		}
	}
	
	@POST
	public ConversationDto createConversation(ConversationDto dto) {
		Conversation conversation=new Conversation();
		conversation.initFromDto(dto);
		int id=dao.insert(conversation);
		dto.setId(id);
		return dto;
	}
	
	@GET
	public List<ConversationDto> getAllConversations() {
		return dao.all().stream().map(Conversation::convertToDto).collect(Collectors.toList());
	}
	
	@GET
	@Path("/{demandeurId}")
	public List<ConversationDto> getConversationDemandeur(@PathParam("demandeurId") int id) {
		return dao.findByDemandeurId(id).stream().map(Conversation::convertToDto).collect(Collectors.toList());
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteConversation(@PathParam("id") int id) {
		dao.delete(id);
	}

}
