package edu.clients;

import edu.services.docs.DocumentLifecycle;
import edu.services.docs.DocumentType;
import edu.services.docs.IncomingDocument;
import edu.services.docs.InformationRequest;
import edu.services.orgs.OfficialIDsHolder;
import edu.services.orgs.PublicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yurii.pyvovarenko on 24.03.14.
 */
@RunWith(MockitoJUnitRunner.class)
public class RequesterTest {
    @Mock
    private OfficialIDsHolder officialIDsHolder;

    @InjectMocks
    Requester requester = new Citizen("ivanenko", "petro", "ivanovych");

    @Test
    public void shouldReturnTrue_WhenInvokedIsRequesterOfficialIdValid() throws Exception {
        //given
        requester.setOfficialId("1234567890");

        //when
        when(officialIDsHolder.ifOfficialIDExists("1234567890")).thenReturn(true);

        //then
        org.junit.Assert.assertTrue(requester.isRequesterOfficialIdValid(officialIDsHolder));
        verify(officialIDsHolder).ifOfficialIDExists("1234567890");

    }
}