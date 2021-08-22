package employeeManager;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//this super class stores the information for all types of employees.
//This class contains constructors and methods to be overwritten by the the
//sub classes with class specific functions
public class Employee {

	// variable store employees name, commission rate, salary, revenue, sales,
	// hours and
	// index in the companies list of employees
	String fName, lName;
	double cRate, salary, revenue, sales;
	int hours, index;

	// default constructor to be overridden
	public Employee() {

	}

	// constructor initializes an employee with a first and last name
	public Employee(String fname, String lname) {
		fName = fname;
		lName = lname;
	}

	// constructor initializes an employee with a name and commission rate
	public Employee(String fname, String lname, double crate) {
		fName = fname;
		lName = lname;
		cRate = crate;
	}

	// constructor initializes an employee with a name and salary
	public Employee(double Salary, String fname, String lname) {
		fName = fname;
		lName = lname;
		salary = Salary;
	}

	// returns paycheck amount, to be overwritten with employee type specific
	// functions
	public double paycheck() {
		return revenue;
	}

	// method to be overwritten to allow exclusive function for commissioned
	// employees through an employee reference
	public void sell() {

	}
}
