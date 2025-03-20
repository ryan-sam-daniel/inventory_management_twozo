package com.twozo.reports;

import java.util.List;

public class Report {
    private final String title;
    private final List<String> reportContents;
    
    Report (String title, List<String> reportContents){
        this.title = title;
        this.reportContents = reportContents;
    }

    public String getTitle() {
        return title;
    }
    
    public List<String> getReportContents() {
        return reportContents;
    }

    public void reportPrinter(){
        System.out.print("** Title : " +title+"\n");
        for (String content : reportContents){
            System.out.println(content);
        }
    }

}
