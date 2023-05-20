package edu.imdadia.employees.repository;
import edu.imdadia.employees.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCase(String Name);
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndFatherNameIgnoreCase(String name, String fatherName);
    Optional<EmployeeEntity> findByEmployeeId(Integer id);
    Optional<EmployeeEntity> findByEmployeeNameIgnoreCaseAndPassword(String name, String password);
    Optional<EmployeeEntity> findByIdCardNumber(Integer idNumber);
}
