package com.aip.design.setAllInO1;

import java.time.LocalDateTime;

public class Node {
    private Integer data;
    private LocalDateTime updatedTs;

    public Node(int data, LocalDateTime updatedTs) {
        this.data = data;
        this.updatedTs = updatedTs;
    }

    public Integer getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public LocalDateTime getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(LocalDateTime updatedTs) {
        this.updatedTs = updatedTs;
    }
}
