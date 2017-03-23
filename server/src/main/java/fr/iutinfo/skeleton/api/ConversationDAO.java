package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ConversationDAO {
	
	@SqlUpdate("create table conversations(id integer autoincrement primary key, demandeurId int, conseilleId int, termine boolean, note int)")
	void createConversationTable();
	
	@SqlUpdate("insert into conversations(demandeurId, conseilleId, termine, note) values(:demandeurId, :conseilleId, :termine, :note)")
	@GetGeneratedKeys
	int insert();
	
	@SqlQuery("select * from conversations where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Conversation findById(@Bind("id") int id);
	
	@SqlQuery("select * from conversations where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Conversation> findByDemandeurId(@Bind("id") int id);
	
	@SqlQuery("select * from conversations order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Product> all();
	
	@SqlUpdate("drop table if exists conversations")
	void dropConversationTable();
	
	@SqlUpdate("delete from conversations where id = :id")
	void delete(@Bind("id") int id);
	
	void close();
	

}
