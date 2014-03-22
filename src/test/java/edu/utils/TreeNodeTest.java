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
        tree.addNode("QQQ");

        //then
        org.junit.Assert.assertEquals(tree.size(),size + 1);

    }

    @Test
    public void shouldGetProperSizeAfterAddingFiveNode() {
        //given
        TreeNode tree = new TreeNode("TTT", null);
        tree.addNode("QQQ_1");
        tree.addNode("QQQ_2");
        int size = tree.size();

        //when
        for (int i = 0; i < 5; i++)
            tree.addNode("QQQ" + i);

        //then
        org.junit.Assert.assertEquals(tree.size(), size + 5);

    }

    @Test
    public void shouldGetTreeNodeHeight() {
        //given
        TreeNode tree = new TreeNode("TTT", null);
        tree.addNode("QQQ");

        //when

        //then
        org.junit.Assert.assertTrue(tree.getHeight() > 1);

    }

    @Test
    public void shouldHaveProperNumOfChildren() {
        //given
        TreeNode treeNode = new TreeNode("TTT", null);

        //when
        treeNode.addNode("QQQ");
        treeNode.addNode("QQQ1");
        treeNode.addNode("QQQ2");

        //then
        org.junit.Assert.assertTrue(treeNode.getMaxChildrenCount() >= treeNode.getChildrenCount());
    }

    @Test
    public void shouldRiseHeightWhenAddedMoreThanMaxChildrenPerNode() {
        //given
        TreeNode treeNode = new TreeNode("TTT", null);
        int initialHeight = treeNode.getHeight();

        //when
        for (int i = 0; i < treeNode.getMaxChildrenCount(); i++)
            treeNode.addNode("QQQ" + i);
        treeNode.addNode("QQQ_!");

        //then
        org.junit.Assert.assertTrue(initialHeight + 1 < treeNode.getHeight());
    }

    @Test
    public void shouldHaveSameHeightWhenAddedLessThanMaxChildrenPerNode() {
        //given
        TreeNode treeNode = new TreeNode("TTT", null);
        int initialHeight = treeNode.getHeight();

        //when
        for (int i = 0; i < treeNode.getMaxChildrenCount(); i++)
            treeNode.addNode("QQQ" + i);

        //then
        org.junit.Assert.assertEquals(initialHeight + 1, treeNode.getHeight());
    }
}
