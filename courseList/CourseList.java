package courseList;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CourseList {

	private Map<String, Collection<String>> courses;
	public Map<String, Integer> courseCapacity;
	private int count = 0;

	public CourseList() {
		courses = new HashMap<String, Collection<String>>();
		courseCapacity = new HashMap<String, Integer>();
	}

	public void takeCourse(String student, String course)
			throws IllegalArgumentException {
		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}
		if (courseCapacity.containsKey(course) == false) {
			Collection<Integer> c = new HashSet<Integer>();
			courseCapacity.put(course, 0);
		}

		if (courses.containsKey(student)) {
			if (courses.get(student).contains(course)) {
				return;
			} else {
				courses.get(student).add(course);
				courseCapacity.put(course, courseCapacity.get(course) + 1);
				count++;
			}
		} else {
			Collection<String> c = new HashSet<String>();
			c.add(course);
			courses.put(student, c);
			courseCapacity.put(course, courseCapacity.get(course) + 1);
			count++;
		}
	}

	public boolean isTakingCourse(String student, String course)
			throws IllegalArgumentException {
		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}
		if (courses.containsKey(student) == false) {
			return false;
		}
		if (courses.get(student).contains(course) == false) {
			return false;
		}
		return true;
	}

	public int numCoursesTaking(String student)
			throws IllegalArgumentException {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (courses.containsKey(student) == false) {
			return 0;
		}
		return courses.get(student).size();
	}

	public int numEnrolled(String course) throws IllegalArgumentException {
		int count = 0;
		if (course == null) {
			throw new IllegalArgumentException();
		}
		for (String stu : courses.keySet()) {
			if (courses.get(stu).contains(course)) {
				count++;
			}
		}
		return count;
	}

	public String mostPopularCourse() {
		int max = 0;
		String winner = null;

		for (String s : courseCapacity.keySet()) {
			// System.out.print(courseCapacity.get(s).size());
			if (courseCapacity.get(s) > max) {
				// System.out.print(courseCapacity.get(s).size());
				max = courseCapacity.get(s);
				winner = s;
			}
		}
		return winner;
	}

	public boolean dropCourse(String student, String course)
			throws IllegalArgumentException {
		if (student == null || course == null) {
			throw new IllegalArgumentException();
		}
		if (courses.containsKey(student) == false) {
			return false;
		}
		if (courseCapacity.containsKey(course) == false) {
			return false;
		}

		if (courses.get(student).contains(course)) {
			courses.get(student).remove(course);
			// System.out.print(courseCapacity.get(course).size());
			courseCapacity.put(course, courseCapacity.get(course) - 1);
			// System.out.print(courseCapacity.get(course).size());
			if (courses.get(student).size() == 0) {
				courses.remove(student);
			}
		} else {
			return false;
		}
		return true;
	}

}
