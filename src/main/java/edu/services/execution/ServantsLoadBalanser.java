package edu.services.execution;

import edu.emory.mathcs.backport.java.util.Arrays;
import edu.services.docs.OrganizationDocument;
import edu.services.servants.PublicServant;

import java.util.List;

/**
 * Created by Lena on 20.03.14.
 */
public class ServantsLoadBalanser extends PublicServant{
    //short servantsCount;
    short nextServantIndex;
    List<PublicServant> publicServants;

    public ServantsLoadBalanser(PublicServant... publicServants) {
        this.publicServants = Arrays.asList(publicServants);
        nextServantIndex = 0;
    }

    public void addDocumentToProcess(OrganizationDocument document) {
        publicServants.get(nextServantIndex).addDocumentToProcess(document);
        if (nextServantIndex < (publicServants.size() - 1) ) {
            nextServantIndex++;
        } else
            nextServantIndex = 0;
    }
}
