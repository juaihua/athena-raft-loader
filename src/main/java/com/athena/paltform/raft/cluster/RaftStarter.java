package com.athena.paltform.raft.cluster;

import com.athena.paltform.raft.config.IRaftConfig;
import com.athena.paltform.raft.config.LocalRaftConfig;
import com.athena.paltform.raft.data.MemberDto;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import static io.grpc.ServerBuilder.forPort;

@Slf4j
@Getter
public class RaftStarter {

    private final Integer port;
    private final Server server;
    private final IRaftConfig raftConfig;
    private final List<BindableService> serviceList;

    public RaftStarter(
            List<BindableService> serviceList,
            IRaftConfig raftConfig) {

        this.raftConfig = raftConfig;
        this.serviceList = serviceList;
        this.port = raftConfig.getServerPort();
        ServerBuilder<?> serverBuilder = forPort(this.port);
        serviceList.forEach(serverBuilder::addService);
        server = serverBuilder.build();

    }

    public void startUp() throws IOException {
        server.start();
        log.info("RaftStarter starting ... ");
    }

    public void shutDown() throws IOException {
        server.shutdown();
        log.info("RaftStarter shutting down  ... ");
    }

}

