package edu.sandbox;

import java.util.ArrayList;

/**
 * Created by yurij.pyvovarenko on 19.04.14.
 */
public final class ImmutableObject implements Cloneable {
    private final int anInt;
    private final ArrayList<String> strings;
    private final ArrayList<StringBuffer> stringBuffers;

    public ImmutableObject(int anInt, final ArrayList<String> strings, final ArrayList<StringBuffer> stringBuffers) {
        this.anInt = anInt;
        this.strings = (ArrayList<String>) strings.clone();
        this.stringBuffers = deepSBCopy(stringBuffers);
//                = new ArrayList<StringBuffer>();
//        for (StringBuffer sb : stringBuffers) {
//            this.stringBuffers.add(new StringBuffer(sb));
//        }

        //((ArrayList<StringBuffer>) stringBuffers.clone());//(ArrayList<StringBuffer>) Collections.unmodifiableList(stringBuffers);
    }

    private static synchronized
            ArrayList<StringBuffer> deepSBCopy
                (ArrayList<StringBuffer> SBtoBeCopied) {
        ArrayList<StringBuffer> SBCopy = new ArrayList<StringBuffer>();
        for (StringBuffer sb : SBtoBeCopied) {
            SBCopy.add(new StringBuffer(sb));
        }
        return SBCopy;
    }

    public final int getAnInt() {
        return anInt;
    }

    public final ArrayList<String> getStrings() {
        return (ArrayList<String>) strings.clone();
    }

    public final ArrayList<StringBuffer> getStringBuffers() {
        return deepSBCopy(stringBuffers);
        //return (ArrayList<StringBuffer>) stringBuffers.clone();
    }

    public ImmutableObject clone() throws CloneNotSupportedException {
        return new ImmutableObject(
                anInt,
                strings, //(ArrayList<String>) strings.clone(),
                stringBuffers);//(ArrayList<StringBuffer>) stringBuffers.clone());
    }
//    public ImmutableObject clone() throws CloneNotSupportedException {
//        ImmutableObject newImmutableObject = (ImmutableObject) super.clone();
//        newImmutableObject.anInt = anInt;
//        newImmutableObject.stringBuffers = stringBuffers.clone();
//        return newImmutableObject;
//    }

    public String toString() {
        return "anInt = " + this.getAnInt()
            + "\nstrings = " + this.getStrings()
            + "\nstringBuffers = " + this.getStringBuffers();
    }


    public static void main(String[] args) {
        ArrayList<String> outerStringsList = new ArrayList<String>();
        ArrayList<StringBuffer> outerSBList = new ArrayList<StringBuffer>();// = (ArrayList) Arrays.asList("q",1,3.2f,'c');
        for (Integer i = 0; i < 5; i++ ) {
            outerStringsList.add(i.toString());
            StringBuffer sb = new StringBuffer(i.toString());
            outerSBList.add(sb);
        }
        int i = 10;
        final ImmutableObject anImmutableObject =
                new ImmutableObject(i, outerStringsList, outerSBList);
        System.out.println(anImmutableObject);

        System.out.println("try to modify anImmutableObject...");
        i = 100000;
        outerStringsList.add("ddddddddddddddd");
        outerStringsList.get(0).concat("qazzzzie");
        outerSBList.add(new StringBuffer("ddddd"));
        outerSBList.get(0).append("qaz");
        anImmutableObject.getStrings().add("qwerty");
        anImmutableObject.getStrings().get(0).concat("zzzzzzzzz");
        anImmutableObject.getStringBuffers().add(new StringBuffer("qwerty"));
        anImmutableObject.getStringBuffers().get(0).append("ssss");


        ImmutableObject clonedImmutableObject = null;
        try {
            clonedImmutableObject = anImmutableObject.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clonedImmutableObject.getStrings().add("qwerty111");
        clonedImmutableObject.getStrings().get(0).concat("zzzzzzzzz111");
        clonedImmutableObject.getStringBuffers().add(new StringBuffer("qwerty111"));
        clonedImmutableObject.getStringBuffers().get(0).append("ssss1111");

        System.out.println(anImmutableObject);
    }
}