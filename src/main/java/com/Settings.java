package com;

public class Settings {
    public static final String TFS_URL = "https://acx-tfs.visualstudio.com/_projects";
    public static final String PROJECT_NAME = "Search";
    public static final String USERNAME = "";
    public static final String PASSWORD = "";
    public static final String QUERY = "Select ID, Title from WorkItems where (State = 'Active') order by Title";
}
