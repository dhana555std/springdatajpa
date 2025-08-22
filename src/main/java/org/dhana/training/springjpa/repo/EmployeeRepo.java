package org.dhana.training.springjpa.repo;

import jakarta.transaction.Transactional;
import org.dhana.training.springjpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);

    Optional<List<Employee>> findByNameContainingIgnoreCaseAndGender(String name, Character gender);

    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    Optional<Employee> findByName(@Param(value = "name") String name);

    @Modifying
    @Transactional
    @Query("Update Employee e SET e.name=:name WHERE e.email=:email")
    int updateName(@Param("name") String name,
                   @Param("email") String email);
}
