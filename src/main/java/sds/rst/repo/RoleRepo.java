package sds.rst.repo;

import org.springframework.data.repository.CrudRepository;
import sds.rst.data.Role;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role getById(Long id);
}
