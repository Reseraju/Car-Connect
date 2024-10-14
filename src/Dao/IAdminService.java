package Dao;

import Entity.Admin;

public interface IAdminService {
	Admin getAdminByID(int adminID);
	Admin getAdminByUsername(String username);
	boolean registerAdmin(Admin adminData);
	boolean updateAdmin(Admin adminData);
	boolean deleteAdmin(int adminID);
}
