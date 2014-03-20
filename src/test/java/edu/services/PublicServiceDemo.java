package edu.services;

import edu.clients.Citizen;
import edu.clients.Requester;
import edu.communications.Address;
import edu.services.docs.*;
import edu.services.execution.ExecutionDefaults;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import edu.services.servants.InformationResponsible;
import edu.services.servants.PublicServant;
import edu.utils.PublicRequestsUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yurii.pyvovarenko on 05.03.14.
 *
 * Implements main workfllow of the Public Service System
 */
public class PublicServiceDemo {
    static int statusNumber = 0;
    public static void main (String[] args) {
        System.out.println("------------- PublicServiceDemo ------------- ");

        /* INITIALIZATION start */
        DocumentType infoRequestDocType = createInfoRequestDocType();
        DocumentType outcomingDocType = createOutcomingDocType();

        Requester requester = createValidCitizenRequester();

        PublicService publicService = createValidPublicService();
        /* INITIALIZATION end */

        /* Assume a user (Citizen wants to create s Request */
        if ( ! ExecutionDefaults.isRequesterOfficialIdValid(requester) )
            throw new IllegalStateException(ExecutionDefaults.REQUESTER_OFFICIAL_ID_IS_INVALID);

        InformationRequest infoRequest0 = createInformationRequest(infoRequestDocType, requester, publicService);

        assert (infoRequest0.getValidityString().equals(DocDefaults.VALID));

        PublicServiceDepartment infoRequestsDep = new PublicServiceDepartment(publicService,"infoRequestsDep_0");
        PublicServiceDepartment thanksAndClaimsDep = new PublicServiceDepartment(publicService,"ThanksAndClaimsDep_1");

        /* The user can modify the Request data until it isReceivedByPublicService */
        infoRequest0.setReceivedByPublicService(true);
        InformationResponsible informationResponsibleServant =
                new InformationResponsible(infoRequestsDep, "Karpenko","Petro","Ivanovych");
        informationResponsibleServant.setInformationForReply(
                "The plan of improvements for 2014 is next... Sincerely, InformationResponsible."); //TODO Remove stubs



        PublicServant publicServant0 = new InformationResponsible(infoRequestsDep, "Karpenko0","Petro0","Ivanovych0");
        PublicServant publicServant1 = new InformationResponsible(infoRequestsDep, "Karpenko1", "Petro1", "Ivanovych1");
        PublicServant publicServant2 = new InformationResponsible(infoRequestsDep, "Karpenko2", "Petro2", "Ivanovych2");

        //TODO implement TasksDispatcher tasksDispatcher = new TaskDispatcherByType(unprocessedIncomingDocsQueue, PublicServantsList);
        publicServant0.addDocumentToProcess(infoRequest0);
        publicServant1.addDocumentToProcess(infoRequest0);
        publicServant0.addDocumentToProcess(infoRequest0);
        publicServant1.addDocumentToProcess(infoRequest0);
//        publicServant2.addDocumentToProcess(thanks0);
//        publicServant2.addDocumentToProcess(claims0);

        Map<String, PublicServiceDepartment> docsDispatchingTable = new HashMap<String, PublicServiceDepartment>();
        docsDispatchingTable.put("InformationRequest", infoRequestsDep);
        docsDispatchingTable.put("Claim", thanksAndClaimsDep);
        docsDispatchingTable.put("Thank", thanksAndClaimsDep);

        //TODO dispatch all docs to all servants
        //  create executed tasks map
        //TODO execute all tasks by all servants =
        //  create outcoming doc
        //      and assign replies to it
        //      and set dependent docsIds to incoming and outcoming docs
        //      and publish outcoming doc
        //      and send it via email and via post
        //      and remove incoming doc from the unprocessedIncomingDocsQueue


        infoRequest0.setIncomingDocResponsible(informationResponsibleServant);
        System.out.println("informationResponsibleServant " + informationResponsibleServant
                + "was assigned to the infoRequest and prepared the Response: "
                + informationResponsibleServant.getInformationForReply());

        printStatusAndAssignee(infoRequest0, infoRequest0.getIncomingDocResponsibleName());


        OutcomingDocument outcomingDocument = createOutcomingDocument(outcomingDocType, publicService, infoRequest0, informationResponsibleServant);

        outcomingDocument.publishToRequester(requester);
        outcomingDocument.setNextDocumentStatus();
        infoRequest0.setNextDocumentStatus();
        infoRequest0.setFinalized(true);

        printStatusAndAssignee(infoRequest0, infoRequest0.getAuthorName());


        if (infoRequest0.isIfSendReplyToEmail()) {
            Email email = new Email(publicService.getEmailAddress(), requester.getEmailAddress(),
                    informationResponsibleServant.getInformationForReply());
            email.sendEmail();
            outcomingDocument.setDocSentEmail(email);
            System.out.println("\nThe Response (Outcoming Doc) was sent to Email: " + outcomingDocument.getDocSentEmailAddress() +
                    " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentEmailDate()));
        }

        if (infoRequest0.isIfSendReplyToPostAddress()) {
            publicService.sendDocumentToAddress(outcomingDocument, requester.getAddress());
            System.out.println("\nThe Response (Outcoming Doc) was sent to Address: " + outcomingDocument.getDocSentAddress() +
                    " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentAddressDate()));
        }
        outcomingDocument.setNextDocumentStatus();
        outcomingDocument.setFinalized(true);
        //TODO: must set outcomingDocument.status to the last one "Sent" -- find out why it doesn't work!

        System.out.println("\ninfoRequest statuses history: " + infoRequest0.getStatusesHistoryString());
        System.out.println("\ncitizen sent the next requests:\n   " + requester.getRequestsString());
        System.out.println("\ncitizen got the next responses:\n   " + requester.getResponsesString());

    }

    public static OutcomingDocument createOutcomingDocument(DocumentType outcomingDocType, PublicService publicService, InformationRequest infoRequest0, InformationResponsible informationResponsibleServant) {
        OutcomingDocument outcomingDocument =
                new OutcomingDocument(outcomingDocType, informationResponsibleServant, publicService);
        outcomingDocument.setText(informationResponsibleServant.getInformationForReply());
        infoRequest0.setReactionDocument(outcomingDocument);
        outcomingDocument.setInitiatingDocument(infoRequest0);
        outcomingDocument.setNextDocumentStatus();
        outcomingDocument.setDocumentName("Response to Information request");
        return outcomingDocument;
    }

    public static PublicService createValidPublicService() {
        PublicService publicService = createPublicService();
        Address pubServiceAddress = createPubServiceAddress();
        publicService.setAddress(pubServiceAddress);
        return publicService;
    }

    public static Requester createValidCitizenRequester() {
        Requester requester = createRequesterCitizen();
        Address requesterAddress = createCitizenAddress();
        requester.setAddress(requesterAddress);
        return requester;
    }

    public static InformationRequest createInformationRequest(DocumentType infoRequestDocType, Requester requester, PublicService publicService) {
        InformationRequest infoRequest0 =
                new InformationRequest(infoRequestDocType, requester, publicService);
        infoRequest0.setText("What parks and streets improvements are planned for 2014 in Kyiv?");
        infoRequest0.setIfSendReplyToPostAddress(true);
        infoRequest0.setAddressForReply(requester.getAddressString());
        infoRequest0.setIfSendReplyToEmail(true);
        infoRequest0.setEmailForReply(requester.getEmailAddress());
        return infoRequest0;
    }

    public static PublicService createPublicService() {
        PublicService publicService = new PublicService("Improvements service");
        publicService.setHierarchyLevel(1);
        publicService.setParentOrgId((long)0);
        publicService.setOrgId((long)0);
        publicService.setEmailAddress("contact@improvements.service.com");
        return publicService;
    }

    public static Address createPubServiceAddress() {
        Address pubServiceAddress = new Address();
        pubServiceAddress.setCountry("Ukraine");
        pubServiceAddress.setRegion("Kyivska obl.");
        pubServiceAddress.setCity("Kyiv");
        pubServiceAddress.setCityArea("Shevchenkivski");
        pubServiceAddress.setStreet("Saksaganskogo");
        pubServiceAddress.setBuilding("104");
        pubServiceAddress.setZipCode("01002");
        return pubServiceAddress;
    }

    public static Requester createRequesterCitizen() {
        Requester requester = new Citizen("Petrenko","Taras","Ivanovych");
        requester.setEmailAddress("citizen@gmail.com");
        requester.setOfficialId("1234567890");
        return requester;
    }

    public static Address createCitizenAddress() {
        Address citizenAddress = new Address();
        citizenAddress.setCountry("Ukraine");
        citizenAddress.setRegion("Kyivska obl.");
        citizenAddress.setCity("Kyiv");
        citizenAddress.setCityArea("Shevchenkivski");
        citizenAddress.setStreet("Khreshchatyk");
        citizenAddress.setBuilding("1A");
        citizenAddress.setApartment("123H");
        citizenAddress.setZipCode("01001");
        return citizenAddress;
    }

    public static DocumentType createOutcomingDocType() {
        DocumentLifecycle outcomingDocLifecycle =
                createLinearLifecycleFinalized("Created", "Passed_for_sending", "Sent");
        DocumentType outcomingDocType = new DocumentType("Outcoming_Document", "Out_",outcomingDocLifecycle);
        outcomingDocType.setFinalized(true);
        return outcomingDocType;
    }

    public static DocumentType createInfoRequestDocType() {
        DocumentLifecycle infoRequestLifecycle =
                createLinearLifecycleFinalized("Created", "Assigned", "Replied");
        DocumentType infoRequestDocType = new DocumentType("Information_Request", "InfoReq_",infoRequestLifecycle);
        infoRequestDocType.setFinalized(true);
        return infoRequestDocType;
    }

    public static DocumentLifecycle createLinearLifecycleFinalized(String... orgDocStatusesStrings) {
        DocumentLifecycle infoRequestLifecycle = new DocumentLifecycle(orgDocStatusesStrings);
        infoRequestLifecycle.setFinalized(true);
        return infoRequestLifecycle;
    }

    static void printStatusAndAssignee(OrganizationDocument orgDocument, String assigneeName) {
        System.out.println("\n" + statusNumber + ". " + orgDocument.getDocumentTypeName() + " status set to " + orgDocument.getDocumentStatusString() +
                " to " + assigneeName);
        statusNumber++;
    }
}
