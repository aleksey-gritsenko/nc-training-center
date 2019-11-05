package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.nc.nctrainingproject.services.PasswordRecoverService;

@Controller
public class PasswordRecoverController {
    private final PasswordRecoverService passwordRecoverService;

    @Autowired
    public PasswordRecoverController(PasswordRecoverService passwordRecoverService) {
        this.passwordRecoverService = passwordRecoverService;
    }

    @RequestMapping("/email")
    @ResponseBody
    public void emailSender(@RequestParam String email,@RequestParam String userName) {
        try {
            passwordRecoverService.makeEmail(email,userName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/userrecover")
    @ResponseBody
    public boolean passwordRecoverUser(@RequestParam String code,
                                       @RequestParam String userName,
                                       @RequestParam String newPassword) {
    return passwordRecoverService.passwordRecover(code,newPassword,userName);
    }

    @RequestMapping("/adminrecover")
    @ResponseBody
    public boolean passwordRecoverAdmin(@RequestParam String code,
                                        @RequestParam String AdminName,
                                        @RequestParam String newPassword) {
         return passwordRecoverService.passwordRecoverAdmin(code,newPassword,AdminName);
    }


}
