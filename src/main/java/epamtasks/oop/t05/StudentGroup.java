package epamtasks.oop.t05;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.util.HashSet;
import java.util.Set;

public class StudentGroup {
    private static final Logger log = LogManager.getLogger(StudentGroup.class);
    private int groupNumber;
    private Discipline discipline;
    private Set<Student> students;

    private static int groupCounter = 1 ;

    public StudentGroup(){
        groupNumber = groupCounter++;
        discipline = Discipline.UNKNOWN;
        students = new HashSet<>();
    }
    public StudentGroup(Discipline discipline){
        this();
        this.discipline = discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public static int getGroupCounter() {
        return groupCounter;
    }

    public StudentGroup addStudent(Student student){
        student.addGroupNumber(groupNumber);
        students.add(student);
        return this;
    }

    public boolean putAnEstimate(Student student,double estimate){
        if(!students.contains(student)){ return false;
        }else{
            student.getGrades().addGrade(discipline,estimate);
            return true;
        }

    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "groupNumber = " + groupNumber +
                ", discipline = " + discipline +
                '}';
    }

    public void printStudents(){
        log.info("Students of {}",this.toString());
        for(Student stud:students){
            log.info(stud.toString());
        }
    }
}
