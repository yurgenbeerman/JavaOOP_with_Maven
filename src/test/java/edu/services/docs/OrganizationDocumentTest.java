package edu.services.docs;

import junit.framework.TestCase;

import java.util.GregorianCalendar;

/**
 * Created by Lena on 13.03.14.
 */
public class OrganizationDocumentTest {
    public void testGetDocumentCreationDate() throws Exception {
        //TODO improve
        OrganizationDocument doc = new OrganizationDocument();
        GregorianCalendar cd = doc.getDocumentCreationDate();
    }
}
