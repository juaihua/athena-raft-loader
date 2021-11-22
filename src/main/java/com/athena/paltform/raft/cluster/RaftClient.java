package com.athena.paltform.raft.cluster;

import com.rcplatform.athena.raft.loader.grpc.Members;
import com.rcplatform.athena.raft.loader.grpc.GrpcRaftLoaderServiceGrpc.GrpcRaftLoaderServiceBlockingStub;
import com.rcplatform.athena.raft.loader.grpc.Member;
import io.grpc.ManagedChannel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.rcplatform.athena.raft.loader.grpc.GrpcRaftLoaderServiceGrpc.newBlockingStub;
import static io.grpc.ManagedChannelBuilder.forAddress;

@Slf4j
@Getter
public class RaftClient {

    private final Member member;
    private GrpcRaftLoaderServiceBlockingStub client;

    public RaftClient(Member member) {
        this.member = member;
    }

    public void connect() {
        this.client = newBlockingStub(
                forAddress(member.getHost(), member.getPort())
                        .usePlaintext().build());
    }

    public void close() {
        ((ManagedChannel) (
                this.client.getChannel())).shutdown();
    }

}