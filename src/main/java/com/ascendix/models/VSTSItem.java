package com.ascendix.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class VSTSItem extends VSTSEntity implements Serializable {
    private String url;
    private String type;
    private String state;
    private String title;
    private Long closedDate;
    private Map<String, String> fields;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public Long getClosedDate() {
        return closedDate;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
        if (fields != null && !fields.isEmpty()) {
            String stringDate = fields.get("Microsoft.VSTS.Common.ClosedDate");
            if (stringDate != null) {
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ").parse(stringDate.replaceAll("Z$", "+0000"));
                    closedDate = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                    closedDate = null;
                }
            }

            title = fields.get("System.Title");
            state = fields.get("System.State");
            type = fields.get("System.WorkItemType");
        }
    }
}
