package edu.services.servants;

import edu.services.docs.IncomingDocument;

import java.util.LinkedList;

/**
 * Created by yurii.pyvovarenko on 28.04.14.
 */
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
