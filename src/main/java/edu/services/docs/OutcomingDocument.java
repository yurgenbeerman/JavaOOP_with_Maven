package edu.services.docs;

import edu.clients.Requester;
import edu.communications.Address;
import edu.services.orgs.PublicService;
import edu.services.servants.PublicServant;

import java.util.GregorianCalendar;

/**
 * Created by yurii.pyvovarenko on 05.03.14.
 */

public class OutcomingDocument extends OrganizationDocument {
    private long initiatingDocId;
    private OrganizationDocument initiatingDocument;
    private Email docSentEmail;
    private Address docSentAddress;
    private GregorianCalendar docSentAddressDate;

    public String isValid() {
        return super.isValid();
    }

    public void setDocSentEmail(Email email) {
        this.docSentEmail = email;
    }

    public GregorianCalendar getDocSentEmailDate() {
        if (null != docSentEmail) {
            return docSentEmail.getEmailSentDate();
        } else {
            return null;
        }
    }

    public String getDocSentEmailAddress() {
        if (null != docSentEmail) {
            return docSentEmail.getEmailToAddresses();
        } else {
            return null;
        }
    }

    public void setDocSentAddress(Address address) {
        this.docSentAddress = address;
    }

    public Address getDocSentAddress() {
        return this.docSentAddress;
    }

    public GregorianCalendar getDocSentAddressDate() {
        return docSentAddressDate;
    }

    public void setDocSentAddressDate(GregorianCalendar docSentAddressDate) {
        this.docSentAddressDate = docSentAddressDate;
    }

    public OutcomingDocument(DocumentType documentType, PublicServant publicServant, PublicService publicService) {
        super(documentType, publicServant, publicService);
    }

    public void publishToRequester(Requester requester) {
        this.isFinalized = true;
        requester.addResponse(this);
    }

    public long getInitiatingDocId() {
        return initiatingDocId;
    }

    public OrganizationDocument getInitiatingDocument() {
        return initiatingDocument;
    }

    /* IMPORTANT! Each doc field setter (modifier method) body must be enclosed by "if (!isFinalized) {}" ! */
    public void setInitiatingDocument(OrganizationDocument initiatingDocument) {
        if (!isFinalized) {
            this.initiatingDocument = initiatingDocument;
            this.initiatingDocId = initiatingDocument.getDocumentId();
        }
    }

    public String getInitiatingDocumentNumber() {
        if (null != initiatingDocument) {
            return this.initiatingDocument.getDocumentNumber();
        } else {
            return DocDefaults.NONE;
        }
    }

    public String toString() {
        String result =
                        "\n    initiatingDocument Number: " + getInitiatingDocumentNumber();
        return super.toString() + result;
    }
}
