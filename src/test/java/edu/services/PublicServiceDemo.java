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
    static ExecutionEnvironment testEnvironment;

    public static void main (String[] args) {
        System.out.println("------------- PublicServiceDemo ------------- ");

        testEnvironment = new ExecutionEnvironment();
        setupEnvironment(testEnvironment);

        /* Assume a user (Citizen wants to create s Request */
        InformationRequest infoRequest0 = createTestInformationRequest();

        /* The user can modify the Request data until it isReceivedByPublicService */
        infoRequest0.setReceivedByPublicService(true);
        testEnvironment.getPublicService().addDocumentToProcess(infoRequest0);

        /* Assume a user creates more requests */
        InformationRequest infoRequest1 = createTestInformationRequest();
        InformationRequest infoRequest2 = createTestInformationRequest();
        InformationRequest infoRequest3 = createTestInformationRequest();
        testEnvironment.getPublicService().addDocumentToProcess(infoRequest1);
        testEnvironment.getPublicService().addDocumentToProcess(infoRequest2);
        testEnvironment.getPublicService().addDocumentToProcess(infoRequest3);

//        printStatusAndAssignee(infoRequest0, infoRequest0.getIncomingDocResponsibleName());
//        printStatusAndAssignee(infoRequest0, infoRequest0.getAuthorName());
//        System.out.println("\ninfoRequest statuses history: " + infoRequest0.getStatusesHistoryString());

        //TODO ensure that informationResponsibles process their documents!

        System.out.println("\ncitizen sent the next requests:\n   " +
                testEnvironment.getRequester().getRequestsString());
        System.out.println("\ncitizen got the next responses:\n   " +
                testEnvironment.getRequester().getResponsesString());
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

    public static PublicService createValidPublicService(ExecutionEnvironment testEnvironment) {
        PublicService publicService = createPublicService(testEnvironment);
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

    public static InformationRequest createTestInformationRequest() {
        return createInformationRequest(
            testEnvironment.getInfoRequestDocType(),
            testEnvironment.getRequester(),
            testEnvironment.getPublicService()
        );
    }

    public static InformationRequest createInformationRequest(DocumentType infoRequestDocType, Requester requester, PublicService publicService) {
        if ( ! ExecutionDefaults.isRequesterOfficialIdValid(requester) )
            throw new IllegalStateException(ExecutionDefaults.REQUESTER_OFFICIAL_ID_IS_INVALID);
        InformationRequest informationRequest =
                new InformationRequest(infoRequestDocType, requester, publicService);
        informationRequest.setText("What parks and streets improvements are planned for 2014 in Kyiv?");
        informationRequest.setIfSendReplyToPostAddress(true);
        informationRequest.setAddressForReply(requester.getAddressString());
        informationRequest.setIfSendReplyToEmail(true);
        informationRequest.setEmailForReply(requester.getEmailAddress());
        return informationRequest;
    }

    public static PublicService createPublicService(ExecutionEnvironment testEnvironment) {
        PublicService publicService = new PublicService("Improvements service", testEnvironment);
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

    public static void setupEnvironment(ExecutionEnvironment environment) {
        DocumentType infoRequestDocType = createInfoRequestDocType();
        environment.setInfoRequestDocType(infoRequestDocType);

        DocumentType outcomingDocType = createOutcomingDocType();
        environment.setOutcomingDocType(outcomingDocType);

        Requester requester = createValidCitizenRequester();
        environment.setRequester(requester);

        PublicService publicService = createValidPublicService(environment);
        environment.setPublicService(publicService);


        PublicServiceDepartment infoRequestsDep = new PublicServiceDepartment(
                publicService,
                "infoRequestsDep_0"
        );
        environment.setInfoRequestsDepartment(infoRequestsDep);

        PublicServiceDepartment thanksAndClaimsDep = new PublicServiceDepartment(
                publicService,
                "ThanksAndClaimsDep_1"
        );

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

        environment.setOutcomingDocType(outcomingDocType);
    }
}
