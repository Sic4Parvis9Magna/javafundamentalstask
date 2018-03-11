package epamtasks.oop.t05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    private static final Logger log = LogManager.getLogger(Student.class);
    private String firstName;
    private String lastName;
    private int id;
    private Set<Integer> groupsNumbers;
    private Grades grades;
    private StudyMode studyMode;

    private static int studentCounter = 1;


    public Student(){
        firstName = "unknown";
        lastName = "unknown";
        id = studentCounter++;
        groupsNumbers = new HashSet<>();
        grades = new Grades();
        studyMode = StudyMode.UNKNOWN;
    }

    public Student addGroupNumber(int groupNumber) {
        this.groupsNumbers.add(groupNumber);
        return this;
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Student setStudyMode(StudyMode studyMode) {
        this.studyMode = studyMode;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getGroupNumber() {
        return groupsNumbers;
    }

    public StudyMode getStudyMode() {
        return studyMode;
    }

    public Grades getGrades() {
        return grades;
    }

    public static int getStudentCounter() {
        return studentCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(groupsNumbers, student.groupsNumbers) &&
                Objects.equals(grades, student.grades) &&
                studyMode == student.studyMode;
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, id, groupsNumbers, grades, studyMode);
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", id = " + id +
                ", studyMode = " + studyMode +
                '}';
    }

    public void printGroupNumbers(){
        for (Integer grNum:this.getGroupNumber()){
            log.info("{} {} studying in group #{}",this.getFirstName(),
                                                this.getLastName(),grNum);

        }
    }
    public void printGrades(){
      Set<Discipline> currentDisciplines = grades.getGradeMap().keySet();
      for(Discipline dis: currentDisciplines) {
          log.info("Discipline {} mean grade = {}",dis.name(),grades.getMeanGrade(dis));
      }
    }
}
