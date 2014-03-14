package edu.services.docs;

import edu.clients.Requester;
import edu.services.orgs.PublicService;
import edu.services.servants.PublicServant;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class IncomingDocument extends OrganizationDocument {
    private long reactionDocumentId;
    private OrganizationDocument reactionDocument;
    private long incomingDocResponsibleId;
    private PublicServant incomingDocResponsible;
    private boolean isReceivedByPublicService;

    public IncomingDocument(DocumentType documentType, Requester author, PublicService publicService) {
        super(documentType, author, publicService);
    }

    public String isValid() {
        return super.isValid();
    }

    public PublicServant getIncomingDocResponsible() {
        return incomingDocResponsible;
    }

    public String getIncomingDocResponsibleName() {
        if (null != incomingDocResponsible) {
            return incomingDocResponsible.getFullNameString();
        } else {
            return DocDefaults.NONE;
        }
    }

    public void setIncomingDocResponsible(PublicServant incomingDocResponsible) {
        if (! isFinalized) {
            this.incomingDocResponsible = incomingDocResponsible;
            this.incomingDocResponsibleId = incomingDocResponsible.getPublicServantId();
        }
    }

    public long getReactionDocumentId() {
        return reactionDocumentId;
    }

    public OrganizationDocument getReactionDocument() {
        return reactionDocument;
    }

    public void setReactionDocument(OrganizationDocument reactionDocument) {
        if (! isFinalized) {
            this.reactionDocument = reactionDocument;
            reactionDocumentId = reactionDocument.getDocumentId();
        }
    }

    public boolean isReceivedByPublicService() {
        return isReceivedByPublicService;
    }

    public void setReceivedByPublicService(boolean isReceivedByPublicService) {
        this.isReceivedByPublicService = isReceivedByPublicService;
    }

    public void setText(String text) {
        if (! isReceivedByPublicService) {
            super.setText(text);
        }
    }

    public String getReactionDocumentNumber() {
        if (null != reactionDocument) {
            return this.reactionDocument.getDocumentNumber();
        } else {
            return DocDefaults.NONE;
        }
    }

    public String toString() {
        String result =
                "\n    isReceivedByPublicService? " + ( (this.isReceivedByPublicService) ? "Yes" : "No" ) +
                "\n    incomingDocResponsible: " + getIncomingDocResponsibleName() +
                "\n    reactionDocument Number: " + getReactionDocumentNumber();
        return super.toString() + result;
    }

}
