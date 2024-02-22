package net.codejava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
//        if (userRepository.getUserByUsername(user.getUsername())!=null) {
//            User userInDb = userRepository.getUserByUsername(user.getUsername());
//            if (userInDb.getPassword() == user.getPassword()) {
//               // ko lam j
//            }else user.setPassword(Encode.encode(user.getPassword()));
//        }else user.setPassword(Encode.encode(user.getPassword()));
        user.setPassword(Encode.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User loadUserByUsername2(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void delete(String username) {
        User user = userRepository.getUserByUsername(username);
        userRepository.deleteById(user.getId());
    }
}
