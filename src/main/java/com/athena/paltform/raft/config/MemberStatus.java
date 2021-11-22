package com.athena.paltform.raft.config;

public enum MemberStatus {
    
    Follow(1),
    Candidate(2),
    Master(3);
    
    private final Integer status;
    MemberStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
