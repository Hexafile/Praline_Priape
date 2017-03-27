package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ConversationDao {
	
	@SqlUpdate("create table convs(id integer primary key autoincrement, demandeurId int, conseilleId int, termine boolean, note int)")
	void createConversationTable();
	
	@SqlUpdate("insert into convs(demandeurId, conseilleId, termine, note) values(:demandeurId, :conseilleId, :termine, :note)")
	@GetGeneratedKeys
	int insert(@BindBean Conversation conversation);
	
	@SqlQuery("select * from convs where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Conversation findById(@Bind("id") int id);
	
	@SqlQuery("select * from convs where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Conversation> findByDemandeurId(@Bind("id") int id);
	
	@SqlQuery("select * from convs order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Conversation> all();
	
	@SqlUpdate("drop table if exists convs")
	void dropConversationTable();
	
	@SqlUpdate("delete from convs where id = :id")
	void delete(@Bind("id") int id);
	
	void close();
}
