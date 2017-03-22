package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ProductDAO {
	
	@SqlUpdate("create table products (id integer primary key autoincrement, name varchar(50),  dealer varchar(50), description varchar(100), basePrice int, promotionalAmout int, available boolean, imageUrl varchar(50))")
	void createProductTable();
	
	@SqlUpdate("insert into products (name, dealer, description, basePrice, promotionalAmount, available, imageUrl) values(:name, :dealer, :description, :basePrice, :promotionalAmount, :available, :imageURL)")
	@GetGeneratedKeys
	int insert(@BindBean() Product product);
	
	@SqlQuery("select * from products where dealer = :dealer")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Product> findByDealer(@Bind("dealer") String dealer);
	
	@SqlQuery("select * from products where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Product findById(@Bind("id") int id);
	
	@SqlQuery("select * from products order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Product> all();
	
	@SqlUpdate("drop table if exists products")
	void dropProductTable();
	
	@SqlUpdate("delete from products where id = :id")
	void delete(@Bind int id);
	
	void close();
	
}
