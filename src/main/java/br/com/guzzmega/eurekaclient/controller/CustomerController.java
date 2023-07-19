package br.com.guzzmega.eurekaclient.controller;

import br.com.guzzmega.eurekaclient.domain.Customer;
import br.com.guzzmega.eurekaclient.dto.CustomerDTO;
import br.com.guzzmega.eurekaclient.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("customers")
public class CustomerController {

	@Autowired
	private ClientService service;

	@GetMapping("/health")
	public String status(){
		log.info("Checking customers microservice status...");
		return "Customers Application Status: UP!";
	}

	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody CustomerDTO objectDto){
		var object = new Customer();
		BeanUtils.copyProperties(objectDto, object);
		service.save(object);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.query("document={document}").buildAndExpand(object.getDocument())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
		List<Customer> customerList = service.findAll();
		List<CustomerDTO> customerDTOList = customerList.stream().map(CustomerDTO::new).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(customerDTOList);

	}

	@GetMapping("/{document}")
	public ResponseEntity<Object> getCustomer(@PathVariable(value="document") String document){
		Optional<Customer> objectOptional = service.getByDocument(document);

		if(objectOptional.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Couldn't find customer with document %s", document));
		}

		return ResponseEntity.status(HttpStatus.OK).body(objectOptional.get());
	}
}