package com.athena.paltform.raft.data;

import com.athena.paltform.raft.task.ITask;
import com.rcplatform.athena.raft.loader.grpc.Member;
import com.rcplatform.athena.raft.loader.grpc.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberDto {

    private String name;
    private String host;
    private Integer port;
    private Integer status;
    private List<ITask> tasks;
    private Integer term;

    public MemberDto() {}

    public MemberDto(Member Member){
        this.name = Member.getName();
        this.host = Member.getHost();
        this.port = Member.getPort();
        this.status = Member.getStatus();
        this.tasks = transFrom(Member.getTasksList());
        this.term = Member.getTerm();
    }

    private List<ITask> transFrom(List<Task> tasks) {

        return null;

    }

}
