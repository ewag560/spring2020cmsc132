package quuly;

import java.util.ArrayList;

//this class keeps track of the students in a course, the queue for office
//hours and moving students in and out of that queue. Students can also be 
//added and removed from the course. The seperate student class defines the
//identifiers for each student.
public class Quuly {
//ints define the maximum allowed visits and total times anyone has been
	// helped
	public int mVisits, totalHelps;
	// total student arraylist
	public ArrayList<Student> qul = new ArrayList<Student>();
	// office hours queue arraylist
	public ArrayList<Student> que = new ArrayList<Student>();

	// constructor creates class with the defined maxVisits with a min of 1
	public Quuly(int maxVisits) {
		if (maxVisits < 1) {
			mVisits = 1;
		} else {
			mVisits = maxVisits;
		}

	}

	// method adds a student to the class if the name and id are valid and the
	// id is not already taken
	public boolean addStudent(String newStudentName, Integer newStudentID) {
		if (newStudentName == "" || newStudentID <= 0) {
			return false;
		}
		if (qul.size() > 0) {
			for (int index = 0; index < qul.size(); index++) {
				if (qul.get(index).ID.compareTo(newStudentID) == 0) {
					return false;
				}
			}
		}
		Student stu = new Student(newStudentName, newStudentID);
		qul.add(stu);
		return true;
	}

	// returns the total number of students
	public int numStudents() {
		return qul.size();
	}

	// adds a student to the queue if their id is valid, in the list of students
	// and not already in the queue for office hours
	public boolean addStudentToQueue(Integer id) {
		int stuIndex = -2;
		if (id == null) {
			return false;
		}
		// checks if the id corresponds to some student
		if (qul.size() > 0) {
			for (int index = 0; index < qul.size(); index++) {
				if (qul.get(index).ID.compareTo(id) == 0) {
					stuIndex = index;
				}
			}
			if (stuIndex < 0) {
				return false;
			}
		}
		// checks if id is not already in the queue
		if (que.size() > 0) {
			for (int x = 0; x < que.size(); x++) {
				if (que.get(x).ID.compareTo(id) == 0) {
					return false;
				}

			}
		}
		if (qul.get(stuIndex).visits >= mVisits) {
			return false;
		}
		// qul.get(stuIndex).visits++;
		que.add(qul.get(stuIndex));
		return true;
	}

	// returns the size of the queue
	public int queueSize() {
		return que.size();
	}

	// checks if a student with the given id is in the queue, returns true/false
	public boolean isInQueue(Integer id) {
		if (id == null)
			return false;
		if (que.size() > 0) {
			for (int index = 0; index < que.size(); index++) {
				if (que.get(index).ID.compareTo(id) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	// returns the amount of students in the queue with a certain name
	public int isInQueue(String name) {
		int count = 0;
		if (name == null || que.size() < 1) {
			return 0;
		}
		for (int index = 0; index < que.size(); index++) {
			if (que.get(index).Name == name) {
				count++;
			}
		}
		return count;
	}

	// proceses a student being helped in office hours, removing them from the
	// queue
	public boolean helpNextStudent() {
		if (que.size() < 1) {
			return false;
		}
		que.get(0).visits++;
		que.remove(0);
		totalHelps++;
		return true;
	}

	// returns the total times any student has been helped
	public int numStudentsHelped() {
		return totalHelps;
	}

	// return the number of times a student with a certain id has been helped in
	// office hours
	public int numTimesHelped(Integer id) {
		if (id == null) {
			return -1;
		}
		// loops throuh quuly to see if the id exists
		for (int index = 0; index < qul.size(); index++) {
			if (qul.get(index).ID.compareTo(id) == 0) {
				return qul.get(index).visits;
			}
		}
		return -1;
	}

	// removes a student from the course
	public boolean dropStudent(Integer id) {
		if (id == null || qul.size() == 0) {
			return false;
		}
		// removes student from a queue
		if (que.size() > 0) {
			for (int index = 0; index < que.size(); index++) {
				if (que.get(index).ID.compareTo(id) == 0) {
					que.remove(index);
				}
			}
		}
		// removes student from the qully
		for (int index = 0; index < qul.size(); index++) {
			if (qul.get(index).ID.compareTo(id) == 0) {
				qul.remove(index);
				return true;
			}
		}
		return false;
	}
}
