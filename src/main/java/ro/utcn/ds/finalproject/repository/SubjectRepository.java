package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ds.finalproject.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectName(String subjectName);

    void deleteById(Long id);

    void deleteBySubjectName(String subjectName);

    Subject findByTeacher_id(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_subject(student_id,subject_id) VALUES (?1,?2)", nativeQuery = true)
    void addStudentToSubject(Long studentId, Long subjectId);

    @Query(value = "SELECT COUNT(s.subject_id) FROM subject s WHERE s.teacher_id=?1", nativeQuery = true)
    Long existsByTeacherId(Long id);

    @Query(value = "SELECT COUNT(s.student_id) FROM student_subject s WHERE s.subject_id=?1", nativeQuery = true)
    Long existsByStudentId(Long id);


}
