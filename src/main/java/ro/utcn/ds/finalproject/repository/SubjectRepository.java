package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.utcn.ds.finalproject.model.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{
    Subject findBySubjectName(String subjectName);

    void deleteById(Long id);

    void deleteBySubjectName(String subjectName);

}
