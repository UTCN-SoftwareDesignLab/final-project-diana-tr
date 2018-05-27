package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;

@Service
public class UserGradeDtoToUserGradeConverter implements SuperConverter<UserGradeDto, UserGrade> {
    @Override
    public UserGrade apply(final UserGradeDto userGradeDto) {
        final UserGrade userGrade = new UserGrade();
        userGrade.setId(userGradeDto.getId());
        userGrade.setGrade(userGradeDto.getGrade());

        return userGrade;
    }
}
