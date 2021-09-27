package sds.rst.repo;

import org.springframework.data.repository.CrudRepository;
import sds.rst.data.Department;

public interface DepartmentRepo extends CrudRepository<Department, Long> {
    Department getById(Long id);
}
