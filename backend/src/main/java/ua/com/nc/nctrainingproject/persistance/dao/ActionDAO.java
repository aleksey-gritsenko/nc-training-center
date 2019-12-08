package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Action;

import java.util.List;

public interface ActionDAO {

	List<Action> getAllActions();

	Action getActionById(int actionId);

	Action getActionByUserId(int userId);

	Action getActionByActionTypeId(int actionTypeId);

	void deleteActionByActionId(int actionId);

	Action createAction(Action action);

	void updateAction(int actionId, Action action);
}
