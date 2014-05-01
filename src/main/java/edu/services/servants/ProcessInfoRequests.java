package edu.services.servants;

import edu.services.docs.IncomingDocument;

import java.util.LinkedList;

/**
 * Created by yurii.pyvovarenko on 28.04.14.
 */
// Implementing Decorator Pattern.
// PublicServant is the main "abstract" class inherited by:
//  1) WorkingPublicServant (concrete component to be decorated)
//  2) PublicServantResponsibilities ("abstract" decorator) to be inherited by concrete decorators:
//      a) ProcessInfoRequests
//      b) ProcessThanksAndClaims
public class ProcessInfoRequests extends PublicServantResponsibilities {
    private PublicServant decoratedPublicServant;
    private LinkedList<IncomingDocument> IfoRequstsQueue;

    public ProcessInfoRequests(PublicServant decoratedPublicServant) {
        this.decoratedPublicServant = decoratedPublicServant;
    }

    public void doMainResponsibility(){

    }

    public void doBonusedResponsibility(){

    }
}