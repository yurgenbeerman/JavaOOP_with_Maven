package edu.services.execution;

import edu.services.docs.OrganizationDocument;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import edu.services.servants.PublicServant;

import java.util.Map;

/**
 * Created by yurii.pyvovarenko on 19.03.14.
 */
public class DepartmentsTasksDispatcher {
    private PublicService publicService;
    private Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable;

    public DepartmentsTasksDispatcher(PublicService publicService) {
        this.publicService = publicService;
    }

    public void setdocsToDepartmentsDispatchingTable(Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable) {
        this.docsToDepartmentsDispatchingTable = docsToDepartmentsDispatchingTable;
    }

    public void addDocumentToProcess(OrganizationDocument document) {
        if ( docsToDepartmentsDispatchingTable != null ) {
            if (0 != docsToDepartmentsDispatchingTable.size()) {
                PublicServiceDepartment department =
                        docsToDepartmentsDispatchingTable.get(document.getClass().getName());
                department.addDocToProcess(document);
            } else
                throw new IllegalStateException(ExecutionDefaults.DOCS_DISPATCHING_TABLE_IS_EMPTY);
        } else
            throw new IllegalStateException(ExecutionDefaults.NO_DOCS_DISPATCHING_TABLE);
    }

    public PublicService getPublicService() {
        return publicService;
    }
}
