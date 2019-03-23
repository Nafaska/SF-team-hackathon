package com;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.query.WorkItemCollection;
import com.microsoft.tfs.core.util.URIUtils;
import com.microsoft.tfs.core.httpclient.Credentials;
import com.microsoft.tfs.core.httpclient.UsernamePasswordCredentials;
import java.util.ArrayList;

import static com.Settings.PASSWORD;
import static com.Settings.USERNAME;

public class TFSCreator {

    private static TFSCreator instance;
    private Project project;

    public TFSCreator() {
        Credentials credentials = new UsernamePasswordCredentials(USERNAME, PASSWORD);
        TFSTeamProjectCollection tpc = new TFSTeamProjectCollection(URIUtils.newURI(Settings.TFS_URL), credentials);
        project = tpc.getWorkItemClient().getProjects().get(Settings.PROJECT_NAME);
    }

    public static TFSCreator getInstance() {
        if (instance == null) {
            instance = new TFSCreator();
        }
        return instance;
    }

    public ArrayList<WorkItem> getWorkItemsByQuery(String query) {
        ArrayList<WorkItem> workItems = new ArrayList<WorkItem>();
        WorkItemClient workItemClient = project.getWorkItemClient();
        WorkItemCollection workItemCollection = workItemClient.query(query);
        for (int i = 0; i < workItemCollection.size(); i++) {
            workItems.add(workItemCollection.getWorkItem(i));
        }
        return workItems;
    }
}
