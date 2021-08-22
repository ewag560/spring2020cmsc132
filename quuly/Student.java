package quuly;

//this class keeps track of the infornmation associated with each student 
//including a name, ID and amount of visits to office hours. This class is
//often referenced in the quuly class where students infornmation for a 
//class is managed.
public class Student {
	public String Name;
	public Integer ID;
	public int visits;

	// this constructor takes in a name and an id and stores them with the
	// object
	public Student(String name, Integer id) {
		Name = name;
		ID = id;
	}

}
