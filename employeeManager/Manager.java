package employeeManager;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//this class manages the creating of company objects 
public class Manager {

	// This method creates and returns a paid company object.
	// It also makes sure that the name is not null.
	public static Company createAccount(String name) {
		if (name == null) {
			return null;
		}
		Company c = new Company();
		c.cname = name;
		c.isfree = false;
		return c;
	}

	// This method creates and returns a free company object
	// It also makes sure that the name is not null and maxEmployees is
	// positive.
	public static Company createAccount(String name, int maxEmployees) {
		if (name == null || maxEmployees <= 0) {
			return null;
		}
		Company c = new Company();
		c.cname = name;
		c.maxEmployees = maxEmployees;
		c.isfree = true;
		return c;
	}

}
