package edu.services.docs;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yurii.pyvovarenko on 05.03.14.
 *
 * Most of setters must check if isFinalized "if (! isEmailSent)"
 */
public final class Email extends Text {
    private String emailFromAddress;
    private String emailToAddresses;
    private String emailCCAddresses;
    private String emailBCCAddresses;
    private GregorianCalendar emailSentDate;

    public Email(String emailFromAddress, String emailToAddress, String emailText) {
        /* TODO validate passed parameters:
         *   areEmailAddressesValid(emailFromAddress)
         *   areEmailAddressesValid(emailToAddress)
         *   isEmailTextValid(emailText)
         *
         *   Found two best validators:
         *     1) http://commons.apache.org/proper/commons-validator/download_validator.cgi
         *     2) https://java.net/projects/javamail/pages/Home#Download_JavaMail_1.5.1_Release
         */
        setEmailFromAddress(emailFromAddress);
        setEmailToAddresses(emailToAddress);
        setText(emailText);
    }

    public void sendEmail() {
        //TODO implement email sending
        this.emailSentDate = new GregorianCalendar();
        this.isFinalized = true;
    }

    public String getEmailFromAddress() {
        return emailFromAddress;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        if (! isFinalized) {
            this.emailFromAddress = emailFromAddress;
        }
    }

    public String getEmailToAddresses() {
        return emailToAddresses;
    }

    public void setEmailToAddresses(String emailToAddresses) {
        if (! isFinalized) {
            this.emailToAddresses = emailToAddresses;
        }
    }

    public String getEmailCCAddresses() {
        return emailCCAddresses;
    }

    public void setEmailCCAddresses(String emailCCAddresses) {
        if (! isFinalized) {
            this.emailCCAddresses = emailCCAddresses;
        }
    }

    public String getEmailBCCAddresses() {
        return emailBCCAddresses;
    }

    public void setEmailBCCAddresses(String emailBCCAddresses) {
        if (! isFinalized) {
            this.emailBCCAddresses = emailBCCAddresses;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (! isFinalized) {
            this.text = text;
        }
    }

    public GregorianCalendar getEmailSentDate() {
        return emailSentDate;
    }
}
