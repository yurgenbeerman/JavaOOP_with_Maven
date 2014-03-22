package edu.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lena on 22.03.14.
 */
public class TreeNodeTest {

    @Test
    public void shouldHaveAtLeastOneElement() {
        //given
        TreeNode tree;

        //when
        tree = new TreeNode("TTT", null);

        //then
        org.junit.Assert.assertFalse(tree.size() == 0);

    }

    @Test
    public void shouldAddNode() {
        //given
        TreeNode tree = new TreeNode("TTT", null);
        int size = tree.size();

        //when
        tree.addNode("QQQ", null);

        //then
        org.junit.Assert.assertTrue(tree.size() == size + 1);

    }

    @Test
    public void shouldGetTreeNodeHeight() {
        //given
        TreeNode tree = new TreeNode("TTT", null);
        tree.addNode("QQQ", null);

        //when

        //then
        org.junit.Assert.assertTrue(tree.getHeight() > 1);

    }
}
