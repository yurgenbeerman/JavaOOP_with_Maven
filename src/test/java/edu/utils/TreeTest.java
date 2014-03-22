package edu.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lena on 22.03.14.
 */
public class TreeTest {

    @Test
    public void shouldHaveAtLeastOneElement() {
        //given
        Tree tree;

        //when
        tree = new Tree("TTT");

        //then
        org.junit.Assert.assertFalse(tree.size() == 0);

    }

    @Test
    public void shouldAddNode() {
        //given
        Tree tree = new Tree("TTT");
        int size = tree.size();

        //when
        tree.addNode("QQQ");

        //then
        org.junit.Assert.assertTrue(tree.size() == size + 1);

    }

    @Test
    public void shouldGetHeight() {
        //given
        Tree tree = new Tree("TTT");
        tree.addNode("QQQ");

        //when

        //then
        org.junit.Assert.assertTrue(tree.getHeight() > 1);

    }
}
