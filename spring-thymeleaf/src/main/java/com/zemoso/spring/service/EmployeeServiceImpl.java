package com.zemoso.spring.service;

import com.zemoso.spring.dao.EmployeeRepository;
import com.zemoso.spring.entity.Employee;
import com.zemoso.spring.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{


        private EmployeeRepository employeeRepository;

        @Autowired
        public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        @Override
        public List<Employee> findAll() {
            return employeeRepository.findAll();
        }

        @Override
        public Employee findById(int id) {
            Optional<Employee> result = employeeRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            } else {
                throw new CustomerNotFoundException("Employee Id not found: " + id);
            }
        }

        @Override
        public void save(Employee employee) {
            employeeRepository.save(employee);
        }

        @Override
        public void deleteById(int id) {
            employeeRepository.deleteById(id);
        }

}
