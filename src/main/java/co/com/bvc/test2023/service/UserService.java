package co.com.bvc.test2023.service;

import co.com.bvc.test2023.model.User;
import co.com.bvc.test2023.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to get all the users
     * @return list of users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Method to get one user for id
     * @return user
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Method to create new user or update a existing
     * @return create or update user
     */
    public User createUpdateUser(User company) {
        return userRepository.save(company);
    }

    /**
     * Method to delete one user for id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
