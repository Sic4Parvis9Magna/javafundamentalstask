package epamtasks.oop.t05;

import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.*;

public class University {
    private static final Logger log = getLogger(University.class);

    public static void main(String[] args) {
        StudentGroup group1 = new StudentGroup(Discipline.ASTRONOMY);
        StudentGroup group2 = new StudentGroup(Discipline.MATH);
        Student stud1 = new Student();
        Student stud2 = new Student();
        Student stud3 = new Student();
        Student stud4 = new Student();
        stud1.setFirstName("Maxim").setLastName("Ivanov").setStudyMode(StudyMode.FULL_TIME);
        stud2.setFirstName("Masha").setLastName("Petrova").setStudyMode(StudyMode.FULL_TIME);
        stud3.setFirstName("Misha").setLastName("Kozlov").setStudyMode(StudyMode.REMOTE);
        stud4.setFirstName("Evgeniy").setLastName("Volosenko").setStudyMode(StudyMode.EXTRAMURAL);
        group2.addStudent(stud1).addStudent(stud2).addStudent(stud3).addStudent(stud4);
        group1.addStudent(stud3).addStudent(stud4);

        group1.printStudents();
        group2.printStudents();

        log.info("Student {} {} takes an estimate {}",stud1.getFirstName(),
                stud1.getLastName(),group2.putAnEstimate(stud1,4.6));
        log.info("Student {} {} takes an estimate {}",stud1.getFirstName(),
                stud1.getLastName(),group2.putAnEstimate(stud1,3.5));
        log.info("Student {} {} takes an estimate {}",stud1.getFirstName(),
                stud1.getLastName(),group2.putAnEstimate(stud1,4.0));
        log.info("Student {} {} takes an estimate {}",stud1.getFirstName(),
                stud1.getLastName(),group2.putAnEstimate(stud1,3));

        stud1.printGrades();
        stud1.printGroupNumbers();

        stud3.printGroupNumbers();
        for (Integer number:stud2.getGroupNumber()){

            log.info("{} {} studying in group #{}",stud2.getFirstName(),
                                                stud2.getLastName(),number);
        }

    }
}
