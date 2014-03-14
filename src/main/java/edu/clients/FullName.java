package edu.clients;

import edu.services.docs.DocDefaults;

/**
 * Created by yurii.pyvovarenko on 3/4/14.
 */
public class FullName {
    private String name;
    private String secondName;
    private String surname;

    public FullName(String surname, String name, String secondName) {
        if (name.equals("")) {
            this.name = DocDefaults.NO_NAME;
        } else {
            this.name = name;
        }

        if (secondName.equals("")) {
            this.secondName = DocDefaults.NO_SECOND_NAME;
        } else {
            this.secondName = secondName;
        }

        if (surname.equals("")) {
            this.surname = DocDefaults.NO_SURNAME;
        } else {
            this.surname = surname;
        }
    }

    public String toString() {
        return (surname + " " + name + " " + secondName);
    }

    public String getName() {
        return name;
    }
}
