package edu.services.execution;

import edu.clients.Requester;

/**
 * Created by Lena on 19.03.14.
 */
public class ExecutionDefaults {
    public static final String NO_DOCS_TO_DISPATCH = "NO_DOCS_TO_DISPATCH";
    public static final String NO_SERVANTS_TO_DISPATCH = "NO_SERVANTS_TO_DISPATCH";
    public static final String NO_SUCH_DOC_TYPE = "NO_SUCH_DOC_TYPE";
    public static final short MAX_SERVANTS_PER_DEP = 100;
    public static final short REQUESTER_OFFICIAL_ID_LENGTH = 10;
    public static final String REQUESTER_OFFICIAL_ID_IS_INVALID = "REQUESTER_OFFICIAL_ID_IS_INVALID";

    public static boolean isRequesterOfficialIdValid(Requester requester) {
        if (( requester.getOfficialId() != null) && (requester.getOfficialId() != "")) {
            if ( requester.getOfficialId().length() != ExecutionDefaults.REQUESTER_OFFICIAL_ID_LENGTH ) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
