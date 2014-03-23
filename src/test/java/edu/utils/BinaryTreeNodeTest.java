package edu.utils;

import org.junit.Test;

import java.security.InvalidParameterException;

/**
 * Created by yurii.pyvovarenko on 22.03.14.
 */
public class BinaryTreeNodeTest {

    @Test
    public void shouldHaveAtLeastOneElement() {
        //given
        BinaryTreeNode tree;

        //when
        tree = new BinaryTreeNode("TTT");

        //then
        org.junit.Assert.assertFalse(tree.size() == 0);
    }

    @Test (expected = InvalidParameterException.class)
    public void shouldExceptionWhenNoDataGivenForConstructor() {
        //given

        //when
        BinaryTreeNode tree = new BinaryTreeNode(null); //InvalidParameterException

        //then

    }

    @Test
    public void shouldAddNode() {
        //given
        BinaryTreeNode tree = new BinaryTreeNode("TTT");
        int size = tree.size();

        //when
        tree.addNode("QQQ");

        //then
        org.junit.Assert.assertEquals(tree.size(), size + 1);
    }

    @Test
    public void shouldGetProperSizeAfterAddingFiveNodes() {
        //given
        BinaryTreeNode tree = new BinaryTreeNode("TTT");
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
        BinaryTreeNode tree = new BinaryTreeNode("TTT");
        tree.addNode("QQQ");

        //when

        //then
        org.junit.Assert.assertTrue(tree.getHeight() > 1);
    }

    @Test
    public void shouldGetProperTreeNodeHeight() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        int level = 5;
        double levelNodesCount = 1;

        //when
        for (int i = 0;
             i < levelNodesCount;
             i++)
            binaryTreeNode.addNode("QQQ" + i);

        //then
        org.junit.Assert.assertEquals("levelNodesCount = " + levelNodesCount, 1 + level, binaryTreeNode.getHeight());
    }

    @Test
    public void shouldHaveProperNumOfChildren() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");

        //when
        binaryTreeNode.addNode("QQQ");
        binaryTreeNode.addNode("QQQ1");
        binaryTreeNode.addNode("QQQ2");

        //then

    }

    @Test
    public void shouldRiseHeightByTwoWhenAddedMoreThanTwoNodesPerChildrenNode() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        int initialHeight = binaryTreeNode.getHeight();

        //when
        binaryTreeNode.addNode("QQQ_0");
        binaryTreeNode.addNode("QQQ_1");
        binaryTreeNode.addNode("QQQ_2");
        binaryTreeNode.addNode("ZZZ_0");
        binaryTreeNode.addNode("ZZZ_1");
        binaryTreeNode.addNode("ZZZ_2");

        //then
        org.junit.Assert.assertTrue(initialHeight + 1 < binaryTreeNode.getHeight());
    }

    @Test
    public void shouldHaveSameHeightWhenAddedLessThanMaxChildrenPerNode() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        int initialHeight = binaryTreeNode.getHeight();

        //when


        //then
        org.junit.Assert.assertEquals(initialHeight + 1, binaryTreeNode.getHeight());
    }
}
