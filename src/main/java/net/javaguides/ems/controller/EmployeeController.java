package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
	позволяет выполнять запросы между разными источниками к аннотированным методам контроллера
 */
@CrossOrigin("*")
@AllArgsConstructor
//  Обработка http запросы
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;


//	REST API для добавления сотрудников
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
//		вернет сохраненного сотрудника
		EmployeeDto saveEmployee = employeeService.createEmployee(employeeDto);

//		Возвращающий объект ответа
		return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
	}

//  REST API для получения сотрудника по id
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
//		получаем сотрудника по идентификатору
		EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

		return ResponseEntity.ok(employeeDto);
	}

//	REST API получение всех сотрудников
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
//		Возвращение всех сотрудников
		List<EmployeeDto> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(employees);
	}

//	REST API обновление данных сотрудника по id и возвращение измененных данных
	@PostMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
		EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
		return ResponseEntity.ok(employeeDto);
	}

//	Rest API для удаления сотрудника в базе данных
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("Employee deleted successfully!");
	}
}
