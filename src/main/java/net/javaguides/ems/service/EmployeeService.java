package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);

//	Получение сотрудника по идентификатору
	EmployeeDto getEmployeeById(Long employeeId);

//	Получение всех сотрудников
	List<EmployeeDto> getAllEmployees();

//	Изменение сотрудника
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

//	Удаление сотрудника
	void deleteEmployee(Long employeeId);
}
