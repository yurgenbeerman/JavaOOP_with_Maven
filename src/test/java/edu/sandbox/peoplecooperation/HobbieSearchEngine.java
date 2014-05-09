package edu.sandbox.peoplecooperation;

import edu.clients.Citizen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lena on 05.05.14.
 */
public class HobbieSearchEngine extends SearchEngine {
    List<Citizen> citizensToSearch;

    public HobbieSearchEngine(List<Citizen> citizensToSearch) {
        this.citizensToSearch = citizensToSearch;
    }

    public List<Citizen> findByHobbie(Citizen citizen) {
        List<Citizen> result = new ArrayList<Citizen>();
        for (Citizen aCitizen : citizensToSearch) {
            if (aCitizen.getHobbie().equals(citizen.getHobbie())) {
                result.add(aCitizen);
            }
        }
        return Collections.unmodifiableList(result);
    }
}
