package in.doracorp.auth.repository;

import in.doracorp.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.email = ?1 and u.userType = 'visitor'")
    User findByEmailAndIsVisitor(String email);

    @Query("select u from User u where u.email = ?1 and u.tenantId = ?2")
    User findByEmailAndTenantId(String email,String tenantId);

    @Query("select count(u)>0 from User u where u.email = ?1 and u.userType = 'visitor'")
    boolean existsUserByEmailAndIsVisitor(String email);

    @Query("select count(u)>0 from User u where u.email = ?1 and u.tenantId = ?2")
    boolean existsUserByEmailAndTenantId(String email,String tenantId);
}
