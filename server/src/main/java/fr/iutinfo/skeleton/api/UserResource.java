package fr.iutinfo.skeleton.api;

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

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    final static Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = getDbi().open(UserDao.class);

    public UserResource() throws SQLException {
        if (!tableExist("users")) {
            logger.debug("Crate table users");
            dao.createUserTable();
            User admin = new User("Admin","Admin");
            admin.setRole(3);
            admin.setPassword("admin");
            dao.insert(admin);
        }
    }

    @POST
    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.initFromDto(dto);
        user.resetPasswordHash();
        int id = dao.insert(user);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/{id}")
    public UserDto getUser(@PathParam("id") int id) {
        User user = dao.findById(id);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        return user.convertToDto();
    }
    
    @GET
    @Path("/limit/{first}/{second}")
    public List<UserDto> getUser(@PathParam("first") int first,@PathParam("second") int second) {
        List<User> users;
        if (first == 0 && second ==0) {
            users = dao.all();
        }else{
        	users= dao.findFromTo(first, second);
        }
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }

    @GET
    public List<UserDto> getAllUsers(@QueryParam("q") String query) {
        List<User> users;
        if (query == null) {
            users = dao.all();
        } else {
            logger.debug("Search users with query: " + query);
            users = dao.search("%" + query + "%");
        }
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        dao.delete(id);
    }

}
