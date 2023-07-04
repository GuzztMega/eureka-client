package br.com.guzzmega.eurekaclient.service;

import br.com.guzzmega.eurekaclient.domain.Customer;
import br.com.guzzmega.eurekaclient.infra.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private CustomerRepository repository;

	@Transactional
	public Customer save(Customer object){
		return repository.save(object);
	}

	public Optional<Customer> getByDocument(String document){
		return repository.findByDocument(document);
	}

	public List<Customer> findAll(){
		return repository.findAll();
	}
}
