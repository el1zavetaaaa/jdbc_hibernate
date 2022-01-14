package hibernate.dao;

import hibernate.entity.Bank;
import hibernate.entity.Client;
import org.hibernate.Session;

public class AddingDataToTable {

    public void initialization(Session session) {

        Client client1 = new Client();
        client1.setName("Client 1");
        session.persist(client1);

        Client client2 = new Client();
        client2.setName("Client 2");
        session.persist(client2);


        Bank bank = new Bank();
        bank.setName("Bank 1");
        bank.addClient(client1);
        bank.addClient(client2);
        session.persist(bank);
    }
}
