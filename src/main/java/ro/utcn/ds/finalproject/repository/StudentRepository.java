package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ds.finalproject.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_subject(student_id,subject_id) VALUES (?1,?2)", nativeQuery = true)
    void addSubjectToStudent(Long studentId, Long subjectId);

    @Query(value = "SELECT COUNT(s.subject_id) FROM student_subject s WHERE s.student_id=?1", nativeQuery = true)
    Long existsBySubjectId(Long id);

    @Query(value = "SELECT COUNT(s.subject_id) FROM student_subject s WHERE s.student_id=?1 AND s.subject_id=?2", nativeQuery = true)
    Long existsBySubjectAndStudent(Long student_id, Long subject_id);
}
