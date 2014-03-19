package edu.services.execution;

import edu.services.docs.OrganizationDocument;
import edu.services.servants.PublicServant;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

/**
 * Created by yurii.pyvovarenko on 19.03.14.
 */
public class TaskDispatcherByTopics implements TasksDispatcher {
    Queue<OrganizationDocument> documents;
    List<PublicServant> servants;
    OrganizationDocument document;
    PublicServant servant;

    public TaskDispatcherByTopics(Queue<OrganizationDocument> documents, List<PublicServant> servants) {
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
}
