package com.athena.paltform.raft.config;

import com.rcplatform.athena.raft.loader.grpc.Member;
import lombok.Data;
import lombok.Getter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import static com.athena.paltform.raft.config.MemberStatus.Follow;

@Getter
public class LocalRaftConfig implements IRaftConfig {

    private final String localHost;
    private final String serverName;
    private final Integer serverPort;
    private final Member localEntity;
    private Integer heartBeatTimeout;
    private Integer electionTimeoutMin;
    private Integer electionTimeoutMax;

    public LocalRaftConfig() {
        this.serverPort = 8693;
        this.localHost = localHost();
        this.serverName = serverName();
        this.localEntity = Member();
    }

    String localHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    String serverName() {
        return localHost() + UUID.randomUUID()
                .toString().replace("-", "");
    }

    Member Member() {
        return Member
                .getDefaultInstance()
                .toBuilder()
                .setName(serverName)
                .setHost(localHost)
                .setPort(this.serverPort)
                .setStatus(Follow.getStatus())
                .setTerm(0)    // init is 0
                .build();
    }
}
