package co.com.bvc.test2023.service;

import co.com.bvc.test2023.model.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User createUpdateUser(User company);

    public void deleteUser(Long id);

}
