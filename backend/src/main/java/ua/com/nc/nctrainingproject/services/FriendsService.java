package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.FriendsPostgreDAO;

import java.util.List;

@Service

public class FriendsService {

	private final FriendsPostgreDAO friendsPostgreDAO;
	private final ActionService actionService;

	@Autowired
	public FriendsService(FriendsPostgreDAO friendsPostgreDAO, ActionService actionService) {
		this.friendsPostgreDAO = friendsPostgreDAO;
		this.actionService = actionService;
	}

	public void sendRequest(int sender, int reciever) {
		friendsPostgreDAO.sendRequest(sender, reciever);
	}

	public void acceptRequest(int sender, int reciever) {
		actionService.addNewAction(reciever, 1);
		actionService.addNewAction(sender, 1);
		friendsPostgreDAO.acceptRequest(sender, reciever);
	}

	public void rejectRequest(int sender, int reciever) {
		friendsPostgreDAO.rejectRequest(sender, reciever);
	}

	public List<User> getAllFriends(int user) {
		return friendsPostgreDAO.getAllFriends(user);
	}

	public List<User> getAllNewRequests(int user) {
		return friendsPostgreDAO.getAllNewRequests(user);
	}

	public boolean checkRequest(int sender, int reciever) {

		return sender != reciever
				& friendsPostgreDAO.getCountApplications(sender, reciever) == 0;
	}

}
