package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.ActionType;

import java.util.List;

public interface ActionTypeDAO {

	List<ActionType> getAllActionTypes();

	ActionType getActionTypeByActionTypeId(int actionTypeId);

	ActionType getActionTypeByName(String name);

	void deleteByActionTypeId(int actionTypeId);

	void createActionType(ActionType actionType);

	void updateActionTypeById(int actionTypeId);
}
