package org.dhana.training.springjpa.repo;

import jakarta.transaction.Transactional;
import org.dhana.training.springjpa.entity.Department;
import org.dhana.training.springjpa.entity.Employee;
import org.dhana.training.springjpa.utils.EmployeeStatus.EmployeeStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;

@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private DepartmentRepo departmentRepo;

    @Test
    @Transactional
    @Commit
    void testEmployeeRepo(){
        var dept = departmentRepo.save(new Department("Information Technology"));
        System.out.println(dept);
        var employee = new Employee("dhana@gmail.com", "Dhana", 'M',
                LocalDate.of(1990, 1, 1), 999999999, EmployeeStatus.ACTIVE);
        employee.setDepartment(dept);
        var emp = employeeRepo.save(employee);

        System.out.println(emp);
    }
}
