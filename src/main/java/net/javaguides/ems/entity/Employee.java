package net.javaguides.ems.entity;

import jakarta.persistence.*;
import lombok.*;

//  Добавляет сотрудника в базу данных
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id", nullable = false, unique = true)
	private String email;
}
