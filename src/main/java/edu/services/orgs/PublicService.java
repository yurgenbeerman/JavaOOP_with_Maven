package edu.services.orgs;

import edu.communications.Address;
import edu.communications.Emailable;
import edu.services.docs.OrganizationDocument;
import edu.services.docs.OutcomingDocument;
import edu.services.execution.DepartmentsTasksDispatcher;
import edu.services.execution.ExecutionDefaults;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class PublicService extends PriorityBlockingQueue<PublicServiceDepartment> implements Emailable {
    private static long lastOrgId;

    private long orgId;
    private Address address;
    private String orgName;
    private int hierarchyLevel;
    private long parentOrgId;
    private String emailAddress;
    private DepartmentsTasksDispatcher departmentsTasksDispatcher;

    private Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable;

    public PublicService(String orgName) {
        this.orgId = PublicService.lastOrgId;
        PublicService.lastOrgId++;
        setOrgName(orgName);
        DepartmentsTasksDispatcher departmentsTasksDispatcher = new DepartmentsTasksDispatcher(this);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(int hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public Long getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Long parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public void sendDocumentToAddress(OutcomingDocument outcomingDocument, Address address) {
        outcomingDocument.setDocSentAddress(address);
        outcomingDocument.setDocSentAddressDate(new GregorianCalendar());
    }

    public String toString() {
        return getOrgName() + ": " +
                "\n    address: " + this.getAddress() +
                "\n    emailAddress: " + this.getEmailAddress() +
                "\n    orgId: " + this.getOrgId() +
                "\n    parentOrgId: " + this.getParentOrgId() +
                "\n    hierarchyLevel: " + this.getHierarchyLevel();
    }

    public void createDocsDispatcher(Map<String, PublicServiceDepartment> docsDispatchingTable) {
        departmentsTasksDispatcher = new DepartmentsTasksDispatcher(this);
        departmentsTasksDispatcher.setdocsToServantsDispatchingTable(docsDispatchingTable);
    }

    public void addDocomentToProcess(OrganizationDocument document) {
        departmentsTasksDispatcher.addDocumentToProcess(document);
    }

    public Map<String, PublicServiceDepartment> getDocsToDepartmentsDispatchingTable() {
        return docsToDepartmentsDispatchingTable;
    }

    public void setDocsToDepartmentsDispatchingTable(Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable) {
        this.docsToDepartmentsDispatchingTable = docsToDepartmentsDispatchingTable;
    }
    public void addDocumentToProcess(OrganizationDocument document) {
        if ( docsToDepartmentsDispatchingTable != null ) {
            if (0 != docsToDepartmentsDispatchingTable.size()) {
                PublicServiceDepartment department = docsToDepartmentsDispatchingTable
                        .get(document.getClass().getName());
                department.addDocToProcess(document);
                document.setCurrentPublicServiceDepartment(department);
            } else
                throw new IllegalStateException(ExecutionDefaults.DOCS_DISPATCHING_TABLE_IS_EMPTY);
        } else
            throw new IllegalStateException(ExecutionDefaults.NO_DOCS_DISPATCHING_TABLE);
    }
}
