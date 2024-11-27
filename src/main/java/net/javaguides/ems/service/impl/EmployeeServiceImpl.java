package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

//	репозиторий сотрудников
	private EmployeeRepository employeeRepository;

	// метод создания сотрудника
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {

		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee saveEmployee = employeeRepository.save(employee); // сохранение в базе данных и возвращает объект созданного сотрудника

		return EmployeeMapper.mapToEmployeeDto(saveEmployee);    // возвращение сохраненного сотрудника клиенту
	}

//	Метод получения сотрудника по идентификатору
	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->
						new ResourceNotFoundException("Employee is not exists with with given id: " + employeeId));

		return EmployeeMapper.mapToEmployeeDto(employee);
	}

//	Метод для получения всех сотрудников
	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();

		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
	}

//	Метод для обновления данных сотрудника
	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
//		Возвращает объект сотрудника
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is exists with given id: " + employeeId)
		);

		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());

//		Сохранение новых данных сотрудника в базу данных и возвращает обновленные данные сотрудника
		Employee updatedEmployeeObj =  employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

//	Метод удаления сотрудника
	@Override
	public void deleteEmployee(Long employeeId) {
//		Возвращает объект сотрудника
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee is exists with given id: " + employeeId)
		);

//		Удалить сотрудника по его идентификатору
		employeeRepository.deleteById(employeeId);
	}
}
