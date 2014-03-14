package edu.clients;

import edu.services.orgs.PublicService;

/**
 * Created by yurii.pyvovarenko on 3/11/14.
 */
public class CitizenTestsBasics {
    Citizen citizen;
    PublicService publicService;

    public void initCitizenAndPublicService() {
        citizen = new Citizen("surname", "name", "secondName");
        publicService = new PublicService("Improvements service");
    }
}
