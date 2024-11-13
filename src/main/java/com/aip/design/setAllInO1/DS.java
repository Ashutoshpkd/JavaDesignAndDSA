package com.aip.design.setAllInO1;

import java.time.LocalDateTime;
import java.util.Objects;

public class DS {
    private boolean isJokerChanged;
    private LocalDateTime jokerSetTime;

    private Node[] ds;

    private Integer jokerData;

    public DS(int len) {
        this.ds = new Node[len];
        this.isJokerChanged = false;
        this.jokerSetTime = null;
        this.jokerData = null;
    }

    public void setAll(int value) {
        this.isJokerChanged = true;
        this.jokerSetTime = LocalDateTime.now();
        this.jokerData = value;
    }

    public void set(int idx, int value) {
        this.isJokerChanged = false;
        this.ds[idx] = new Node(value, jokerSetTime);
    }

    public Integer get(int idx) {
        if (idx >= ds.length) return null;

        if (this.isJokerChanged) return jokerData;

        if (Objects.nonNull(ds[idx]) && ds[idx].getData() != null) {
            if (this.isJokerChanged) return this.jokerData;

            if (ds[idx].getUpdatedTs() == jokerSetTime) {
                return ds[idx].getData();
            }

        }
        return this.jokerData;
    }
}
