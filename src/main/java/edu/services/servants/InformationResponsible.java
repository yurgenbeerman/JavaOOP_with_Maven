package edu.services.servants;

import edu.services.orgs.PublicService;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class InformationResponsible extends PublicServant {
    String informationForReply = "The plan of improvements for 2014 is next... Sincerely, InformationResponsible.";

    public InformationResponsible(PublicService organization, String surname, String name, String secondName) {
        super(organization, surname, name, secondName);
    }

    public String getInformationForReply() {
        return informationForReply;
    }

    public String getInformationResponsibleName() {
        return this.getFullNameString();
    }

    public String toString() {
        return this.getFullNameString();
    }
}
