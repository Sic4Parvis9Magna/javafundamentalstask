package epamtasks.oop.t02;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Office {
    private static final Logger log = LogManager.getLogger(Office.class);

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setFirstName("Mark")
                .setLastName("Renton")
                .setPosition(Position.MANAGER);
        Employee employee2 = new Employee();
        employee2.setLastName("White")
                .setFirstName("Linda")
                .setPosition(Position.SELLER);
        Employee employee3 = new Employee();
        Employee employee4 = new Employee();
        employee4.setPosition(Position.EMPLOYEE)
                .setFirstName("Kevin")
                .setLastName("Finerty");
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeList.add(employee4);

        for (Employee emp:employeeList){
           emp.addAllTools(StationeryGenerator.generateStationery(10));
            log.info("Employee: {} {} as {} have tool set at price {}",
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getPosition().name(),
                    emp.getTotalPrice());
        }


    }
}
