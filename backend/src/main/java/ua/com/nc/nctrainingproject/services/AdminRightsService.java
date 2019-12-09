package ua.com.nc.nctrainingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.AdminRightsDto;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdminRightsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.RightsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRightsService {

    private final AdminRightsPostgreDAO adminRightsPostgreDAO;
    private final UserPostgreDAO userPostgreDAO;
    private final RightsPostgreDAO rightsPostgreDAO;
    //TODO rewrite to add possibility to add new rights
    private final int NUMBER_OF_RIGHTS = 3;
    private final String role = "moderator";


    @Autowired
    public AdminRightsService(AdminRightsPostgreDAO adminRightsPostgreDAO, UserPostgreDAO userPostgreDAO, RightsPostgreDAO rightsPostgreDAO) {
        this.adminRightsPostgreDAO = adminRightsPostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
        this.rightsPostgreDAO = rightsPostgreDAO;
    }

    public void updateDb(List<AdminRightsDto> data){

        for (AdminRightsDto dto: data){
            adminRightsPostgreDAO.deleteRightsByUserId(dto.getUser().getId());
            for (String description: dto.getRights()){
                adminRightsPostgreDAO.add(dto.getUser().getId(),rightsPostgreDAO.getRightIdByDescription(description).intValue());
            }

        }
    }

    public List<AdminRightsDto> getData(){
        List<User> adminsList = userPostgreDAO.getUsersByRole(role);
        List<AdminRightsDto> result = new ArrayList<>();
        for (User admin: adminsList){
            AdminRightsDto dto = new AdminRightsDto();
            dto.setUser(admin);
            List<String> rights = new ArrayList<>();
            List<Integer> actualRightsIds = adminRightsPostgreDAO.getRightsIdByUserId(admin.getId());
            for(Integer id: actualRightsIds){
                rights.add(rightsPostgreDAO.getDescriptionByRightId(id.intValue()));
            }
            dto.setRights(rights);
            result.add(dto);

        }
        return result;
    }
}
