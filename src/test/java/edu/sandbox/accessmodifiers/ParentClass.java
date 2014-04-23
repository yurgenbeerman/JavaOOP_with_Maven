package edu.sandbox.accessmodifiers;

/**
 * Created by Lena on 23.04.14.
 */
public class ParentClass {
    private int privateInt;
    int packageInt;
    protected int protectedInt;
    public int publicInt;

    public ParentClass()  {
        privateInt = 101;
        packageInt = 102;
        protectedInt = 103;
        publicInt = 104;
    }

    protected int getPackageInt() {
        return packageInt;
    }

    public String toString() {
        return "ParentClass"
        + "\nprivateInt = " + privateInt
        + "\npackageInt = " + packageInt
        + "\nprotectedInt = " + protectedInt
        + "\npublicInt = " + publicInt;
    }
}
