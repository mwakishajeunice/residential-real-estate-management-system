package com.jeunice.realestate.bootstrap;

import com.jeunice.realestate.dao.AgentRepository;
import com.jeunice.realestate.dao.HouseRepository;
import com.jeunice.realestate.models.Agent;
import com.jeunice.realestate.models.House;
import com.jeunice.realestate.models.HouseTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PopulateDataAgents implements CommandLineRunner {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("In the run method: ");
        if (agentRepository.count() == 0) {
            populateAgents();
        }
        if (houseRepository.count() == 0) {
            populateHouses();
        }
    }

    private void populateAgents() {
        System.out.println("In the populateAgents method: ");

        Agent agent1 = new Agent(89L, 81475L, "otieno", "chris", "otienochris@gmail.com", 55663L);
        Agent agent2 = new Agent(90L, 70254L, "jeunice", "mwakisha", "mwakisha@gmail.com", 5663L);
        Agent agent3 = new Agent(91L, 4670L, "xavier", "oduor", "frank@gmail.com", 545663L);
        Agent agent4 = new Agent(92L, 54460L, "wachiye", "jeremy", "sirah@gmail.com", 445663L);
        List<Agent> agents = List.of(agent1, agent2, agent3, agent4);
        agentRepository.saveAll(agents);
//        agentRepository.save(agent1);
    }


    private void populateHouses() {
        System.out.println("In the populateHouses method: ");

        House house1 = new House(null, HouseTypes.ONE_BEDROOM, "Gate", 7000.00, "Acacia", "Booked", "A1",new Agent(90L, 70254L, "jeunice", "mwakisha", "mwakisha@gmail.com", 5663L));

        House house2 = new House(null, HouseTypes.SINGLE, "420-Gate", 3500.00, "Lareba", "Available", "A5",new Agent(90L, 70254L, "jeunice", "mwakisha", "mwakisha@gmail.com", 5663L));

        House house3 = new House(null,HouseTypes.BEDSITTER, "Jacaranda-Gate", 4000.00, "Jacaranda Hostels", "Available","4", new Agent(90L, 70254L, "jeunice", "mwakisha", "mwakisha@gmail.com", 5663L));

        House house4 = new House(null, HouseTypes.TWO_BEDROOM, "Mosque-Njokerio", 8000.00, "Starehe", "Booked", "D7", new Agent(92L, 54460L, "wachiye", "jeremy", "sirah@gmail.com", 445663L));

        House house5 = new House(null, HouseTypes.BEDSITTER, "Gate", 6000.00, "Jovpe", "Booked", "V8", new Agent(92L, 54460L, "wachiye", "jeremy", "sirah@gmail.com", 445663L));

        List<House> houses = List.of(house1, house2, house3, house4, house5);
        houseRepository.saveAll(houses);
    }

}
