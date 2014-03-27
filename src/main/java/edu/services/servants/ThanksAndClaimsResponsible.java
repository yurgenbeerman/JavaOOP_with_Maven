package edu.services.servants;

import edu.services.docs.IncomingDocument;
import edu.services.orgs.PublicServiceDepartment;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class ThanksAndClaimsResponsible extends PublicServant {
    String replyToThank;
    String replyToClaim;

    public ThanksAndClaimsResponsible(PublicServiceDepartment department, String surname, String name, String secondName) {
        super(department, surname, name, secondName);
    }

    public String getReplyToThank() {
        return replyToThank;
    }

    public void setReplyToThank(String replyToThank) {
        this.replyToThank = replyToThank;
    }

    public String getReplyToClaim() {
        return replyToClaim;
    }

    public void setReplyToClaim(String replyToClaim) {
        this.replyToClaim = replyToClaim;
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
        if (! (other instanceof ThanksAndClaimsResponsible) )
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
    }
}
