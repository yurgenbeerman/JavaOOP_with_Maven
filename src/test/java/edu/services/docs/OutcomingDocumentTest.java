package edu.services.docs;

import edu.clients.Citizen;
import edu.clients.Requester;
import edu.services.orgs.PublicService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 13.03.14.
 */


public class OutcomingDocumentTest extends DocsTestsBasics {


    /*
        IncomingDocument incomingDoc;
    OutcomingDocument outcomingDoc;

    @Before
    public void createDocsForTests() {
        Requester requester = new Citizen("Petrenko","Taras","Ivanovych");
        DocumentLifecycle infoRequestLifecycle = new DocumentLifecycle({"Created"});
        PublicService publicService = new PublicService("Improvements service");
        incomingDoc = new InformationRequest(infoRequestLifecycle,requester,);
        outcomingDoc = new OutcomingDocument(infoRequestLifecycle,requester,null);
    }

    DocumentLifecycle infoRequestLifecycle

    @Before
    public void createLinearLifecycleFinalized() {
        DocumentLifecycle infoRequestLifecycle = new DocumentLifecycle({"Created"});
        infoRequestLifecycle.setFinalized(true);
    }
    */
    @Test
    public void shouldGetInitiatingDocIdAppropriateValue() throws Exception {
        //given

        //when
        outcomingDoc.setInitiatingDocument(incomingDoc);

        //then
        org.junit.Assert.assertEquals(outcomingDoc.getInitiatingDocId(), incomingDoc.getDocumentId());
    }

    @Test
    public void shouldGetInitiatingDocumentSameObject() throws Exception {
        //given

        //when
        outcomingDoc.setInitiatingDocument(incomingDoc);

        //then
        org.junit.Assert.assertSame(outcomingDoc.getInitiatingDocument(), incomingDoc);
    }


}
