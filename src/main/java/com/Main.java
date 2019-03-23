package main.java.com;

import com.Settings;
import com.TFSCreator;
import com.microsoft.tfs.core.clients.workitem.WorkItem;

public class Main {

    public static void main(String[] args) {
        TFSCreator tfsc = TFSCreator.getInstance();
        for (WorkItem item : tfsc.getWorkItemsByQuery(Settings.QUERY)) {
            System.out.println(item.getID() + '\t' + item.getTitle());
        }
    }
}