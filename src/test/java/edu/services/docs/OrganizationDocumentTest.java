package edu.services.docs;

import org.junit.Test;

/**
 * Created by yurii.pyvovarenko on 13.03.14.
 */
public class OrganizationDocumentTest {

    @Test
    public void shouldGetDocumentCreationDate() throws Exception {
        //given
        OrganizationDocument doc = new OrganizationDocument(null,null,null);

        //when

        //then
        org.junit.Assert.assertNotNull("getDocumentCreationDate = ", doc.getDocumentCreationDate());
    }
}
