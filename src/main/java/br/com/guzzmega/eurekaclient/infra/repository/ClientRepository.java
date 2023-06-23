package br.com.guzzmega.eurekaclient.infra.repository;

import br.com.guzzmega.eurekaclient.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
	Optional<Client> findByDocument(String document);
}
