package Dao;

import java.io.IOException;

import Entity.Admin;

public interface IAdminService {
	Admin getAdminByID(int adminID) throws IOException;
	Admin getAdminByUsername(String username) throws IOException;
	boolean registerAdmin(Admin adminData) throws IOException;
	boolean updateAdmin(Admin adminData) throws IOException;
	boolean deleteAdmin(int adminID) throws IOException;
}
