package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Admin;

public interface AdministratorDAO {

    Admin getAdministratorByName(String name);

    void createAdministrator(Admin administrator);


}
