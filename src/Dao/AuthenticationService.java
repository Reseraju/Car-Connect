package Dao;

import Entity.Admin;
import Entity.Customer;
import Exception.AuthenticationException;

public class AuthenticationService {
	
	
	public boolean authenticateAdmin(Admin admin, String password) throws AuthenticationException {
        if (admin == null) {
            throw new AuthenticationException("Admin not found.");
        }

        if (!admin.authenticate(password)) {
            throw new AuthenticationException("Incorrect password for admin.");
        }
        return true;
    }
	
	public boolean authenticateCustomer(Customer customer, String password) throws AuthenticationException {
        if (customer == null) {
            throw new AuthenticationException("Customer not found.");
        }

        if (!customer.authenticate(password)) {
            throw new AuthenticationException("Incorrect password for customer.");
        }
        return true;
    }
}
