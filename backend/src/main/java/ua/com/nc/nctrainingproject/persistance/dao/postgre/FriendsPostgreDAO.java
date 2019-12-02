package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FriendQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

import java.util.ArrayList;
import java.util.List;
@Repository

public class FriendsPostgreDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FriendsPostgreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void sendRequest(int sender, int reciever) {
        jdbcTemplate.update(FriendQuery.SEND_REQUEST, sender,reciever,false);
    }

    public void acceptRequest(int sender, int reciever) {
        jdbcTemplate.update(FriendQuery.ACCEPT_REQUEST,true,sender,reciever);
    }
    public List<User> getAllFriends(int user){
        return jdbcTemplate.query(FriendQuery.GET_ALL_FRIENDS,new UserRowMapper(),true,user,true,user);
    }
    public  List<User>  getAllNewRequests(int user){
        return jdbcTemplate.query(FriendQuery.GET_ALL_NEW_REQUESTS,new UserRowMapper() ,false,user);
    }
    public List <User> getReciever(int sender,int reciever){
        return jdbcTemplate.query(FriendQuery.GET_RECIEVER,new UserRowMapper(),sender,reciever
        );
    }
    public List <User> getSender(int sender,int reciever){
        return jdbcTemplate.query(FriendQuery.GET_SENDER,new UserRowMapper(),sender,reciever
        );
    }
}
