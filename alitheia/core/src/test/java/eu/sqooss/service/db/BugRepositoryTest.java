package eu.sqooss.service.db;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.impl.service.db.BaseDBServiceImpl;
import eu.sqooss.service.db.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BugRepositoryTest {

    AlitheiaCore alitheiaCore = mock(AlitheiaCore.class);
    DBService dbService = mock(BaseDBServiceImpl.class);

    @Before
    public void setUp() {
        AlitheiaCore.setInstance(alitheiaCore);
        when(alitheiaCore.getDBService()).thenReturn(dbService);
    }

    @Test
    public void testGetAllReportComments() {
        List bugReportMessages = new ArrayList<>();
        bugReportMessages.add(new BugReportMessage());
        bugReportMessages.add(new BugReportMessage());
        bugReportMessages.add(new BugReportMessage());
        when(dbService.doHQL(anyString(), anyMap())).thenReturn(bugReportMessages);

        BugRepository bugRepository = new BugRepository();
        List<?> result = bugRepository.getAllReportComments(new Bug());

        assertThat(result, is(bugReportMessages));
    }

    @Test
    public void testGetBug() {
        List bugs = new ArrayList();
        Bug bug = new Bug();
        bugs.add(bug);
        when(dbService.doHQL(anyString(), anyMap(), anyInt())).thenReturn(bugs);

        BugRepository bugRepository = new BugRepository();
        Bug result = bugRepository.getBug("1", null);

        assertThat(result, is(bug));
    }

    @Test
    public void testGetBugEmptyResult() {
        List bugs = new ArrayList();
        when(dbService.doHQL(anyString(), anyMap(), anyInt())).thenReturn(bugs);

        BugRepository bugRepository = new BugRepository();
        Bug result = bugRepository.getBug("1", new StoredProject());

        assertNull(result);
    }

    @Test
    public void testGetLastUpdate() {
        List bugs = new ArrayList();
        Bug bug = new Bug();
        bugs.add(bug);
        when(dbService.doHQL(anyString(), anyMap(), anyInt())).thenReturn(bugs);

        BugRepository bugRepository = new BugRepository();
        Bug result = bugRepository.getLastUpdate(new StoredProject());

        assertThat(result, is(bug));
    }

    @Test
    public void testGetLastUpdateNoStoredProject() {
        List bugs = new ArrayList();
        Bug bug = new Bug();
        bugs.add(bug);
        when(dbService.doHQL(anyString(), anyMap(), anyInt())).thenReturn(bugs);

        BugRepository bugRepository = new BugRepository();
        Bug result = bugRepository.getLastUpdate(null);

        assertNull(result);
    }

    @Test
    public void testGetLastUpdateEmptyResult() {
        List bugs = new ArrayList();
        when(dbService.doHQL(anyString(), anyMap(), anyInt())).thenReturn(bugs);

        BugRepository bugRepository = new BugRepository();
        Bug result = bugRepository.getLastUpdate(new StoredProject());

        assertNull(result);
    }

}
