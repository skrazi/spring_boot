package com.zemoso.spring.service;


import com.zemoso.spring.dao.EmployeeRepository;
import com.zemoso.spring.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void testFindAllEmployees() {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee(1,"John", "John","jj@email.com");
        Employee empTwo = new Employee(2,"Alex", "Maleachi","ak@email.com");
        Employee empThree = new Employee(3,"Steve", "Waugh","sw@email.com");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.findAll()).thenReturn(list);

        //test
        List<Employee> empList = employeeService.findAll();

        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        int id = 1;
        Employee empOne = new Employee(1,"John", "John","jj@email.com");
        when(employeeRepository.findById(id)).
                thenReturn(java.util.Optional.of(empOne));

        Employee employee = employeeService.findById(id);

        assertEquals(employee.getFirstName(),"John");
        assertEquals(employee.getLastName(),"John");
        assertEquals(employee.getEmail(),"jj@email.com");

    }

    @Test
    void testFindByIdNotFound() {
        
    }

    @Test
    void testSave() {
        Employee empOne = new Employee(1,"John", "John","jj@email.com");

        employeeService.save(empOne);

        verify(employeeRepository, times(1)).save(empOne);
    }

    @Test
    void testUpdate() {
        Employee empOne = new Employee(1,"John", "John","jj@email.com");
        employeeService.save(empOne);

        empOne.setLastName("Stamos");

        employeeService.save(empOne);

        verify(employeeRepository, times(2)).save(empOne);
        assertEquals(empOne.getId(), 1);
        assertEquals(empOne.getLastName(), "Stamos");
    }
}
