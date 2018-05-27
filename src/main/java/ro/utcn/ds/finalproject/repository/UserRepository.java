package ro.utcn.ds.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.utcn.ds.finalproject.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);

    void deleteByUsername(String username);

    void deleteById(Long id);

    User findByUsernameAndPassword(String username,String password);

    @Query(value="SELECT * FROM user u INNER JOIN user_role ur on(u.user_id=ur.user_id) INNER JOIN role r on(ur.role_id=r.role_id) WHERE r.role=?1",nativeQuery = true)
    List<User> findByRole(String role);
}
