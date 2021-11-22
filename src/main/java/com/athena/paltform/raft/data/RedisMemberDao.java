package com.athena.paltform.raft.data;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class RedisMemberDao implements IMemberDao{

    private final String BASE_PATH = "rc:architecture:raft:members";

    private final RedisTemplate redisTemplate;

    public RedisMemberDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<MemberDto> currentMembers() {
        return redisTemplate.opsForList().range(BASE_PATH, 0, -1);
    }

    @Override
    public void registerMember(MemberDto memberDto) {
        redisTemplate.opsForList().leftPush(BASE_PATH, memberDto);
    }

    @Override
    public void resetMembers(List<MemberDto> memberDtos) {
        memberDtos.forEach(dto ->
                redisTemplate.opsForList().leftPush(BASE_PATH, dto));
    }

}
