package com.athena.paltform.raft.cluster;

import com.rcplatform.athena.raft.loader.grpc.Members;
import com.rcplatform.athena.raft.loader.grpc.Member;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RaftClientPool {

    private static final Map<String, RaftClient> pool = new ConcurrentHashMap();

    public RaftClient raftMember(Member member) {
        return pool.computeIfAbsent(member.getName(), key -> {
            return new RaftClient(member);
        });
    }

    public List<RaftClient> raftMembers() {
        return pool.values().stream().collect(Collectors.toList());
    }

    public Members Members() {
        return Members.getDefaultInstance().toBuilder()
                .addAllMembers(raftMembers().stream().map(RaftClient::getMember)
                        .collect(Collectors.toList())).build();
    }

}
