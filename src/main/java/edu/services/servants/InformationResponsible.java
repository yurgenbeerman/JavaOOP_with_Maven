package edu.services.servants;

import edu.services.docs.DocDefaults;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class InformationResponsible extends PublicServant {
    String informationForReply;

    public InformationResponsible(PublicServiceDepartment department, String surname, String name, String secondName) {
        super(department, surname, name, secondName);
    }

    public String getInformationForReply() {
        return informationForReply;
    }

    public void setInformationForReply(String info) {
        this.informationForReply = new String(info);
    }

    public String getInformationResponsibleName() {
        return super.toString();
    }

    public String toString() {
        return super.toString();
    }
}
