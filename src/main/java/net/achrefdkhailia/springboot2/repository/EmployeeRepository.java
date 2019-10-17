package net.achrefdkhailia.springboot2.repository;

import net.achrefdkhailia.springboot2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
