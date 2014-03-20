package edu.services.servants;

import edu.services.docs.*;
import edu.services.execution.ExecutionDefaults;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;

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
        document.setIncomingDocResponsible(this);
        super.addDocumentToProcess(document);
        processDocument(document);
    }

    public void processDocument(IncomingDocument document) {
        OutcomingDocument outcomingDocument =
                this.createOutcomingDocument(document);

        outcomingDocument.publishToRequester(document.getAuthor());
        outcomingDocument.setNextDocumentStatus();
        document.setNextDocumentStatus();
        document.setFinalized(true);



        getDocumentsToProcess().remove(document);
        document.setNextDocumentStatus();
    }

    private OutcomingDocument createOutcomingDocument(IncomingDocument initiatingDocument) {
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
    }
}
