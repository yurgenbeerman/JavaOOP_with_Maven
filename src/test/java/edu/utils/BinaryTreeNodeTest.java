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
    public void shouldHaveProperSizeWhenSeveralChildrenAdded() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        binaryTreeNode.addNode("QQQ_1");
        binaryTreeNode.addNode("QQQ_2");
        int size = binaryTreeNode.size();

        //when
        int childrenToAdd = 5;
        for (int i = 0; i < childrenToAdd; i++)
            binaryTreeNode.addNode("ZZZ" + i);

        //then
        org.junit.Assert.assertEquals("added children = " + childrenToAdd, size + childrenToAdd, binaryTreeNode.size());
    }

    @Test
    public void shouldGetTreeNodeHeightOfOneWhenNoChildren() {
        //given
        BinaryTreeNode tree = new BinaryTreeNode("TTT");

        //when

        //then
        org.junit.Assert.assertEquals(1, tree.getHeight());
    }

    @Test
    public void shouldGetProperTreeNodeHeightWhenSeveralChildrenAdded() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        int level = 5;

        //when
        for (int i = 0; i < level; i++)
            binaryTreeNode.addNode("ZZZ" + i);

        //then
        org.junit.Assert.assertEquals("added levels = " + level, 1 + level, binaryTreeNode.getHeight());
    }

    @Test
    public void shouldRiseHeightByTwoWhenAddedMoreThanTwoNodesPerChildrenNode() {
        //given
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode("TTT");
        int initialHeight = binaryTreeNode.getHeight();

        //when
        binaryTreeNode.addNode("QQQ_1");
        binaryTreeNode.addNode("QQQ_0");
        binaryTreeNode.addNode("QQQ_2");
        binaryTreeNode.addNode("ZZZ_1");
        binaryTreeNode.addNode("ZZZ_0");
        binaryTreeNode.addNode("ZZZ_2");

        //then
        org.junit.Assert.assertTrue(initialHeight + 2 <= binaryTreeNode.getHeight());
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
