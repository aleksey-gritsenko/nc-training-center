package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.nc.nctrainingproject.models.AdminRightsDto;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.RightsPostgreDAO;
import ua.com.nc.nctrainingproject.services.AdminRightsService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/admin-rights")
public class AdminRightsController {

	private final AdminRightsService adminRightsService;
	private final RightsPostgreDAO rightsPostgreDAO;

	@Autowired
	public AdminRightsController(AdminRightsService adminRightsService, RightsPostgreDAO rightsPostgreDAO) {
		this.adminRightsService = adminRightsService;
		this.rightsPostgreDAO = rightsPostgreDAO;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(adminRightsService.getData());
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	public ResponseEntity<?> setAll(@RequestBody List<AdminRightsDto> list) {
		adminRightsService.updateDb(list);
		return ResponseEntity.ok("true");
	}

	@RequestMapping(value = "/get-all-descriptions", method = RequestMethod.GET)
	public ResponseEntity<?> getAllDescriptions() {
		return ResponseEntity.ok(rightsPostgreDAO.getAllDescriptions());
	}
}
