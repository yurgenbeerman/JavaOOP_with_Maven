package edu.sandbox;

import edu.communications.Address;

/**
 * Created by Lena on 16.04.14.
 */
public class EqualsAndHashCode {
    int intVal;
    String stringVal;
    Address address;

    public EqualsAndHashCode(
            int intVal,
            String stringVal,
            Address address) {
        this.intVal = intVal;
        this.stringVal = stringVal;
        this.address = address;
    }

    public int hashCode() {
        return intVal*33 + stringVal.hashCode() + address.hashCode();
    }

    public boolean equals(Object other) {

    }
}
