package edu.services;

import edu.clients.Citizen;
import edu.clients.Requester;
import edu.communications.Address;
import edu.services.docs.*;
import edu.services.orgs.PublicService;
import edu.services.servants.InformationResponsible;
import edu.utils.PublicRequestsUtils;

import java.util.GregorianCalendar;

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
        String[] infoRequestLifecycleString = {"Created", "Assigned", "Replied"};
        DocumentLifecycle infoRequestLifecycle = createLinearLifecycleFinalized(infoRequestLifecycleString);
        DocumentType infoRequestDocType = new DocumentType("Information_Request", "InfoReq_",infoRequestLifecycle);
        infoRequestDocType.setFinalized(true);

        String[] outcomingDocLifecycleString = {"Created", "Passed_for_sending", "Sent"};
        DocumentLifecycle outcomingDocLifecycle = createLinearLifecycleFinalized(outcomingDocLifecycleString);
        DocumentType outcomingDocType = new DocumentType("Outcoming_Document", "Out_",outcomingDocLifecycle);
        outcomingDocType.setFinalized(true);

        Requester requester = new Citizen("Petrenko","Taras","Ivanovych");
        requester.setEmailAddress("citizen@gmail.com");
        requester.setOfficialId("1234567890");
        Address citizenAddress = new Address();
        citizenAddress.setCountry("Ukraine");
        citizenAddress.setRegion("Kyivska obl.");
        citizenAddress.setCity("Kyiv");
        citizenAddress.setCityArea("Shevchenkivski");
        citizenAddress.setStreet("Khreshchatyk");
        citizenAddress.setBuilding("1A");
        citizenAddress.setApartment("123H");
        citizenAddress.setZipCode("01001");
        requester.setAddress(citizenAddress);

        PublicService publicService = new PublicService("Improvements service");
        publicService.setHierarchyLevel(1);
        publicService.setParentOrgId((long)0);
        publicService.setOrgId((long)0);
        publicService.setEmailAddress("contact@improvements.service.com");
        Address pubServiceAddress = new Address();
        pubServiceAddress.setCountry("Ukraine");
        pubServiceAddress.setRegion("Kyivska obl.");
        pubServiceAddress.setCity("Kyiv");
        pubServiceAddress.setCityArea("Shevchenkivski");
        pubServiceAddress.setStreet("Saksaganskogo");
        pubServiceAddress.setBuilding("104");
        pubServiceAddress.setZipCode("01002");
        publicService.setAddress(pubServiceAddress);
        /* INITIALIZATION end */

        /* Assume a user (Citizen wants to create s Request */
        if (( requester.getOfficialId() != null) && (requester.getOfficialId() != "")) {
            if ( requester.getOfficialId().length() != 10 ) {
                System.out.println("RequesterOfficialId is wrong: " + requester.getOfficialId() + ". We can not allow to create a request. You still may email to us.");
                System.exit(2);
            }
        } else {
            System.out.println("There's no RequesterOfficialId: " + requester.getOfficialId() + ". We can not allow to create a request. You still may email to us.");
            System.exit(1);
        }

        InformationRequest infoRequest =
                new InformationRequest(infoRequestDocType, requester, publicService);
        infoRequest.setText("What parks and streets improvements are planned for 2014 in Kyiv?");
        infoRequest.setAddressForReply(requester.getAddressString());
        infoRequest.setEmailForReply(requester.getEmailAddress());

        if (infoRequest != null) {
            requester.addRequest(infoRequest);
            if (infoRequest.isValid().equals(DocDefaults.VALID)) {
                System.out.println("requester: " + requester.getFullNameString());
                System.out.println("    requesterId: " + requester.getId());
                System.out.println("\npublicService: " + publicService.getOrgName() + "\n");
                System.out.println(infoRequest.toString());
            } else {
                System.out.println(infoRequest.isValid());
            }
        } else {
            System.out.println("infoRequest is NULL");
        }

        /* The user can modify the Request data until it isReceivedByPublicService */
        infoRequest.setReceivedByPublicService(true);
        InformationResponsible informationResponsibleServant =
                new InformationResponsible(publicService, "Karpenko","Petro","Ivanovych");

        infoRequest.setIncomingDocResponsible(informationResponsibleServant);
        infoRequest.setNextDocumentStatus();

        printStatusAndAssignee(infoRequest, infoRequest.getIncomingDocResponsibleName());


        OutcomingDocument outcomingDocument =
                new OutcomingDocument(outcomingDocType, informationResponsibleServant, publicService);
        outcomingDocument.setText(informationResponsibleServant.getInformationForReply());
        infoRequest.setReactionDocument(outcomingDocument);
        outcomingDocument.setInitiatingDocument(infoRequest);
        outcomingDocument.setNextDocumentStatus();

        outcomingDocument.publishToRequester(requester);
        outcomingDocument.setNextDocumentStatus();
        infoRequest.setNextDocumentStatus();
        infoRequest.setFinalized(true);

        printStatusAndAssignee(infoRequest, infoRequest.getAuthorName());

        Email email = new Email(publicService.getEmailAddress(), requester.getEmailAddress(),
                informationResponsibleServant.getInformationForReply());
        email.sendEmail();
        outcomingDocument.setDocSentEmail(email);
        System.out.println("\nThe Response (Outcoming Doc) was sent to Email: " + outcomingDocument.getDocSentEmailAddress() +
                " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentEmailDate()));

        publicService.sendDocumentToAddress(outcomingDocument, requester.getAddress());
        System.out.println("\nThe Response (Outcoming Doc) was sent to Address: " + outcomingDocument.getDocSentAddress() +
                " on " + PublicRequestsUtils.toTimeAndDateString(outcomingDocument.getDocSentAddressDate()));

        outcomingDocument.setNextDocumentStatus();
        outcomingDocument.setFinalized(true);
        //TODO: must set outcomingDocument.status to the last one "Sent" -- find out why it doesn't work!

        System.out.println("\ninfoRequest statuses history: " + infoRequest.getStatusesHistoryString());
        System.out.println("\ncitizen sent the next requests:\n   " + requester.getRequestsString());
        System.out.println("\ncitizen got the next responses:\n   " + requester.getResponsesString());

    }

    private static DocumentLifecycle createLinearLifecycleFinalized(String[] orgDocStatusesStrings) {
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
