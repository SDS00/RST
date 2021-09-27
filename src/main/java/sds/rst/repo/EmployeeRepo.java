package sds.rst.repo;

import org.springframework.data.repository.CrudRepository;
import sds.rst.data.Department;
import sds.rst.data.Employee;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    List<Employee> getByDepartment(Department department);
}
