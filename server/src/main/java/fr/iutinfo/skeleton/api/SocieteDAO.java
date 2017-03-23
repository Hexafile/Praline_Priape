package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface SocieteDAO {
	
	@SqlUpdate("create table societes(sno integer primary key autoincrement, nom varchar(50), numSiret int, adresse varchar(50), tel int, mail varchar(30), methodeLivraison varchar(50), livreur varchar(50)")
	void createSocieteTable();
	
	@SqlUpdate("insert into Societe(nom, numSiret, adresse, tel, mail, methodeLivraison, livreur")
	@GetGeneratedKeys
	int insert(@BindBean() Societe societe);
	
	@SqlQuery("select * from  societes where sno = :sno")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Societe findBySno(@Bind("sno") int sno);
	
	@SqlQuery("select * from societes order by sno")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Societe> all();
	
	@SqlUpdate("drop table if exists societes")
	void dropSocieteTable();
	
	@SqlUpdate("delete from societes where sno = :sno")
	void delete(@Bind int sno);
	
	void close();
	
}
