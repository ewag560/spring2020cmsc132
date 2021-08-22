package employeeManager;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//this class keeps track of the information and implements the functions 
//exclusive to commissioned employees
public class CommissionedEmployee extends Employee {

	// variable keeps track of commission rate;
	double r;

	// method returns the proper paycheck amount for a commissioned employee
	public double paycheck() {
		return (r / 100) * revenue;
	}

	// constructor initializes a commissioned employee and its information
	public CommissionedEmployee(String fname, String lname, double rate) {
		fName = fname;
		lName = lname;
		revenue = sales * rate;
		r = rate;
	}

	// overwritten method processes the value of sales for commissioned
	// employees
	public void sell(int amt) {
		revenue += amt;
	}
}
