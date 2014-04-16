package edu.sandbox;
//9-10
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
            System.out.println("--- Add() tests ---");

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

            ArrayList<String> strArrayList = new ArrayList<String>(COUNT);
            LinkedList<String> strLinkedList = new LinkedList<String>();
            Map<String, String> hashmap = new HashMap<String, String>();

            String s = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                    "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strArrayList.add(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("   strArrayList<String>: " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                strLinkedList.add(s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("  strLinkedList<String>: " + (lFinishTime - lStartTime) + " milisec.");

            lStartTime = System.nanoTime();
            for (int i = 0; i < COUNT; i++) {
                hashmap.put(s + i, s + i);
            }
            lFinishTime = System.nanoTime();
            System.out.println("hashmap<String, String>: " + (lFinishTime - lStartTime) + " milisec.");

            String aString = new String();
            String lastChar = new String();
            // 1.
            System.out.println("\nhashmap<String, String> entries:");
            for (Map.Entry<String, String> entry: hashmap.entrySet()) {
                aString = entry.getValue();
                lastChar = aString.substring(aString.length()-1);
                System.out.print(entry.getKey().hashCode() + " = " + lastChar + ". ");
            }
/*
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

		/*
		//get
		for (int i = 0; i < COUNT; i++) {
			arrayList.add(new Integer(5));
			linkedList.add(new Integer(5));
		}

		//remove
		for (int i = -; i < COUNT; i++) {
			arrayList.add(new Integer(5));
			linkedList.add(new Integer(5));
		}
		*/
        }

}
