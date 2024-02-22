package net.codejava.role;

import net.codejava.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepo repo;
    public List<Role> listAll() {
        return (List<Role>) repo.findAll();
    }
}
