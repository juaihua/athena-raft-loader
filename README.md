## raft 细节

    
    1、不能光是启用 -DjoinServer 的情况，因为要考虑将来容器话动态太缩，所以此外还适应redis支持下！

    2、node-A通过SyncMembers() 被触发后，检查参数中的master 和 自己本地的是否一样，如果不一样，
    比term,以大的为准：
        
        a、如果自己的是小的，则直接以参数master为准，然后向新的master注册自己。
	    
        b、如果自己是大的，则证明发生了脑裂，直接通知对方更细新的正确的master,落后
        的master收到以后，也通知他所能通知到的节点，更新新的master，然后注册。

    3、master收到心跳响应后， 检查和自己本机信息是否一致，如果不一致则说明，自己发生了网络隔离故障，切目前选
    出了新的有效的master, 向他注册即可。

    4、如果发生了同时多个follow变成candidate 然后申请成为master, 那么就让多个candidate同时退回为follow。

    5、每当follow接收到一次vote请求或者master的心跳同步，就重置一次follow的election—timeout的时间。

    # athena-raft-loader
