package Dao;

import Entity.Customer;

public interface ICustomerService {
	Customer getCustomerByID(int customerID);
	Customer getCustomerByUserName(String username);
	boolean registerCustomer(Customer customerData);
	boolean updateCustomer(Customer customerData);
	boolean deleteCustomer(int customerID);
}
