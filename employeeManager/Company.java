package employeeManager;

//Eric Wagner 116484695 ew046 0202
//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//This class manages the functions of company objects by managing Employee objects.
// This class also processes the employees sales and revenue for given pay periods
public class Company {

	// variables keep track of a companies name, employee capacity, list of the
	// companies employees, and if it is a free company
	String cname;
	int maxEmployees = -1;
	Employee[] es = new Employee[0];
	boolean isfree;

	// method returns the name of the company
	public String getCompanyName() {
		return cname;
	}

	// method checks to see if the given name exists in the list of employees
	// and returns true if it does
	public boolean isValidEmployee(String fName, String lName) {
		for (int x = 0; x < es.length; x++) {
			if (fName == es[x].fName && lName == es[x].lName) {
				es[x].index = x;
				return true;
			}
		}
		return false;
	}

	// Method attempts to create a commissioned employee and add them to the
	// list for the company. Method returns false if the parameters are
	// unacceptable, true otherwise.
	public boolean hireCommissionedEmployee(String firstName, String lastName,
			double commissionRate) {

		if (firstName == "" || lastName == "" || commissionRate < 0
				|| commissionRate > 100) {
			return false;
		}

		Employee e = new CommissionedEmployee(firstName, lastName,
				commissionRate);
		Employee[] newes = new Employee[es.length + 1];
		for (int i = 0; i < es.length; i++) {
			newes[i] = es[i];
		}
		newes[es.length] = e;

		if (isValidEmployee(firstName, lastName) == true) {
			return false;
		}

		es = newes;
		return true;
	}

	// Method attempts to create a salaried employee and add them to the
	// list for the company. Method returns false if the parameters are
	// unacceptable, true otherwise.
	public boolean hireSalariedEmployee(String firstName, String lastName,
			double yearlySalary) {
		if (firstName == "" || lastName == "" || yearlySalary <= 0) {
			return false;
		}

		Employee e = new SalariedEmployee(yearlySalary, firstName, lastName);
		Employee[] newes = new Employee[es.length + 1];
		for (int i = 0; i < es.length; i++) {
			newes[i] = es[i];
		}

		newes[es.length] = e;
		if (maxEmployees == -1) {
			maxEmployees = Integer.MAX_VALUE;
		}
		if (isValidEmployee(firstName, lastName) == true
				|| es.length >= maxEmployees) {
			return false;
		}
		es = newes;
		return true;
	}

	// returns the number of hired employees
	public int numEmployees() {
		return es.length;
	}

	// returns the maximum capacity of employees for the company
	public int employeeCapacity() {
		if (maxEmployees == -1) {
			return Integer.MAX_VALUE;
		}
		return maxEmployees;
	}

	// This method returns the names of all of the employees hired by the
	// company in alphabetical order. The names are sorted alphabetically
	// through bubble sort and compared with compareTo
	public String[] getEmployeeNames() {
		Employee E = new Employee();
		String[] s = new String[es.length];
		if (es.length == 0) {
			String[] emp = new String[0];
			return emp;
		}
		Employee[] nams = new Employee[es.length];

		// loop creates a new array of the employees
		for (int i = 0; i < es.length; i++) {
			nams[i] = new Employee(es[i].fName, es[i].lName);
		}

		// loops compare each employee to every employee after it
		for (int i = 0; i < es.length; i++) {
			for (int oi = i + 1; oi < es.length; oi++) {

				// last names are compared first
				if (nams[i].lName.compareTo(nams[oi].lName) > 0) {
					E.fName = nams[i].fName;
					E.lName = nams[i].lName;
					nams[i].fName = nams[oi].fName;
					nams[i].lName = nams[oi].lName;
					nams[oi].fName = E.fName;
					nams[oi].lName = E.lName;
				}

				// first names are compared second
				if (nams[i].lName.compareTo(nams[oi].lName) == 0
						&& nams[i].fName.compareTo(nams[oi].fName) > 0) {
					E.fName = nams[i].fName;
					E.lName = nams[i].lName;
					nams[i].fName = nams[oi].fName;
					nams[i].lName = nams[oi].lName;
					nams[oi].fName = E.fName;
					nams[oi].lName = E.lName;
					E = es[oi];
					es[oi] = es[i];
					s[i] = E.fName + " " + E.lName;
				}
			}
		}
		for (int i = 0; i < es.length; i++) {
			s[i] = nams[i].fName + " " + nams[i].lName;
		}

		return s;
	}

	// method adds hours worked to a given employees total if they meet the
	// requirements, returning true, false if they don't meet the requirments.
	public boolean workHours(String firstName, String lastName, int numHours) {
		int index = -1;

		// loop finds the index of the employee in the array with the given name
		for (int i = 0; i < es.length; i++) {
			if (es[i].fName == firstName && es[i].lName == lastName) {
				index = i;
			}
		}
		if (isValidEmployee(firstName, lastName) == false || index < 0
				|| es[index].hours + numHours >= 80 || firstName == null
				|| lastName == null || numHours < 0) {
			return false;
		}
		es[index].hours += numHours;
		return true;
	}

	// method returns the hours worked by a given employee. returns -1 if the
	// employee doesn't exist or the name provided is null
	public int numHours(String firstName, String lastName) {
		if (firstName != null && lastName != null) {
			if (isValidEmployee(firstName, lastName) == true) {
				for (int i = 0; i < es.length; i++) {
					if (es[i].fName == firstName && es[i].lName == lastName) {
						return es[i].hours;
					}
				}
			}
		}
		return -1;
	}

	public boolean makeSale(String firstName, String lastName, double saleAmt) {
		if (firstName == null || lastName == null || saleAmt < 0
				|| isValidEmployee(firstName, lastName) == false) {
			return false;
		}
		for (int i = 0; i < es.length; i++) {
			if (es[i].fName == firstName && es[i].lName == lastName) {
				es[i].revenue += saleAmt;
				es[i].sales++;
			}
		}
		return true;
	}

	// returns the value of all the sales made by a given employee. Returns -1
	// if the employee doesn't exist or the name is null
	public double amtSalesMade(String firstName, String lastName) {
		if (firstName == null || lastName == null
				|| isValidEmployee(firstName, lastName) == false) {
			return -1;
		}
		for (int i = 0; i < es.length; i++) {
			if (es[i].fName == firstName && es[i].lName == lastName) {
				return es[i].revenue;
			}
		}
		return -1;
	}

	// returns the proper paycheck amount for a given employee name. Returns -1
	// if the employee doesn't exist or the name is null
	public double getPaycheckAmount(String firstName, String lastName) {
		if (firstName == null || lastName == null
				|| isValidEmployee(firstName, lastName) == false) {
			return -1;
		}
		for (int i = 0; i < es.length; i++) {
			if (es[i].fName == firstName && es[i].lName == lastName) {
				// over written paycheck methods returns differn't values for
				// commission and salaried employees
				return es[i].paycheck();
			}
		}
		return -1;
	}

	// method returns the amount the company has to pay all of its employees in
	// the current pay period
	public double getPayroll() {
		double sum = 0.0;
		for (int i = 0; i < es.length; i++) {
			sum += es[i].revenue;
		}
		return sum;
	}

	// resets the pay period by setting all of the sales and revenue of
	// employees to 0
	public void newPayPeriod() {
		for (int i = 0; i < es.length; i++) {
			es[i].sales = 0;
			es[i].revenue = 0;

		}
	}

	// returns the amount that the system charges to run your company. Returns
	// 0 for free accounts
	public double billAmount() {
		if (isfree == true) {
			return 0.0;
		}
		return 10 * es.length;

	}

}
