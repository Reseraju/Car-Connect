package CarConnect;

public class AuthenticationService {
	
	public boolean authenticateAdmin(Admin admin, String password) {
		return admin.authenticate(password);
	}
	
	public boolean autenticateCustomer(Customer customer, String password) {
		return customer.authenticate(password);
	}
}
