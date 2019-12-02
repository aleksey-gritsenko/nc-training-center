package ua.com.nc.nctrainingproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.AdminRightsDto;
import ua.com.nc.nctrainingproject.services.AdminRightsService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/adminrights")
public class AdminRightsController {


    private final AdminRightsService adminRightsService;

    @Autowired
    public AdminRightsController(AdminRightsService adminRightsService) {
        this.adminRightsService = adminRightsService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adminRightsService.getData());
    }
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public ResponseEntity<?> setAll(@RequestBody List<AdminRightsDto> list){
        adminRightsService.updateDb(list);
        return  ResponseEntity.ok("true");
    }

}
