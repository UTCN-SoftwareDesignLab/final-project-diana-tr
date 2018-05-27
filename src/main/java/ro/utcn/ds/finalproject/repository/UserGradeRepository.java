package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.ds.finalproject.model.UserGrade;

import java.util.List;

@Repository
public interface UserGradeRepository extends JpaRepository<UserGrade,Long>{
    List<UserGrade> getAllByUser_id(Long id);

    List<UserGrade> getAllBySubjectId(Long id);
}
