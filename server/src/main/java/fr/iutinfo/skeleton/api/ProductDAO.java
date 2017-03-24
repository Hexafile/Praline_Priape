package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ProductDAO {
	
	@SqlUpdate("create table products (id integer primary key autoincrement, name varchar(50) not null, description varchar(140), baseprice int, promotionalamout int, available boolean not null, imageurl varchar(100))")
	void createProductTable();
	
	@SqlUpdate("insert into products (name, description, baseprice, promotionalamount, available, imageurl) values(:name, :description, :basePrice, :promotionalAmount, :available, :imageURL)")
	@GetGeneratedKeys
	int insert(@BindBean() Product product);
	
	@SqlQuery("select * from products where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Product findById(@Bind("id") int id);
	
	@SqlQuery("select * from products where name = :name")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Product findByName(@Bind("name") String name);
	
	@SqlUpdate("update products set name=:name,description=:description,baseprice=:baseprice, promotionalamount=:promotionalamount, available=:available, imageurl=:imageurl where id = :id")
    void update(@BindBean() Product product);
	
	@SqlQuery("select * from products order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Product> all();
	
	@SqlUpdate("drop table if exists products")
	void dropProductTable();
	
	@SqlUpdate("delete from products where id = :id")
	void delete(@Bind("id") int id);
	
	void close();
	
}
