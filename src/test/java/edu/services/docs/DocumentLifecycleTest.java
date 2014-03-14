package edu.services.docs;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lena on 13.03.14.
 */
public class DocumentLifecycleTest {
    DocumentLifecycle infoRequestLifecycle;

    @Before
    public void createLinearLifecycleFinalized() {
        String[] infoRequestLifecycleString = {"Created", "Assigned", "Replied"};
        infoRequestLifecycle = new DocumentLifecycle(infoRequestLifecycleString);
        infoRequestLifecycle.setFinalized(true);
    }

    @Test
    public void shouldThrowExceptionWhenLifecycleInUseAndTryingToSetSomething() throws IllegalStateException {
        //given

        //when

        //then
        infoRequestLifecycle.setFinalStatusIndex(9);
    }

    @Test
    public void shouldIndicateIsLifecycleInUse() throws Exception {
        //given

        //when

        //then
        org.junit.Assert.assertTrue(infoRequestLifecycle.isInUse());
    }

    @Test
    public void testIsFinalized() throws Exception {
        org.junit.Assert.assertTrue(infoRequestLifecycle.isInUse());
    }

    @Test
    public void testSetFinalized() throws Exception {
        org.junit.Assert.assertTrue(infoRequestLifecycle.isInUse());
    }
}
