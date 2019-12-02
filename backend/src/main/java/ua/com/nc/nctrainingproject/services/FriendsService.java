package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.FriendsPostgreDAO;

import java.util.List;

@Service

public class FriendsService {

    private final FriendsPostgreDAO friendsPostgreDAO;

    @Autowired
    public FriendsService(FriendsPostgreDAO friendsPostgreDAO) {
        this.friendsPostgreDAO = friendsPostgreDAO;
    }

    public void sendRequest(int sender, int reciever) {
        friendsPostgreDAO.sendRequest(sender, reciever);
    }

    public void aceptRequest(int sender, int reciever) {
        friendsPostgreDAO.acceptRequest(sender, reciever);
    }

    public List<User> getAllFriends(int user) {
        return friendsPostgreDAO.getAllFriends(user);
    }

    public List<User> getAllNewRequests(int user) {
        return friendsPostgreDAO.getAllNewRequests(user);
    }

    public boolean checkRequest(int sender,int reciever) {
        if(sender != reciever
                && friendsPostgreDAO.getSender(sender, reciever)== null

                && friendsPostgreDAO.getReciever(sender, reciever)== null){
            return true;
        }
        return false;
    }

}
