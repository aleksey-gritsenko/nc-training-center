package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.models.ActionType;
import ua.com.nc.nctrainingproject.services.ActionService;
import ua.com.nc.nctrainingproject.services.ActionTypeService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/action")
public class ActionController {


	private final ActionTypeService actionTypeService;
	private final ActionService actionService;

	@Autowired
	public ActionController(ActionTypeService actionTypeService, ActionService actionService) {
		this.actionTypeService = actionTypeService;
		this.actionService = actionService;
	}

	@RequestMapping(value = "/addNewAction", method = RequestMethod.POST)
	public ResponseEntity<Action> addNewAction(@RequestParam(name = "userId") int userId,
											   @RequestParam(name = "actionTypeId") int actionTypeId) {
		Action response = actionService.addNewAction(userId, actionTypeId);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Action> createAction(@RequestBody Action action) {
		Action response = actionService.createAction(action);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/all-types", method = RequestMethod.GET)
	public List<ActionType> getAllActionTypes() {
		return actionTypeService.getAllActionTypes();
	}
}
