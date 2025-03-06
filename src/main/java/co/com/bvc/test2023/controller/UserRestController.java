package co.com.bvc.test2023.controller;

import co.com.bvc.test2023.model.User;
import co.com.bvc.test2023.repository.UserRepository;
import co.com.bvc.test2023.service.ICompanyService;
import co.com.bvc.test2023.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private IUserService userService;

    /**
     * Method to get all the users
     * @return entity your body is list of users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("llega al método getAllUsers...");
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Method to get one user for id
     * @param id user
     * @return entity your body contain user
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        logger.info("llega al método getUserById...");
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to create new user or update a existing
     * @param user
     * @return entity your body contain user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("llega al método createUser...");
        User savedUser = userService.createUpdateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /**
     * Method to update user
     * @param id user
     * @param user
     * @return entity your body contain user
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("llega al método updateUser...");
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            User updatedUser = userService.createUpdateUser(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to delete one user for id
     * @param id
     * @return entity with http status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        logger.info("llega al método deleteUser...");
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
