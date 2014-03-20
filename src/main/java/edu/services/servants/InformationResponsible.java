package edu.services.servants;

import edu.services.docs.*;
import edu.services.execution.ExecutionDefaults;
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

    public void addDocumentToProcess(IncomingDocument document) {
        super.addDocumentToProcess(document);
        document.setIncomingDocResponsible(this);
        document.setIncomingDocResponsible(this);
        ((InformationRequest) document).setInformationResponsible(this);

        processDocument(((InformationRequest) document));
    }

    public void processDocument(InformationRequest document) {
        OutcomingDocument outcomingDocument =
                this.createOutcomingDocument(document);
        document.setReactionDocument(outcomingDocument);
        document.setFinalized(true);
        outcomingDocument.setNextDocumentStatus();

        if (document.isToSendReplyToEmail()) {
            Email email = new Email(getDepartment().getEmailAddress(), document.getAuthor().getEmailAddress(),
                    this.getInformationForReply());
            email.sendEmail();
            outcomingDocument.setDocSentEmail(email);
        }
        if (document.isToSendReplyToPostAddress()) {
            getDepartment().sendDocumentToAddress(outcomingDocument, document.getAuthor().getAddress());
        }
        outcomingDocument.setNextDocumentStatus();

        outcomingDocument.publishToRequester(document.getAuthor());
        getDocumentsToProcess().remove(document);
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
                outcomingDocument.setDocumentName(ExecutionDefaults.OUTCOMING_DOC_NAME);
                return outcomingDocument;
            } else
                throw new IllegalStateException(ExecutionDefaults.DEPARTMENT_IS_NULL);
        } else
            throw new IllegalStateException(ExecutionDefaults.ENVIRONMENT_IS_NULL);
    }
}
