package com.zangmz.hit.medicineneo4j.services;

import com.zangmz.hit.medicineneo4j.utils.RestTicketClient;
import org.springframework.stereotype.Service;

@Service
public class UMLSService {

    RestTicketClient restTicketClient = new RestTicketClient();

    public String getTgt(){
        return restTicketClient.getTgt();
    }

}
