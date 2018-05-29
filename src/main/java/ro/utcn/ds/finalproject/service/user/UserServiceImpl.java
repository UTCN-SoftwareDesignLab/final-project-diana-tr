package ro.utcn.ds.finalproject.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ds.finalproject.converter.UserDtoToUserConverter;
import ro.utcn.ds.finalproject.dto.UserDto;
import ro.utcn.ds.finalproject.model.Role;
import ro.utcn.ds.finalproject.model.User;
import ro.utcn.ds.finalproject.repository.RoleRepository;
import ro.utcn.ds.finalproject.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDtoToUserConverter converter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        Role role = roleRepository.findByRole(userDto.getRole());
        User user = new User(userDto.getUsername(), bCryptPasswordEncoder.encode(userDto.getPassword()), true, new HashSet<>(Arrays.asList(role)));
        return userRepository.save(user);
    }

    @Override
    public void delete(UserDto userDto) {
        User user = converter.apply(userDto);
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {

        User newUser = userRepository.getOne(user.getId());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        newUser.setEnabled(true);
        userRepository.save(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllByRole(String role) {
        return userRepository.findByRole(role);
    }

}
