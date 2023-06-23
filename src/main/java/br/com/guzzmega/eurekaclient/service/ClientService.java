package br.com.guzzmega.eurekaclient.service;

import br.com.guzzmega.eurekaclient.domain.Client;
import br.com.guzzmega.eurekaclient.infra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	@Transactional
	public Client save(Client object){
		return repository.save(object);
	}

	public Optional<Client> getByDocument(String document){
		return repository.findByDocument(document);
	}
}
