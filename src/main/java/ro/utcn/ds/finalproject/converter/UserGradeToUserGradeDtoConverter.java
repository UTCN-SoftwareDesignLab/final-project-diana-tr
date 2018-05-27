package ro.utcn.ds.finalproject.converter;

import org.springframework.stereotype.Service;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;

@Service
public class UserGradeToUserGradeDtoConverter implements SuperConverter<UserGrade, UserGradeDto> {

    @Override
    public UserGradeDto apply(final UserGrade userGrade) {
        final UserGradeDto userGradeDto = new UserGradeDto();
        userGradeDto.setId(userGrade.getId());
        userGradeDto.setGrade(userGrade.getGrade());
        userGradeDto.setSubjectdetailid(userGrade.getSubjectDetail().getId());
        userGradeDto.setStudent_id(userGrade.getStudent().getId());
        userGradeDto.setSubject_id(userGrade.getSubject().getId());
        return userGradeDto;
    }
}
