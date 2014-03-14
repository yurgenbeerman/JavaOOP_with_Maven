package edu.clients;

import edu.services.docs.DocumentLifecycle;
import edu.services.docs.DocumentType;
import edu.services.docs.OutcomingDocument;
import edu.services.servants.InformationResponsible;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 3/11/14.
 */
public class CitizenResponsesTest extends CitizenTestsBasics {
    OutcomingDocument outcomingDocument;

    @Before
    public void createInfoReqDocType_Citizen_PubService() {
        initCitizenAndPublicService();

        String[] outcomingDocLifecycleString = {"Created", "Passed_for_sending", "Sent"};
        DocumentLifecycle outcomingDocLifecycle = new DocumentLifecycle(outcomingDocLifecycleString);
        DocumentType outcomingDocType = new DocumentType("Outcoming_Document", "Out_",outcomingDocLifecycle);
        outcomingDocType.setFinalized(true);

        InformationResponsible informationResponsibleServant =
                new InformationResponsible(publicService, "Karpenko","Petro","Ivanovych");

        outcomingDocument =
                new OutcomingDocument(outcomingDocType, informationResponsibleServant, publicService);
    }

    @Test
    public void GetResponsesTest() throws Exception {
        //given

        //when
        citizen.addResponse(outcomingDocument);

        //then
        org.junit.Assert.assertTrue("Should be not empty string of response", citizen.getResponses().size() > 0);
    }

    @Test
    public void ResponseTextTest() throws Exception {
        //given

        //when
        outcomingDocument.setText("1");
        citizen.addResponse(outcomingDocument);

        //TODO check value of infoResponse.isValid()

        //then
        org.junit.Assert.assertFalse("Response text can not be empty string: " + citizen.getResponses().get(0).getText() + ".",citizen.getResponses().get(0).getText().equals(""));
    }

    @Test
    public void testGetResponsesString() throws Exception {
        //given
        outcomingDocument.setText("responce text");

        //when
        citizen.addResponse(outcomingDocument);

        //then
        org.junit.Assert.assertTrue("Response must contain '" + outcomingDocument.getText() +
                "' but its value is '" + citizen.getResponsesString() + "'",
                citizen.getResponsesString().contains(outcomingDocument.getText()));
    }


    @Test
    public void testAddResponse() throws Exception {
        //given
        citizen.addResponse(outcomingDocument);
        int result = citizen.getResponses().size();

        //when
        citizen.addResponse(outcomingDocument);
        citizen.addResponse(outcomingDocument);

        //then
        org.junit.Assert.assertEquals("Size of responses array should be " + (result + 2) + " but is " + citizen.getResponses().size(), result + 2, citizen.getResponses().size());
    }
}
