package Dao;

import java.io.IOException;

import Entity.Customer;

public interface ICustomerService {
	Customer getCustomerByID(int customerID) throws IOException;
	Customer getCustomerByUserName(String username) throws IOException;
	boolean registerCustomer(Customer customerData) throws IOException;
	boolean updateCustomer(Customer customerData) throws IOException;
	boolean deleteCustomer(int customerID) throws IOException;
}
