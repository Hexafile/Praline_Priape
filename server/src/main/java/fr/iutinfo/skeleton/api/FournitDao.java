package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface FournitDao {
	
	@SqlUpdate("create table fournit (idproduct integer primary key not null, idsociete integer primary key not null, constraint fkidp foreign key(idproduct) references products(id) on delete cascade, constraint fksno foreign key(idsociete) references societe(id) on delete cascade)")
	void createFournitTable();
	
	@SqlUpdate("insert into fournit values(:pno,:sno)")
	@GetGeneratedKeys
	int insert(@BindBean() Fournit f);
	
	@SqlQuery("select * from fournit where idsociete = :sno")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Fournit> findByProduct(@Bind("sno") int sno);
	
	@SqlQuery("select * from fournit where idproduct = :pno")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Fournit> findBySociete(@Bind("pno") int pno);
	
	@SqlQuery("select * from fournit order by idproduct")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Fournit> all();
	
	@SqlUpdate("drop table if exists fournit")
	void dropFournitTable();
	
	@SqlUpdate("delete from fournit where idproduct = :pno")
	void deleteByProduct(@Bind int pno);
	
	@SqlUpdate("delete from fournit where idsociete = :sno")
	void deleteBySociete(@Bind int sno);
	
	void close();
	
}
