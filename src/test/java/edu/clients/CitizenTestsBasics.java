package edu.clients;

import edu.services.PublicServiceDemo;
import edu.services.orgs.PublicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 3/11/14.
 */
public class CitizenTestsBasics {
    Citizen citizen;
    PublicService publicService;

    @Before
    public void initCitizenAndPublicService() {
        citizen = (Citizen) PublicServiceDemo.createValidCitizenRequester();
        publicService = PublicServiceDemo.createValidPublicService();
    }

    @Test
    public void shouldBeNotNullCitizenWhenConstructed() {
        //given

        //when

        //then
        Assert.assertNotNull(citizen);
    }

    @Test
    public void shouldBeNotNullPublicServiceWhenConstructed() {
        //given

        //when

        //then
        Assert.assertNotNull(publicService);
    }
}
