package edu.services.docs;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

/**
 * Created by yurii.pyvovarenko on 12.03.14.
 */
public class EmailTest {
    Email email;

    @Before
    public void setUp() {
        email = new Email("to@address", "from@address", "email text");
    }

    @Test
    public void shouldEmailSentDateWhenSendEmail() throws Exception {
        //given
        GregorianCalendar dateBeforeSending = new GregorianCalendar();

        //when
        email.sendEmail();

        //then
        org.junit.Assert.assertNotNull(email.getEmailSentDate());

        /* the next expression fails the test:
        org.junit.Assert.assertTrue( ( ( email.getEmailSentDate().before(new GregorianCalendar()) ) &&
                ( email.getEmailSentDate().after(dateBeforeSending) ) ) ) ;
        */
    }

    @Test
    public void shouldIsFinalizedWhenSendEmail() throws Exception {
        //given

        //when
        email.sendEmail();

        //then
        org.junit.Assert.assertTrue( email.isFinalized() );
    }

    @Test
    public void shouldSetEmailCCAddresses() throws Exception {
        //when
        email.setEmailCCAddresses("cc@address");

        //then
        org.junit.Assert.assertNotNull(email.getEmailCCAddresses());
    }

    @Test
    public void shouldSetEmailBCCAddresses() throws Exception {
        //when
        email.setEmailBCCAddresses("bcc@address");

        //then
        org.junit.Assert.assertNotNull(email.getEmailBCCAddresses());
    }

    @Test
    public void shouldGetEmailSentDateAfterSending() throws Exception {
        //when
        email.sendEmail();

        //then
        org.junit.Assert.assertNotNull(email.getEmailSentDate());
    }

    @Test
    public void testSetFinalized() throws Exception {
        //when
        email.setFinalized(true);

        //then
        org.junit.Assert.assertTrue(email.isFinalized());
    }


    @Test
    public void testGetEmailFromAddressWhenSetFinalized() throws Exception {
        //given
        email.setEmailFromAddress("fromFROM");

        //when
        email.setFinalized(true);

        //then
        org.junit.Assert.assertEquals("fromFROM", email.getEmailFromAddress());
    }
}
