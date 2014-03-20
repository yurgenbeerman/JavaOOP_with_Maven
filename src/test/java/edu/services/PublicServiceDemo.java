package edu.services;

import edu.clients.Citizen;
import edu.clients.Requester;
import edu.communications.Address;
import edu.services.docs.*;
import edu.services.execution.*;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import edu.services.servants.InformationResponsible;
import edu.services.servants.PublicServant;
import edu.services.servants.ThanksAndClaimsResponsible;
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
    static ExecutionEnvironment environment;

    public static void main (String[] args) {
        System.out.println("------------- PublicServiceDemo ------------- ");

        /* INITIALIZATION start */
        environment = new ExecutionEnvironment();

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

        /*Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable =
                createDocsToDepartmentsDispatchingTable(infoRequestsDep, thanksAndClaimsDep);
        assert (docsToDepartmentsDispatchingTable != null); */
        Map<String, PublicServiceDepartment> docsToDepartmentsDispatchingTable = new HashMap<String, PublicServiceDepartment>();
        docsToDepartmentsDispatchingTable.put("edu.services.docs.InformationRequest", infoRequestsDep);
        docsToDepartmentsDispatchingTable.put("edu.services.docs.Claim", thanksAndClaimsDep);
        docsToDepartmentsDispatchingTable.put("edu.services.docs.Thank", thanksAndClaimsDep);
        DepartmentsTasksDispatcher departmentsTasksDispatcher = new DepartmentsTasksDispatcher(publicService);
        departmentsTasksDispatcher.setDocsToDepartmentsDispatchingTable(docsToDepartmentsDispatchingTable);

        ServantsTasksDispatcher infoRequestsDepServantsTasksDispatcher = new ServantsTasksDispatcher(infoRequestsDep);
        ServantsTasksDispatcher thanksAndClaimsDepServantsTasksDispatcher = new ServantsTasksDispatcher(thanksAndClaimsDep);

        InformationResponsible publicServant0 = new InformationResponsible(infoRequestsDep, "Karpenko0","Petro0","Ivanovych0");
        InformationResponsible publicServant1 = new InformationResponsible(infoRequestsDep, "Karpenko1", "Petro1", "Ivanovych1");
        publicServant0.setInformationForReply(
                "The plan of improvements for 2014 is next... Sincerely, publicServant0."); //TODO Remove stubs
        publicServant1.setInformationForReply(
                "The plan of improvements for 2014 is next... Sincerely, publicServant1."); //TODO Remove stubs

        ServantsLoadBalancer servantsLoadBalancer = new ServantsLoadBalancer();
        servantsLoadBalancer.addServant(publicServant0);
        servantsLoadBalancer.addServant(publicServant1);

        ThanksAndClaimsResponsible publicServant2 = new ThanksAndClaimsResponsible(thanksAndClaimsDep, "Karpenko2", "Petro2", "Ivanovych2");
        publicServant2.setInformationForReply(
                "Thank you for information! Sincerely, publicServant2."); //TODO Remove stubs

        Map<String, PublicServant> infoRequestsDepDispatchingTable = new HashMap<String, PublicServant>();
        infoRequestsDepDispatchingTable.put("edu.services.docs.InformationRequest", servantsLoadBalancer);

        infoRequestsDepServantsTasksDispatcher.setDocsToServantsDispatchingTable(infoRequestsDepDispatchingTable);

        Map<String, PublicServant> thanksAndClaimsDepDispatchingTable = new HashMap<String, PublicServant>();
        thanksAndClaimsDepDispatchingTable.put("edu.services.docs.Claim", publicServant2);
        thanksAndClaimsDepDispatchingTable.put("edu.services.docs.Thank", publicServant2);
        thanksAndClaimsDepServantsTasksDispatcher.setDocsToServantsDispatchingTable(thanksAndClaimsDepDispatchingTable);

        /* The user can modify the Request data until it isReceivedByPublicService */
        infoRequest0.setReceivedByPublicService(true);
        publicService.addDocumentToProcess(infoRequest0);

        InformationRequest infoRequest1 = createInformationRequest(infoRequestDocType, requester, publicService);
        InformationRequest infoRequest2 = createInformationRequest(infoRequestDocType, requester, publicService);
        InformationRequest infoRequest3 = createInformationRequest(infoRequestDocType, requester, publicService);
        publicService.addDocumentToProcess(infoRequest1);
        publicService.addDocumentToProcess(infoRequest2);
        publicService.addDocumentToProcess(infoRequest3);

        //TODO execute all tasks by all servants =
        //  create outcoming doc
        //      and assign replies to it
        //      and set dependent docsIds to incoming and outcoming docs
        //      and publish outcoming doc
        //      and send it via email and via post
        //      and remove incoming doc from the unprocessedIncomingDocsQueue

        printStatusAndAssignee(infoRequest0, infoRequest0.getIncomingDocResponsibleName());

        OutcomingDocument outcomingDocument = createOutcomingDocument(
                outcomingDocType, publicService, infoRequest0, publicServant0);


        environment.setOutcomingDocType(outcomingDocType);


        printStatusAndAssignee(infoRequest0, infoRequest0.getAuthorName());


        if (infoRequest0.isIfSendReplyToEmail()) {
            Email email = new Email(publicService.getEmailAddress(), requester.getEmailAddress(),
                    publicServant0.getInformationForReply());
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
        InformationRequest informationRequest =
                new InformationRequest(infoRequestDocType, requester, publicService);
        informationRequest.setText("What parks and streets improvements are planned for 2014 in Kyiv?");
        informationRequest.setIfSendReplyToPostAddress(true);
        informationRequest.setAddressForReply(requester.getAddressString());
        informationRequest.setIfSendReplyToEmail(true);
        informationRequest.setEmailForReply(requester.getEmailAddress());
        return informationRequest;
    }

    public static PublicService createPublicService() {
        PublicService publicService = new PublicService("Improvements service", environment);
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

    public static Map<String, PublicServiceDepartment> createDocsToDepartmentsDispatchingTable(PublicServiceDepartment... departments) {
        Map<String, PublicServiceDepartment> docsDispatchingTable = new HashMap<String, PublicServiceDepartment>();
        docsDispatchingTable.put("InformationRequest", departments[0]);
        docsDispatchingTable.put("Claim", departments[1]);
        docsDispatchingTable.put("Thank", departments[1]);
        return docsDispatchingTable;
    }

    static void printStatusAndAssignee(OrganizationDocument orgDocument, String assigneeName) {
        System.out.println("\n" + statusNumber + ". " + orgDocument.getDocumentTypeName() + " status set to " + orgDocument.getDocumentStatusString() +
                " to " + assigneeName);
        statusNumber++;
    }

}
