package edu.sandbox;
//9-14
/**
 * Created by yurij.pyvovarenko on 16.04.14.
 */

import java.util.*;

public class CollectionsTests {
        static final int COUNT = 10000;
        static long lStartTime;
        static long lFinishTime;

        public static void main(String[] args) {
            ArrayList<Number> arrayList = new ArrayList<Number>(COUNT);
            LinkedList<Number> linkedList = new LinkedList<Number>();

            //add
            System.out.println("--- add() tests ---");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                arrayList.add(new Integer(i));
            }
            lFinishTime = System.nanoTime();
            System.out.println(" arrayList<Number>: " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                linkedList.add(new Integer(i));
            }
            lFinishTime = System.nanoTime();
            System.out.println("linkedList<Number>: " + (lFinishTime - lStartTime) + " milisec.");

            String s = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

            String[] strArray = new String[COUNT];
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strArray[i] = (s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("   String[]        :      " + (lFinishTime - lStartTime) + " milisec.");

            ArrayList<String> strArrayList = new ArrayList<String>(COUNT);
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strArrayList.add(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("   strArrayList<String>:  " + (lFinishTime - lStartTime) + " milisec.");

            LinkedList<String> strLinkedList = new LinkedList<String>();
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strLinkedList.add(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("  strLinkedList<String>:  " + (lFinishTime - lStartTime) + " milisec.");

            Map<String, String> hashmap = new HashMap<String, String>();
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                hashmap.put(s + i, s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("hashmap<String, String>:  " + (lFinishTime - lStartTime) + " milisec.");

            TreeSet<String> aTreeSet = new TreeSet<String>();
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                aTreeSet.add(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("       aTreeSet<String>: " + (lFinishTime - lStartTime) + " milisec.");


            //--------------- Get form a collection tests -------------------------
            System.out.println("--- get() tests ---");

            lStartTime = System.nanoTime();
            for (String strValue : strArray) {
            }
            lFinishTime = System.nanoTime();
            System.out.println("   String[]        :      " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (String strValue : strArrayList) {
            }
            lFinishTime = System.nanoTime();
            System.out.println("arrayList<String>:       " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (String strValue : strLinkedList) {
            }
            lFinishTime = System.nanoTime();
            System.out.println("linkedList<String>:      " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (Map.Entry<String, String> entry: hashmap.entrySet()) {
                //strValue = entry.getValue();
            }
            lFinishTime = System.nanoTime();
            System.out.println("hashmap<String, String>: " + (lFinishTime - lStartTime) + " milisec.");
            Iterator<Map.Entry<String, String>> itr = hashmap.entrySet().iterator();
            lStartTime = System.nanoTime();
            //while (itr.hasNext()) {}
            for (int i = 0; i < COUNT; i++) {
                itr.next();
            }
            lFinishTime = System.nanoTime();
            System.out.println("Iterator hashmp<St, St>:  " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (String strValue : aTreeSet) {
            }
            lFinishTime = System.nanoTime();
            System.out.println("aTreeSet<String>:       " + (lFinishTime - lStartTime) + " milisec.");


            //--------------- Remove form a collection tests -------------------------
            System.out.println("--- remove() tests ---");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strArray[i] = null;
            }
            lFinishTime = System.nanoTime();
            System.out.println("   String[]          :      " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strArrayList.remove(0);
            }
            lFinishTime = System.nanoTime();
            System.out.println("arrayList<String>:        " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strLinkedList.remove(0);
            }
            lFinishTime = System.nanoTime();
            System.out.println("linkedList<String>:        " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
//            for (Map.Entry<String, String> entry: hashmap.entrySet()) {
//                hashmap.remove(entry.getKey());
//            }
            for (int i = 0; i < COUNT; i++) {
                hashmap.remove(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("hashmap<String, String>:  " + (lFinishTime - lStartTime) + " milisec.");
            //Iterator<Map.Entry<String, String>> itr = hashmap.entrySet().iterator();
            lStartTime = System.nanoTime();
            while (itr.hasNext()) {
                    hashmap.remove(itr.next());
            }
            lFinishTime = System.nanoTime();
            System.out.println("Iterator hashmp<St, St>:      " + (lFinishTime - lStartTime) + " milisec.");

            Iterator<String> itrOfTree = aTreeSet.iterator();
            lStartTime = System.nanoTime();
//            while (itrOfTree.hasNext()) {
//                aTreeSet.remove(itrOfTree.next());
//            }
            for (int i = 0; i < COUNT; i++) {
                aTreeSet.remove(s + i);
            }
            lFinishTime = System.nanoTime();
            //System.out.println("Iterator aTreeSet<Stri>: " + (lFinishTime - lStartTime) + " milisec.");
            System.out.println("         aTreeSet<Stri>:  " + (lFinishTime - lStartTime) + " milisec.");

/*
            System.out.println("\nhashmap<String, String> entries:");
            String aString = new String();
            String lastChar = new String();
            // 1.
            System.out.println("\nhashmap<String, String> entries:");
            for (Map.Entry<String, String> entry: hashmap.entrySet()) {
                aString = entry.getValue();
                lastChar = aString.substring(aString.length()-1);
                System.out.print(entry.getKey().hashCode() + " = " + lastChar + ". ");
            }

		// 2.
		System.out.println("\nhashmap<String, String> keys:");
		for (String key: hashmap.keySet())
			System.out.print(hashmap.get(key) + ". ");

		// 3.
		System.out.println("\nhashmap<String, String> entries:");
		Iterator<Map.Entry<String, String>> itr = hashmap.entrySet().iterator();
		while (itr.hasNext())
			System.out.print(itr.next());

*/
        }

}
