package com.athena.paltform.raft.task;

import java.util.List;

public interface ITaskExecutor {

    public void execute(List<ITask> tasks);

}
