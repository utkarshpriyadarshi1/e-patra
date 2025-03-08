package in.updev.repository;

import in.updev.model.Role;
import in.updev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();

    List<Role> findAllRoles();

    Role findRoleById(Long id);

    void saveRole(Role role);
}