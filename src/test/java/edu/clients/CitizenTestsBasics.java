package edu.clients;

import edu.services.orgs.PublicService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 3/11/14.
 */
public class CitizenTestsBasics {
    Citizen citizen;
    PublicService publicService;

    @Test
    public void shouldBeNotNullCitizenWhenConstructed() {
        //given

        //when
        citizen = new Citizen("surname", "name", "secondName");

        //then
        Assert.assertNotNull(citizen);
    }

    @Test
    public void shouldBeNotNullPublicServiceWhenConstructed() {
        //given

        //when
        publicService = new PublicService("Improvements service");

        //then
        Assert.assertNotNull(publicService);
    }
}
