package edu.clients;
import edu.services.docs.DocumentLifecycle;
import edu.services.docs.DocumentType;
import edu.services.docs.IncomingDocument;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 3/11/14.
 */
public class CitizenRequestsTest extends CitizenTestsBasics {
    IncomingDocument infoRequest;

    @Before
    public void createInfoReqDocType_Citizen_PubService() {
        initCitizenAndPublicService();

        String[] infoRequestLifecycleString = {"Created", "Assigned", "Replied"};
        DocumentLifecycle infoRequestLifecycle = new DocumentLifecycle(infoRequestLifecycleString);
        infoRequestLifecycle.setFinalized(true);

        DocumentType infoRequestDocType = new DocumentType("Information_Request", "InfoReq_",infoRequestLifecycle);
        infoRequestDocType.setFinalized(true);
        infoRequest = new IncomingDocument(infoRequestDocType, citizen, publicService);
    }


    @Test
    public void shouldBeOneRequest_whenFirstAddRequest() throws Exception {
        //given

        //when
        citizen.addRequest(infoRequest);

        //then
        org.junit.Assert.assertEquals("Size of requests array should be 1", 1, citizen.getRequests().size());
    }

    @Test
    public void shouldIncreaseRequestsNumber_whenAddRequest() throws Exception {
        //given
        citizen.addRequest(infoRequest);
        int result = citizen.getRequests().size();

        //when
        citizen.addRequest(infoRequest);
        citizen.addRequest(infoRequest);

        //then
        org.junit.Assert.assertEquals("Size of requests array should be " + (result + 2) + " but is " + citizen.getRequests().size(), result + 2, citizen.getRequests().size());

    }

    @Test
    public void shouldBeNotEmptyString_whenGetRequestsString() throws Exception {
        //given

        //when
        citizen.addRequest(infoRequest);

        //then
        org.junit.Assert.assertNotNull("After addition the first request should not be null", citizen.getRequests().get(0));
    }

    @Test

    public void shouldNotFail_whenGetRequestsString_and_NoRequestsAdded() throws java.lang.IndexOutOfBoundsException {
        //given

        //when

        //then
        Assert.assertNull("citizen.getRequests() = " + citizen.getRequests(), citizen.getRequests());
    }

    @Test
    public void RequestTextTest() throws Exception {
        //given

        //when
        infoRequest.setText("1");
        citizen.addRequest(infoRequest);

        //TODO check value of infoRequest.isValid()

        //then
        org.junit.Assert.assertFalse("Request text can not be empty string: " + citizen.getRequests().get(0).getText() + ".",citizen.getRequests().get(0).getText().equals(""));
    }

    @Test
    public void getRequestsStringTest() throws Exception {
        //given

        //when
        citizen.addRequest(infoRequest);
        infoRequest.setText("1");


        //then
        org.junit.Assert.assertFalse("After addition the first request should not be empty string: " + citizen.getRequestsString() + ".", citizen.getRequestsString().equals(""));
    }

    @Test
    public void testGetCitizenId() throws Exception {
        //given

        //when

        //then
        Assert.assertTrue("citizenId should be > 0", citizen.getId() > 0);
    }
}
