package CarConnect;

public interface ICustomerService {
	Customer getCustomerID(int customerID);
	Customer getCustomerByUserName(String username);
	boolean registerCustomer(Customer customerData);
	boolean updateCustomer(Customer customerData);
	boolean deleteCustomer(int customerID);
}
