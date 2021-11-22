package com.athena.paltform.raft.cluster;

import com.athena.paltform.raft.config.IRaftConfig;
import com.athena.paltform.raft.data.IMemberDao;
import com.athena.paltform.raft.data.MemberDto;
import com.athena.paltform.raft.task.ITaskBalancer;
import com.athena.paltform.raft.task.ITaskExecutor;
import com.athena.paltform.raft.task.ITaskLoader;

import java.io.IOException;

import static com.google.common.collect.ImmutableList.*;

public class RaftBootStrap {

    private final IMemberDao memberDao;
    private final IRaftConfig raftConfig;
    private final RaftStarter raftStarter;
    private final RaftServer raftServer;
    private final RaftClientPool raftClientPool;
    private final ITaskExecutor taskExecutor;
    private final ITaskLoader taskLoader;
    private final ITaskBalancer taskBalancer;

    public RaftBootStrap(IMemberDao memberDao, IRaftConfig raftConfig, ITaskExecutor
            taskExecutor, ITaskLoader taskLoader, ITaskBalancer taskBalancer) {
        this.memberDao = memberDao;
        this.raftConfig = raftConfig;
        this.taskExecutor = taskExecutor;
        this.taskLoader = taskLoader;
        this.taskBalancer = taskBalancer;
        this.raftClientPool = new RaftClientPool();
        this.raftServer = new RaftServer(raftClientPool, memberDao, raftConfig,
                this.taskExecutor, this.taskLoader, this.taskBalancer);
        this.raftStarter = new RaftStarter(of(raftServer), raftConfig);
    }

    public void bootStrap() throws IOException {
        this.startGrpcServer();
        this.registerLocalServer();
        this.joinCluster();
    }

    public void registerLocalServer(){
        this.memberDao.registerMember(
                new MemberDto(this.raftConfig.getLocalEntity()));
    }

    public void startGrpcServer() throws IOException {
        this.raftStarter.startUp();
    }

    public void joinCluster(){
        this.raftServer.joinCluster(this.memberDao.currentMembers());
    }
}
