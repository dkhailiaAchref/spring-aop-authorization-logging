package net.achrefdkhailia.springboot2.init;

import net.achrefdkhailia.springboot2.model.Employee;
import net.achrefdkhailia.springboot2.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public void initData() {

        try {
            employeeRepository.save( new Employee("jhon"));
            employeeRepository.save( new Employee("frederic"));
            employeeRepository.save( new Employee("kevin"));
            employeeRepository.save( new Employee("michel"));
            employeeRepository.save( new Employee("franc"));
            employeeRepository.save( new Employee("raymond"));
        } catch (final Exception ex) {
            logger.error("Exception while inserting mock data {}", ex);
        }

    }


}
