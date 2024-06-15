package guibas.java.gof.service.impl;

import guibas.java.gof.model.Adress;
import guibas.java.gof.model.Client;
import guibas.java.gof.repository.AdressRepository;
import guibas.java.gof.repository.ClientRepository;
import guibas.java.gof.service.ClientService;
import guibas.java.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        saveClientWithCep(client);
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientBd = clientRepository.findById(id);
        if (clientBd.isPresent()){
            saveClientWithCep(client);
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void saveClientWithCep(Client client){
        String cep = client.getAdress().getCep();
        Adress adress = adressRepository.findById(cep).orElseGet(() -> {
            Adress newAdress = viaCepService.consultarCep(cep);
            adressRepository.save(newAdress);
            return newAdress;
        });
        client.setAdress(adress);
        clientRepository.save(client);
    }

}
