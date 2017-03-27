package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface MessageDao {

	@SqlUpdate("create table msgs(id integer primary key autoincrement, writerId int, date date, message varchar(140))")
	void createConversationTable();
	
	@SqlUpdate("insert into msgs(writerId, date, message) values(:idWriter, :date, :msg)")
	@GetGeneratedKeys
	int insert(@BindBean Message message);
	
	@SqlQuery("select * from msgs where id = :idConv")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Message findById(@Bind("idConv") int idConv);
	
	@SqlQuery("select * from msgs where writerId = :idWriter")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Message> findByWriterId(@Bind("idWriter") int idWriter);
	
	@SqlQuery("select * from msgs order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Message> all();
	
	@SqlUpdate("drop table if exists msgs")
	void dropConversationTable();
	
	@SqlUpdate("delete from msgs where id = :id")
	void delete(@Bind("id") int id);
	
	void close();
	
}
