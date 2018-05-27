package ro.utcn.ds.finalproject.service.user;

import ro.utcn.ds.finalproject.dto.UserDto;
import ro.utcn.ds.finalproject.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User create(UserDto userDto);

    void delete(UserDto userDto);

    void update(User user);

    User findByUsername(String username);

    User findById(Long id);

    User findByUsernameAndPassword(String username, String password);

    void deleteByUsername(String username);

    void deleteById(Long id);

    List<User> getAllByRole(String role);
}
