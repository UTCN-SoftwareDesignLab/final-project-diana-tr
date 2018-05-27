package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.UserDto;
import ro.utcn.ds.finalproject.model.User;

@Service
public class UserDtoToUserConverter implements SuperConverter<UserDto, User> {
    @Override
    public User apply(final UserDto input) {
        final User user = new User();
        user.setId(input.getId());
        user.setPassword(input.getPassword());
        user.setUsername(input.getUsername());
        user.setEnabled(input.isEnabled());
        return user;
    }
}
