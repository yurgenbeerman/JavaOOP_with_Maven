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
}
