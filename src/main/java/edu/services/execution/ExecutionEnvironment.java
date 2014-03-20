package edu.services.execution;

import edu.clients.Requester;
import edu.services.docs.DocumentType;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;

/**
 * Created by yurii.pyvovarenko on 20.03.14.
 */
public class ExecutionEnvironment {
    private DocumentType outcomingDocType;
    private DocumentType infoRequestDocType;
    private Requester requester;
    PublicService publicService;
    PublicServiceDepartment infoRequestsDepartment;

    public DocumentType getInfoRequestDocType() {
        return infoRequestDocType;
    }

    public void setInfoRequestDocType(DocumentType infoRequestDocType) {
        this.infoRequestDocType = infoRequestDocType;
    }

    public Requester getRequester() {
        return requester;
    }

    public void setRequester(Requester requester) {
        this.requester = requester;
    }

    public PublicService getPublicService() {
        return publicService;
    }

    public void setPublicService(PublicService publicService) {
        this.publicService = publicService;
    }

    public DocumentType getOutcomingDocType() {
        return outcomingDocType;
    }

    public void setOutcomingDocType(DocumentType outcomingDocType) {
        this.outcomingDocType = outcomingDocType;
    }

    public PublicServiceDepartment getInfoRequestsDepartment() {
        return infoRequestsDepartment;
    }

    public void setInfoRequestsDepartment(PublicServiceDepartment infoRequestsDepartment) {
        this.infoRequestsDepartment = infoRequestsDepartment;
    }
}
