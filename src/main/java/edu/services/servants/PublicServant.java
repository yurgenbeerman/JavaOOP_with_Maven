package edu.services.servants;

import edu.clients.Citizen;
import edu.services.orgs.PublicService;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class PublicServant extends Citizen {
    private static long lastPublicServantId;
    private long publicServantId;
    private long orgId;

    public PublicServant(PublicService organization, String surname, String name, String secondName) {
        super(surname, name, secondName);

        this.publicServantId = PublicServant.lastPublicServantId;
        PublicServant.lastPublicServantId++;

        this.orgId = organization.getOrgId();
    }

    public String toString() {
        return this.getFullNameString();
    }
}
