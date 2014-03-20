package edu.services.servants;

import edu.services.docs.*;
import edu.services.execution.ExecutionDefaults;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import edu.utils.PublicRequestsUtils;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class InformationResponsible extends PublicServant {
    String informationForReply;

    public InformationResponsible(PublicServiceDepartment department, String surname, String name, String secondName) {
        super(department, surname, name, secondName);
    }

    public String getInformationForReply() {
        return informationForReply;
    }

    public void setInformationForReply(String info) {
        this.informationForReply = new String(info);
    }

    public String getInformationResponsibleName() {
        return super.toString();
    }

    public String toString() {
        return super.toString();
    }

    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (! (other instanceof InformationResponsible) )
            return false;
        return super.equals(other);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public int compareTo(PublicServant other) {
        return super.compareTo(other);
    }

    public void addDocumentToProcess(InformationRequest document) {
        document.setIncomingDocResponsible(this);
        document.setInformationResponsible(this);
        super.addDocumentToProcess(document);
        processDocument(document);
    }

    public void processDocument(InformationRequest document) {
        OutcomingDocument outcomingDocument =
                this.createOutcomingDocument(document);
        document.setReactionDocument(outcomingDocument);

        /* TODO:
            reactionDocument Number: NONE
            InformationResponsible: NONE
         */


        outcomingDocument.publishToRequester(document.getAuthor());
        outcomingDocument.setNextDocumentStatus();
        document.setNextDocumentStatus();
        document.setFinalized(true);

        if (document.isIfSendReplyToEmail()) {
            Email email = new Email(getDepartment().getEmailAddress(), document.getAuthor().getEmailAddress(),
                    this.getInformationForReply());
            email.sendEmail();
            outcomingDocument.setDocSentEmail(email);
            System.out.println("\nThe Response (Outcoming Doc) was sent to Email: " + outcomingDocument.getDocSentEmailAddress() +
                    " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentEmailDate()));
        }

        if (document.isIfSendReplyToPostAddress()) {
            getDepartment().sendDocumentToAddress(outcomingDocument, document.getAuthor().getAddress());
            System.out.println("\nThe Response (Outcoming Doc) was sent to Address: " + outcomingDocument.getDocSentAddress() +
                    " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentAddressDate()));
        }

        outcomingDocument.setNextDocumentStatus();
        outcomingDocument.setFinalized(true);
        //TODO: must set outcomingDocument.status to the last one "Sent" -- find out why it doesn't work!

        getDocumentsToProcess().remove(document);
        document.setNextDocumentStatus();
    }

    private OutcomingDocument createOutcomingDocument(IncomingDocument initiatingDocument) {
        if (getEnvironment() != null ) {
            if (getDepartment() != null ) {
                OutcomingDocument outcomingDocument =
                        new OutcomingDocument(
                                getEnvironment().getOutcomingDocType(),
                                this,
                                getDepartment().getPublicService()
                        );
                outcomingDocument.setText(this.getInformationForReply());
                initiatingDocument.setReactionDocument(outcomingDocument);
                outcomingDocument.setInitiatingDocument(initiatingDocument);
                outcomingDocument.setNextDocumentStatus();
                outcomingDocument.setDocumentName(ExecutionDefaults.OUTCOMING_DOC_NAME);
                return outcomingDocument;
            } else
                throw new IllegalStateException(ExecutionDefaults.DEPARTMENT_IS_NULL);
        } else
            throw new IllegalStateException(ExecutionDefaults.ENVIRONMENT_IS_NULL);
    }
}
