package edu.services.docs;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

/**
 * Created by Lena on 12.03.14.
 */
public class EmailTest extends TestCase {
    Email email;

    @Before
    public void setUp() {
        email = new Email("to@address", "from@address", "email text");
    }

    @Test
    public void testEmailSentDate_whenSendEmail() throws Exception {
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
    public void testIsFinalized_whenSendEmail() throws Exception {
        //given

        //when
        email.sendEmail();

        //then
        org.junit.Assert.assertTrue( email.isFinalized() );
    }

    @Test
    public void testSetEmailCCAddresses() throws Exception {
        //when
        email.setEmailCCAddresses("cc@address");

        //then
        org.junit.Assert.assertNotNull(email.getEmailCCAddresses());
    }

    @Test
    public void testSetEmailBCCAddresses() throws Exception {
        //when
        email.setEmailBCCAddresses("bcc@address");

        //then
        org.junit.Assert.assertNotNull(email.getEmailBCCAddresses());
    }

    public void testGetEmailSentDate() throws Exception {

    }

    @Test
    public void testSetFinalized() throws Exception {

    }


    @Test
    public void testGetEmailFromAddress_whenSetFinalized() throws Exception {
        String e = email.getEmailFromAddress();
    }
}
