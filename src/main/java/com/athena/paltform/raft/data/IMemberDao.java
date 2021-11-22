package com.athena.paltform.raft.data;

import java.util.List;

public interface IMemberDao {

    List<MemberDto> currentMembers();

    void registerMember(MemberDto memberDto);

    void resetMembers(List<MemberDto> memberDtos);

}