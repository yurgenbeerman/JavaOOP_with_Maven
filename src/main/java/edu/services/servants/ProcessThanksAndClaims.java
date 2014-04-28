package edu.services.servants;

import edu.services.docs.IncomingDocument;

import java.util.LinkedList;

/**
 * Created by yurii.pyvovarenko on 28.04.14.
 */
public class ProcessThanksAndClaims extends PublicServantResponsibilities {
    private PublicServant decoratedPublicServant;
    private LinkedList<IncomingDocument> ThanksQueue;
    private LinkedList<IncomingDocument> ClaimsQueue;

    public ProcessThanksAndClaims(PublicServant decoratedPublicServant) {
        this.decoratedPublicServant = decoratedPublicServant;
    }

    public void doMainResponsibility(){

    }

    public void doBonusedResponsibility(){

    }
}