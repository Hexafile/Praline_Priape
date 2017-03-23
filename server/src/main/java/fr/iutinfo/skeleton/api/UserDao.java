package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table users (id integer primary key autoincrement, role integer, name varchar(20), surname varchar(20), alias varchar(20), adresse varchar(50), societe varchar(30), telephone integer, sexe integer, ptsfidelite integer, dateinscription date, datenaissance date, newsletter boolean, email varchar(100), passwdHash varchar(64), salt varchar(64), search varchar(1024))")
    void createUserTable();

    @SqlUpdate("insert into users (role,name,surname,alias,adresse,societe,telephone,sexe,ptsfidelite,dateinscription,datenaissance,newsletter,email,passwdHash,salt,search) values (:role,:name,:surname,:alias,:adresse,:societe,:tel,:sexe,:ptsFidelite,:dateInscription,:dateNaissance,:newsLetter,:email,:passwdHash,:salt,:search)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where name = :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByName(@Bind("name") String name);

    @SqlQuery("select * from users where search like :name")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);

    void close();
}
