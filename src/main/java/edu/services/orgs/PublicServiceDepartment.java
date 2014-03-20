package edu.services.orgs;

import edu.communications.Emailable;
import edu.services.docs.IncomingDocument;
import edu.services.docs.OrganizationDocument;
import edu.services.execution.ExecutionDefaults;
import edu.services.servants.PublicServant;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by yurii.pyvovarenko on 19.03.14.
 */
public class PublicServiceDepartment implements Emailable {
    private static long nextDepartmentId = 0;
    private long departmentId;
    private BlockingQueue<PublicServant> servants;
    private PublicService publicService;
    private String name;
    private Map<PublicServiceDepartment,String> docsDispatchingTable;
    private String emailAddress;

    public PublicServiceDepartment() {
        this.setDepartmentId(this);
    }

    public PublicServiceDepartment(PublicService publicService, String name) {
        this();
        this.publicService = publicService;
        this.name = name;
        servants = new ArrayBlockingQueue<PublicServant>(ExecutionDefaults.MAX_SERVANTS_PER_DEP);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addPublicServant(PublicServant servant) {
        servants.add(servant);
    }

    public BlockingQueue<PublicServant> getPublicServants() {
        return servants;
    }

    public void addDocToProcess(OrganizationDocument document) {
        //TODO .addDocToProcess(OrganizationDocument document);
        ((IncomingDocument) document).setPublicServiceDepartment(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.getName();
    }

    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (! (other instanceof PublicServiceDepartment) )
            return false;
        PublicServiceDepartment otherPublicServiceDepartment = (PublicServiceDepartment) other;
        return (name == null) ?
                (otherPublicServiceDepartment.name == null) :
                (name.equals(otherPublicServiceDepartment.name) &&
                        publicService.equals(otherPublicServiceDepartment.publicService)
                );
    }

    public int hashCode() {
        return (name.hashCode() + publicService.toString().hashCode() + ((Long) departmentId).toString().hashCode());
    }

    private static void setDepartmentId(PublicServiceDepartment department) {
        department.departmentId = nextDepartmentId;
        nextDepartmentId++;
    }
}
