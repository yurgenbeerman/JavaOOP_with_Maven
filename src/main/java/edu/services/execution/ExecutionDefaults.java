package edu.services.execution;

import edu.clients.Requester;

/**
 * Created by Lena on 19.03.14.
 */
public class ExecutionDefaults {
    public static final String DOCS_DISPATCHING_TABLE_IS_EMPTY = "DOCS_DISPATCHING_TABLE_IS_EMPTY";
    public static final String NO_DOCS_DISPATCHING_TABLE = "NO_DOCS_DISPATCHING_TABLE";
    public static final short MAX_SERVANTS_PER_DEP = 100;
    public static final short REQUESTER_OFFICIAL_ID_LENGTH = 10;
    public static final String REQUESTER_OFFICIAL_ID_IS_INVALID = "REQUESTER_OFFICIAL_ID_IS_INVALID";

    public static final String DEP_IS_NULL = "DEP_IS_NULL";
    public static final String DEPS_DISPATCHING_TABLE_IS_EMPTY = "DEPS_DISPATCHING_TABLE_IS_EMPTY";
    public static final String NO_DEPS_DISPATCHING_TABLE = "NO_DEPS_DISPATCHING_TABLE";

    public static final String OUTCOMING_DOC_NAME = "Response to Information request";

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
