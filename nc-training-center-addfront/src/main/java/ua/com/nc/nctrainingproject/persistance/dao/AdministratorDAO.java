package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Admin;

public interface AdministratorDAO {

    public Admin getAdministratorByName(String name);

    public void createAdministrator(Admin administrator);


}
