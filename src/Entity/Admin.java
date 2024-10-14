package Entity;

import java.util.Date;

public class Admin {
	private int adminID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private Date joinDate;

    // Default constructor
    public Admin() {}

    // Parameterized constructor
    public Admin(int adminID, String firstName, String lastName, String email,
                 String phoneNumber, String username, String password, String role, Date joinDate) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.joinDate = joinDate;
    }

    // Getters and Setters
    public int getAdminID() {
    	return adminID;
    }
    public void setAdminID(int adminID) {
    	this.adminID = adminID;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPhoneNumber() {
    	return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getRole() {
    	return role;
    }
    public void setRole(String role) {
    	this.role = role;
    }
    
    public Date getJoinDate() {
    	return joinDate;
    }
    public void setJoinDate(Date joinDate) {
    	this.joinDate = joinDate;
    }
    

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
