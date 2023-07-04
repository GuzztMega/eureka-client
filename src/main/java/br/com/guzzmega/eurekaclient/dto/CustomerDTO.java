package br.com.guzzmega.eurekaclient.dto;

import br.com.guzzmega.eurekaclient.domain.Customer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerDTO {

	private UUID id;
	private String document;
	private String name;
	@DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
	private LocalDate birthDate;

	public CustomerDTO(UUID id, String document, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.document = document;
		this.birthDate = birthDate;
	}

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.document = customer.getDocument();
		this.birthDate = customer.getBirthDate();
	}
}