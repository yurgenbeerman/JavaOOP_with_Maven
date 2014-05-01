package edu.services.servants;

/**
 * Created by yurii.pyvovarenko on 01.05.14.
 */
import edu.services.execution.ExecutionEnvironment;
import edu.services.orgs.PublicService;
import edu.services.orgs.PublicServiceDepartment;
import org.junit.Test;

public class WorkingPublicServantTests {
    @Test
    public void MustCreateInstanceOfWorkingPublicServant() {
        //given
        ExecutionEnvironment environment = new ExecutionEnvironment();
        PublicService publicService = new PublicService("Improvements service", environment);
        PublicServiceDepartment infoRequestsDep = new PublicServiceDepartment(publicService,"infoRequestsDep_0");

        //when
        PublicServant workingPublicServant =
                new WorkingPublicServant(infoRequestsDep, "Karpenko","Petro","Ivanovych");

        //then
        org.junit.Assert.assertEquals("Karpenko Petro Ivanovych", workingPublicServant.toString());
    }
}
