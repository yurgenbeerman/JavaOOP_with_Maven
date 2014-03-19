package edu.services.servants;

import edu.clients.Citizen;
import edu.services.docs.OrganizationDocument;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class PublicServant extends Citizen {
    private static long lastPublicServantId;
    private long publicServantId;
    private PublicServiceDepartment department;
    private PriorityBlockingQueue<OrganizationDocument> documentsToProcess;


    public PublicServant(PublicServiceDepartment department, String surname, String name, String secondName) {
        super(surname, name, secondName);

        this.publicServantId = PublicServant.lastPublicServantId;
        PublicServant.lastPublicServantId++;

        this.department = department;
        department.addPublicServant(this);
    }

    public String toString() {
        return this.getFullNameString();
    }

    //equals(), hashCode
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (! (other instanceof PublicServant) )
            return false;
        PublicServant otherPublicServant = (PublicServant) other;
        return (
                    (publicServantId == otherPublicServant.publicServantId) &&
                    (department == otherPublicServant.department) &&
                    super.equals(otherPublicServant)
                );
    }

    public int hashCode() {
        return (super.hashCode() +
                ((Long) publicServantId).toString().hashCode() +
                department.toString().hashCode());
    }

    public void addDocumentToProcess(OrganizationDocument document) {
        documentsToProcess.add(document);
    }

    public int compareTo(PublicServant other) {
        if ( this.getId() == other.getId() )
            return 0;
        return ( this.getId() > other.getId() ) ? 1 : -1;
    }
}
