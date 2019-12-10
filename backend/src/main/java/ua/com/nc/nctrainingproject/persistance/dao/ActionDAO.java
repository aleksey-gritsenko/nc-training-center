package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Action;

import java.util.List;

public interface ActionDAO {

	List<Action> getAllActions();

	List<Action> getAllActionsByUserIdAndActionTypeId(int userId, int actionTypeId);

	Action getActionById(int actionId);

	Action getActionByUserId(int userId);

	Action getActionByUserAndTypeId(int userId, int actionTypeId);

	Action getActionByActionTypeId(int actionTypeId);

	void deleteActionByActionId(int actionId);

	Action createAction(Action action);

	void updateAction(int actionId, Action action);
}
