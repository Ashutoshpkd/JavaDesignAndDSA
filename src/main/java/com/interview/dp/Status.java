package com.interview.dp;

public enum Status {
    IN_PROGRESS("in_progress"),
    COMPLETED("completed"),
    REVIEW("review"),
    FAILED("failed"),
    NOT_APPLICABLE("not_applicable");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}
