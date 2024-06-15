package guibas.java.gof.service;

import guibas.java.gof.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientService {

    Iterable<Client> findAll();

    Client findById(Long id);

    void insert(Client client);

    void update(Long id, Client client);

    void delete(Long id);
}
