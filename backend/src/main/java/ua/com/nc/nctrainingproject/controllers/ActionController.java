package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Action;
import ua.com.nc.nctrainingproject.services.ActionService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/action")
public class ActionController {

	private final ActionService actionService;

	@Autowired
	public ActionController(ActionService actionService) {
		this.actionService = actionService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAllActions() {
		return ResponseEntity.ok(actionService.getAllActions());
	}

	@RequestMapping(value = "/addNewAction", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addNewAction(@RequestParam(name = "userId") int userId,
										  @RequestParam(name = "actionTypeId") int actionTypeId) {
		Action response = actionService.addNewAction(userId, actionTypeId);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createAction(@RequestParam(name = "userId") int userId,
										  @RequestParam(name = "actionTypeId") int actionTypeId) {
		Action response = actionService.createAction(new Action(userId, actionTypeId));
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void deleteActionType(@RequestParam(name = "actionId") int actionId) {
		actionService.deleteActionByActionId(actionId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Action updateAction(@RequestParam(name = "actionId") int actionId,
							   @RequestParam(name = "userId") int userId,
							   @RequestParam(name = "actionTypeId") int actionTypeId) {

		Action action = new Action(actionId, userId, actionTypeId);
		actionService.updateAction(actionId, action);
		return action;
	}
}
