package eu.sqooss.service.db;

import eu.sqooss.core.AlitheiaCore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BugRepository {

    /**
     * Get a list of all bug report comments for a specific bug,
     * ordered by the time the comment was left (old to new).
     */
    @SuppressWarnings("unchecked")
    public List<BugReportMessage> getAllReportComments(Bug bug) {
        DBService dbs = AlitheiaCore.getInstance().getDBService();

        String paramBugID = "paramBugID";
        String paramStoredProject = "stroredProject";

        String query = "select brm " +
                "from Bug b, BugReportMessage brm " +
                "where brm.bug = b " +
                "and b.bugID = :" + paramBugID +
                " and b.project =:" + paramStoredProject +
                " order by brm.timestamp asc" ;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(paramBugID, bug.getBugID());
        params.put(paramStoredProject, bug.getProject());

        return (List<BugReportMessage>) dbs.doHQL(query, params);
    }

    /**
     * Get the latest entry for the bug with the provided Id.
     */
    public Bug getBug(String bugID, StoredProject sp) {
        DBService dbs = AlitheiaCore.getInstance().getDBService();

        String paramBugID = "paramBugID";
        String paramStoredProject = "stroredProject";

        String query = "select b " +
                "from Bug b " +
                "where b.bugID = :" + paramBugID +
                " and b.project = :" + paramStoredProject +
                " order by b.timestamp desc";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(paramBugID, bugID);
        params.put(paramStoredProject, sp);

        List<Bug> bug = (List<Bug>) dbs.doHQL(query, params, 1);

        if (bug.isEmpty())
            return null;
        else
            return bug.get(0);
    }

    /**
     * Get the latest entry processed by the bug updater
     */
    @SuppressWarnings("unchecked")
    public Bug getLastUpdate(StoredProject sp) {
        DBService dbs = AlitheiaCore.getInstance().getDBService();

        if (sp == null)
            return null;

        String paramStoredProject = "storedProject";

        String query = " select b " +
                " from Bug b, StoredProject sp" +
                " where b.project=sp" +
                " and sp = :" + paramStoredProject +
                " order by b.updateRun desc";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(paramStoredProject, sp);

        List<Bug> buglist = (List<Bug>) dbs.doHQL(query, params,1);

        if (buglist.isEmpty())
            return null;

        return buglist.get(0);
    }

}
