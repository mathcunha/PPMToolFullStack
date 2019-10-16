package com.mathcunha.ppmtool.domain;

public class PhotoStatistics {
    private String owner;
    private Long   cnt;

    public PhotoStatistics(String owner, Long cnt) {
        this.owner = owner;
        this.cnt = cnt;
    }

    public String getOwner() {
        return owner;
    }

    public Long getCnt() {
        return cnt;
    }

    @Override
    public String toString() {
        return "PhotoStatistics{" +
                "owner='" + owner + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
