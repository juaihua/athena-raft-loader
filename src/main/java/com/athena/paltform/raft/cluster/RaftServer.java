package com.athena.paltform.raft.cluster;


import com.athena.paltform.raft.config.IRaftConfig;
import com.athena.paltform.raft.data.IMemberDao;
import com.athena.paltform.raft.data.MemberDto;
import com.athena.paltform.raft.task.ITaskBalancer;
import com.athena.paltform.raft.task.ITaskExecutor;
import com.athena.paltform.raft.task.ITaskLoader;
import com.rcplatform.athena.raft.loader.grpc.Members;
import com.rcplatform.athena.raft.loader.grpc.DynamicTasks;
import com.rcplatform.athena.raft.loader.grpc.GrpcRaftLoaderServiceGrpc;
import com.rcplatform.athena.raft.loader.grpc.Member;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaftServer extends GrpcRaftLoaderServiceGrpc.GrpcRaftLoaderServiceImplBase {

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final RaftClientPool clientPool;
    private final IMemberDao memberDao;
    private final IRaftConfig raftConfig;
    private final ITaskExecutor taskExecutor;
    private final ITaskLoader taskLoader;
    private final ITaskBalancer taskBalancer;

    public RaftServer(RaftClientPool clientPool, IMemberDao memberDao,
                      IRaftConfig raftConfig, ITaskExecutor taskExecutor,
                      ITaskLoader taskLoader, ITaskBalancer taskBalancer) {
        this.clientPool = clientPool;
        this.memberDao = memberDao;
        this.raftConfig = raftConfig;
        this.taskExecutor = taskExecutor;
        this.taskLoader = taskLoader;
        this.taskBalancer = taskBalancer;
    }

    @Override
    public void requestMembers(
            Member request, StreamObserver<Members> responseObserver) {
        responseObserver.onNext(clientPool.Members());
        responseObserver.onCompleted();
    }

    @Override
    public void syncMembers(
            Members request, StreamObserver<Member> responseObserver) {
        super.syncMembers(request, responseObserver);
    }

    @Override
    public void requestVote(
            Member request, StreamObserver<Member> responseObserver) {
        super.requestVote(request, responseObserver);
    }

    @Override
    public void dynamicAssignTasks(
            DynamicTasks request, StreamObserver<Member> responseObserver) {
        super.dynamicAssignTasks(request, responseObserver);
    }

    public void joinCluster(List<MemberDto> currentMembers) {

        //@TODO first-check 如惨节点和当前是节点是否匹配，
        //开始参考 README.md 实现算法


    }

}

