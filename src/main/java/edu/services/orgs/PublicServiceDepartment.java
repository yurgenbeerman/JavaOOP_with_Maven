package edu.services.orgs;

import edu.communications.Emailable;
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
    private static long lastDepartmentId;
    private long departmentId;
    private BlockingQueue<PublicServant> servants;
    private PublicService publicService;
    private String name;
    private Map<PublicServiceDepartment,String> docsDispatchingTable;

    public PublicServiceDepartment(PublicService publicService, String name) {
        this.publicService = publicService;
        this.name = name;
        servants = new ArrayBlockingQueue<PublicServant>(ExecutionDefaults.MAX_SERVANTS_PER_DEP);
    }

    public String getEmailAddress() {
        //TODO
        return null;
    }

    public void setEmailAddress(String emailAddress) {
        //TODO
    }

    public void addPublicServant(PublicServant servant) {
        servants.add(servant);
    }

    public void addDocToProcess(OrganizationDocument document) {
        //TODO .addDocToProcess(OrganizationDocument document);
    }

    public String getName() {
        return this.name;
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
        return (name.hashCode() + publicService.toString().hashCode());
    }

}
