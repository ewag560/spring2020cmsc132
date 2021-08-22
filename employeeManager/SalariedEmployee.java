package employeeManager;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//this class keeps track of the infornmation and processes of salaried employees
public class SalariedEmployee extends Employee {
	// public int sal;

	// constructor initializes a salaried employee with the necessary
	// information
	public SalariedEmployee(double sal, String fname, String lname) {
		// sal = salary;
		fName = fname;
		lName = lname;
		salary = sal / 26;
		revenue = sal / 26;
	}

	// overwritten method returns the salary constant for a salaried employee
	public double paycheck() {
		return salary;
	}
}
