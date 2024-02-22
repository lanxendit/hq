package net.codejava.role;

import net.codejava.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepo extends JpaRepository<Role, Integer> {
    //Optional<Individual> findProductByCnId(String cn_id);
}
