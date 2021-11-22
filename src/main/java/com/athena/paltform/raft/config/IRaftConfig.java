package com.athena.paltform.raft.config;

import com.rcplatform.athena.raft.loader.grpc.Member;

public interface IRaftConfig {

    public String getLocalHost();
    public String getServerName();
    public Integer getServerPort();

    public Member getLocalEntity();
    public Integer getHeartBeatTimeout();
    public Integer getElectionTimeoutMin();
    public Integer getElectionTimeoutMax();

}
