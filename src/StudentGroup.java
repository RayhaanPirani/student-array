import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		// Add your implementation here
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		// Add your implementation here
		if(students == null) {
			throw new IllegalArgumentException();
		}

		this.students = students;
	}

	@Override
	public Student getStudent(int index) {
		// Add your implementation here
		if(index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}

		return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		// Add your implementation here
		if(student == null || index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		
		this.students[index] = student;
 	}

	@Override
	public void addFirst(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		this.add(student, 0);
	}

	@Override
	public void addLast(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		this.add(student, this.students.length);
	}

	@Override
	public void add(Student student, int index) {
		// Add your implementation here
		if(student == null || index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}
		Student[] studentsUpdated = new Student[this.students.length + 1];

		for(int i = 0; i < index; i++) {
			studentsUpdated[i] = this.students[i];
		}

		studentsUpdated[index] = student;

		for(int i = index; i < this.students.length; i++) {
			studentsUpdated[i + 1] = this.students[i];
		}

		this.setStudents(studentsUpdated);
	}

	@Override
	public void remove(int index) {
		// Add your implementation here
		if(index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}

		Student[] studentsUpdated = new Student[this.students.length - 1];

		for(int i = 0; i < index; i++) {
			studentsUpdated[i] = this.students[i];
		}

		for(int i = index + 1; i < this.students.length; i++) {
			studentsUpdated[i - 1] = this.students[i];
		}

		this.setStudents(studentsUpdated);
	}

	@Override
	public void remove(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		for(int i = 0; i < this.students.length; i++) {
			if(this.students[i].equals(student)) {
				this.remove(i);
				return;
			}
		}

		throw new IllegalArgumentException("Student not exist");
	}

	@Override
	public void removeFromIndex(int index) {
		// Add your implementation here
		if(index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}

		Student[] studentsUpdated = new Student[index + 1];

		for(int i = 0; i <= index; i++) {
			studentsUpdated[i] = this.students[i];
		}

		this.setStudents(studentsUpdated);
	}

	@Override
	public void removeFromElement(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		int studentIndex = -1;

		for(int i = 0; i < this.students.length; i++) {
			if(this.students[i].equals(student)) {
				studentIndex = i;
				break;
			}
		}

		this.removeFromIndex(studentIndex);
	}

	@Override
	public void removeToIndex(int index) {
		// Add your implementation here
		if(index < 0 || index >= this.students.length) {
			throw new IllegalArgumentException();
		}

		Student[] studentsUpdated = new Student[this.students.length - index - 1];

		for(int i = 0; i < studentsUpdated.length; i++) {
			studentsUpdated[i] = this.students[i + index];
		}

		this.setStudents(studentsUpdated);
	}

	@Override
	public void removeToElement(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		int studentIndex = -1;

		for(int i = 0; i < this.students.length; i++) {
			if(student.equals(this.students[i])) {
				studentIndex = i;
				break;
			}
		}

		this.removeToIndex(studentIndex);
	}

	@Override
	public void bubbleSort() {
		// Add your implementation here
		for(int i = 0; i < this.students.length - 1; i++) {
			for(int j = 0; j < this.students.length - i - 1; j++) {
				if(this.students[j].compareTo(this.students[j + 1]) > 0) {
					Student temporaryStudent = this.students[j];
					this.students[j] = this.students[j + 1];
					this.students[j + 1] = temporaryStudent;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		// Add your implementation here
		if(date == null) {
			throw new IllegalArgumentException();
		}

		List<Integer> studentsWithBirthDateCountIndices = new ArrayList<Integer>();
		
		for(int i = 0; i < this.students.length; i++) {
			if(date.equals(this.students[i].getBirthDate())) {
				studentsWithBirthDateCountIndices.add(i);
			}
		}

		Student[] studentsByBirthDate = new Student[studentsWithBirthDateCountIndices.size()];

		for(int i = 0, j = 0; i < this.students.length; i++) {
			if(date.equals(this.students[i].getBirthDate())) {
				studentsByBirthDate[j++] = this.students[i];
			}
		}
		
		int i = 0;
		for(int j : studentsWithBirthDateCountIndices) {
			studentsByBirthDate[i] = this.students[j];
			i++;
		}

		return studentsByBirthDate;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		// Add your implementation here
		if(firstDate == null || lastDate == null) {
			throw new IllegalArgumentException();
		}

		int studentsBetweenBirthDatesCount = 0;
		
		for(int i = 0; i < this.students.length; i++) {
			Date studentBirthDate = this.students[i].getBirthDate();
			if(studentBirthDate.equals(firstDate) || studentBirthDate.equals(lastDate) ||
				(studentBirthDate.after(firstDate) && studentBirthDate.before(lastDate))) {
				studentsBetweenBirthDatesCount++;
			}
		}

		Student[] studentsBetweenBirthDates = new Student[studentsBetweenBirthDatesCount];

		for(int i = 0, j = 0; i < this.students.length; i++) {
			Date studentBirthDate = this.students[i].getBirthDate();
			if(studentBirthDate.after(firstDate) && studentBirthDate.before(lastDate)) {
				studentsBetweenBirthDates[j++] = this.students[i];
			}
		}

		return studentsBetweenBirthDates;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		if(date == null) {
			throw new IllegalArgumentException();
		}

		int studentsWithNearBirthDateCount = 0;

		LocalDate specifiedDate = date.toInstant().atZone(ZoneId.systemDefault())
									  .toLocalDate();
		LocalDate lowerBoundDate = specifiedDate.minusDays(days);
		LocalDate upperBoundDate = specifiedDate.plusDays(days);

		for(int i = 0; i < this.students.length; i++) {
			LocalDate studentBirthDate = this.students[i].getBirthDate()
										 .toInstant().atZone(ZoneId.systemDefault())
										 .toLocalDate();

			if(studentBirthDate.isEqual(specifiedDate) ||
			   (studentBirthDate.isAfter(lowerBoundDate) && studentBirthDate.isBefore(upperBoundDate))) {
				studentsWithNearBirthDateCount++;
			   }
		}

		Student[] studentsWithNearBirthDate = new Student[studentsWithNearBirthDateCount];

		for(int i = 0, j = 0; i < this.students.length; i++) {
			LocalDate studentBirthDate = this.students[i].getBirthDate()
										 .toInstant().atZone(ZoneId.systemDefault())
										 .toLocalDate();

			if(studentBirthDate.isEqual(specifiedDate) ||
			   (studentBirthDate.isAfter(lowerBoundDate) && studentBirthDate.isBefore(upperBoundDate))) {
				studentsWithNearBirthDate[j++] = this.students[i];
			   }
		}

		return studentsWithNearBirthDate;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		// Add your implementation here
		if(indexOfStudent < 0 || indexOfStudent >= this.students.length) {
			throw new IllegalArgumentException();
		}

		LocalDate studentBirthDate = this.students[indexOfStudent].getBirthDate()
										 .toInstant().atZone(ZoneId.systemDefault())
										 .toLocalDate();
		
		return Period.between(studentBirthDate, LocalDate.now()).getYears();
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		// Add your implementation here
		int studentsWithAgeCount = 0;

		for(int i = 0; i < this.students.length; i++) {
			if(this.getCurrentAgeByDate(i) == age) {
				studentsWithAgeCount++;
			}
		}

		Student[] studentsWithAge = new Student[studentsWithAgeCount];

		for(int i = 0, j = 0; i < this.students.length; i++) {
			if(this.getCurrentAgeByDate(i) == age) {
				studentsWithAge[j++] = this.students[i];
			}
		}

		return studentsWithAge;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		// Add your implementation here
		double maxAvgMark = this.students[0].getAvgMark();
		int firstMaxAvgMarkIndex = 0, maxAvgMarkCount = 0;

		for(int i = 1; i < this.students.length; i++) {
			if(this.students[i].getAvgMark() > maxAvgMark) {
				maxAvgMark = this.students[i].getAvgMark();
				firstMaxAvgMarkIndex = i;
				maxAvgMarkCount = 1;
			}
			else if(this.students[i].getAvgMark() == maxAvgMark) {
				maxAvgMarkCount++;
			}
		}

		Student[] studentsWithMaxAvgMark = new Student[maxAvgMarkCount];

		for(int i = 0, j = 0; i < this.students.length; i++) {
			if(this.students[i].getAvgMark() == maxAvgMark) {
				studentsWithMaxAvgMark[j++] = this.students[i];
			}
		}

		return studentsWithMaxAvgMark;
	}

	@Override
	public Student getNextStudent(Student student) {
		// Add your implementation here
		if(student == null) {
			throw new IllegalArgumentException();
		}

		for(int i = 0; i < students.length; i++) {
			if(student.equals(this.students[i])) {
				return this.students[i + 1];
			}
		}
		return null;
	}
}
