package com.athena.paltform.raft.task;

import com.rcplatform.athena.raft.loader.grpc.Member;

import java.util.List;

public interface ITaskBalancer {

    public List<Member> balanceTasks(List<ITask> tasks);

}
