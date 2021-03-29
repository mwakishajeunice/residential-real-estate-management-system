package com.jeunice.realestate.bootstrap;

import com.jeunice.realestate.dao.AgentRepository;
import com.jeunice.realestate.dao.HouseRepository;
import com.jeunice.realestate.dao.TenantRepository;
import com.jeunice.realestate.dao.TransactionRepository;
import com.jeunice.realestate.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PopulateDataAgents implements CommandLineRunner {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("In the run method: ");
        if(agentRepository.count() == 0){
            populateAgents();
        }
        if (houseRepository.count() == 0 ){
            populateHouses();
        }
        if (tenantRepository.count() == 0 ){
            populateTenants();
        }
        if (transactionRepository.count() == 0 ){
            populateTransactions();
        }
    }
    private void populateAgents() {
        System.out.println("In the populateAgents method: ");

        Agent agent1 = new Agent(89L,81475L, "otieno", "chris", "otienochris@gmail.com", 55663L);
        Agent agent2 = new Agent(90L,70254L, "jeunice", "mwakisha", "mwakisha@gmail.com", 5663L);
        Agent agent3 = new Agent(91L,4670L, "xavier", "oduor", "frank@gmail.com", 545663L);
        Agent agent4 = new Agent(92L,54460L, "wachiye", "jeremy", "sirah@gmail.com", 445663L);
        List<Agent> agents = List.of(agent1, agent2, agent3,agent4);
        agentRepository.saveAll(agents);
    }
    private void populateHouses() {
        System.out.println("In the populateHouses method: ");

        House house1 = new House(null, HouseTypes.  ONE_BEDROOM,"Gate",7000.00,"Acacia","Booked","A1",
                agentRepository.findById(89L).orElse(Agent.builder().build()));

        House house2 = new House(null, HouseTypes.SINGLE,"420-Gate", 3500.00, "Lareba", "Available","A5",
                agentRepository.findById(90L).orElse(Agent.builder().build()));
//
//        House house3 = new House(null,HouseTypes.BEDSITTER, "Jacaranda-Gate", 4000.00, "Jacaranda Hostels", "Available","4",
//                agentRepository.findById(72L).orElse(Agent.builder().agentId(87L).build()));

        House house4 = new House(null, HouseTypes.TWO_BEDROOM, "Mosque-Njokerio", 8000.00, "Starehe", "Booked","D7",
                agentRepository.findById(91L).orElse(Agent.builder().build()));

        House house5 = new House(null, HouseTypes.BEDSITTER ,"Gate", 6000.00, "Jovpe", "Booked","V8",
                agentRepository.findById(92L).orElse(Agent.builder().build()));

        List<House> houses = List.of(house1, house2, house4, house5);
        houseRepository.saveAll(houses);
    }


    private void populateTenants() {
        System.out.println("In the populateTenants method: ");

        Tenant tenant1 = new Tenant(392L,"Christopher","Otieno","onechrisly123@gmail.com",78);

        Tenant tenant2 = new Tenant(626L,"betty","mutuku","bet@gmail.com",93L);
        Tenant tenant3 = new Tenant(272L,"daniel","sam","sam@gmail.com",29);
        Tenant tenant4= new Tenant(782L,"choice","angelah","choice@gmail.com",20);
        Tenant tenant5 = new Tenant(922L,"choice","angelah","choice@gmail.com",48);
        List<Tenant> tenants = List.of(tenant1,tenant2,tenant3, tenant4,tenant5);
        tenantRepository.saveAll(tenants);
    }
    private void populateTransactions() {
        System.out.println("In the populateTransactions method: ");

        Transaction transaction1 = new Transaction("OSP23H",3000.00, LocalDate.now(),"Deposit",
                Tenant.builder()
                        .email("mwakisha@gmail.com")
                        .firstName("Jeunice")
                        .lastName("Mwakisha")
                        .nationalId(280L)
                        .phoneNumber(716751979L)
                        .build());
        Transaction transaction2 = new Transaction("O39HDH",3000.00,LocalDate.now(),"Rent",
                Tenant.builder()
                        .email("mwakisha@gmail.com")
                        .firstName("Jeunice")
                        .lastName("Mwakisha")
                        .nationalId(280L)
                        .phoneNumber(716751979L)
                        .build());
        Transaction transaction3 = new Transaction("EMCE4S",3000.00,LocalDate.now(),"Deposit",
                Tenant.builder()
                        .phoneNumber(786L)
                        .email("alice@gmail.com")
                        .nationalId(938L)
                        .lastName("Alice")
                        .firstName("Mumbi")
                        .build());
        Transaction transaction4 = new Transaction("CIO339",3000.00,LocalDate.now(),"Monthly Rent",
                Tenant.builder()
                        .phoneNumber(786L)
                        .email("alice@gmail.com")
                        .nationalId(938L)
                        .lastName("Alice")
                        .firstName("Mumbi")
                        .build());
        Transaction transaction5 = new Transaction("D0LMCC",3000.00,LocalDate.now(),"Deposit",Tenant.builder()
                .phoneNumber(786L)
                .email("alice@gmail.com")
                .nationalId(938L)
                .lastName("Alice")
                .firstName("Mumbi")
                .build());
        List<Transaction> transactions = List.of(transaction1,transaction2,transaction3,transaction4,transaction5);
        transactionRepository.saveAll(transactions);
    }
}
