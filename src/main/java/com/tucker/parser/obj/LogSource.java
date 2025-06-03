package com.tucker.parser.obj;

public class LogSource {

    // thinking to keep source as POJO?

    private String SelfName;
    // TODO add queue UUID here
    private String messageQueue;

    //TODO keep if deciding to group log collectors by source?
    //private String ClusterName

    public LogSource(String SelfName, String MessageQueue) {
        this.SelfName = SelfName;
        this.messageQueue = MessageQueue;
    }
}
