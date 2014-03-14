package edu.services.docs;

import edu.clients.Requester;
import edu.services.orgs.PublicService;
import edu.services.servants.InformationResponsible;

/**
 * Created by yurii.pyvovarenko on 05.03.14.
 */
public final class InformationRequest extends IncomingDocument {
    private String addressForReply;
    private String emailForReply;
    private InformationResponsible informationResponsible;

    public InformationRequest(DocumentType documentType, Requester author, PublicService publicService) {
        super(documentType, author, publicService);
        informationResponsible = (InformationResponsible) super.getIncomingDocResponsible();
        documentType.setDocTypeInUse(true);
        setDocumentName(documentType.getDocTypeName() + " #" + this.getDocumentId());
    }

    public String isValid() {
        return super.isValid();
    }

    public String getAddressForReply() {
        return addressForReply;
    }

    public void setAddressForReply(String addressForReply) {
        if (! isReceivedByPublicService()) {
            this.addressForReply = addressForReply;
        }
    }

    public String getEmailForReply() {
        return emailForReply;
    }

    public void setEmailForReply(String emailForReply) {
        if (! isReceivedByPublicService()) {
            this.emailForReply = emailForReply;
        }
    }

    public InformationResponsible getInformationResponsible() {
        return informationResponsible;
    }

    public void setInformationResponsible(InformationResponsible informationResponsible) {
        this.informationResponsible = informationResponsible;
    }

    public String getInformationResponsibleName() {
        if (null != informationResponsible){
          return this.informationResponsible.getInformationResponsibleName();
        } else {
            return DocDefaults.NONE;
        }
    }

    public String toString() {
        String result =
                "\n    addressForReply: " + this.getAddressForReply() +
                "\n    emailForReply: " + this.getEmailForReply() +
                "\n    InformationResponsible: " + this.getInformationResponsibleName();
        return super.toString() + result;
    }
}
