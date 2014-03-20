package edu.services.execution;

import edu.services.docs.OrganizationDocument;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import java.util.Map;

/**
 * Created by yurii.pyvovarenko on 19.03.14.
 */
public class DepartmentsTasksDispatcher {
//    Queue<OrganizationDocument> documents;
//    List<PublicServant> servants;
//    OrganizationDocument document;
//    PublicServant servant;

    private PublicService publicService;
    private Map<String, PublicServiceDepartment> docsDispatchingTable;

    public DepartmentsTasksDispatcher(PublicService publicService) {
        this.publicService = publicService;
    }

    public void setDocsDispatchingTable(Map<String, PublicServiceDepartment> docsDispatchingTable) {
        this.docsDispatchingTable = docsDispatchingTable;
    }

    public void addDocumentToProcess(OrganizationDocument document) {
        if ( docsDispatchingTable != null ) {
            if (0 != docsDispatchingTable.size()) {
                PublicServiceDepartment department = docsDispatchingTable.get(document.getClass().getName());
                department.addDocToProcess(document);
            } else
                throw new IllegalStateException(ExecutionDefaults.DOCS_DISPATCHING_TABLE_IS_EMPTY);
        } else
            throw new IllegalStateException(ExecutionDefaults.NO_DOCS_DISPATCHING_TABLE);
    }

/*
        this.documents = documents;
        this.servants = servants;
    }

    public OrganizationDocument chooseDocument() {
        if ( documents.size() > 0 ) {
            return documents.element();
        } else {
            throw new IllegalStateException(ExecutionDefaults.NO_DOCS_TO_DISPATCH);
        }
    }

    public PublicServant choosePublicServant() {
        if ( servants.size() > 0 ) {
            servant = servants.get(0);
            String docClass = document.getClass().getName();
            if ( docClass.equals("InformationRequest") ){
                return servant;
            } else {
                throw new InvalidParameterException(ExecutionDefaults.NO_SUCH_DOC_TYPE);

            }
        } else {
            throw new IllegalStateException(ExecutionDefaults.NO_SERVANTS_TO_DISPATCH);

        }
    }
    */
}
