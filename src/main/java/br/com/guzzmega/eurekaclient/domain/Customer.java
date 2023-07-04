package br.com.guzzmega.eurekaclient.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String document;
	private String name;
	private LocalDate birthDate;

	public Customer(UUID id, String document, String name, LocalDate birthDate){
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
	}

	public Integer getAge()
	{
		return Period.between(this.getBirthDate(), LocalDate.now()).getYears();
	}
}
