package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.member.model.GdsInvite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GdsInviteMapper extends IBaseMapper<GdsInvite> {

    /**
     * @param openId openid
     * @return 返回不等于0说明已经关注过
     */
    int findByOpenId(@Param("openId") String openId);

    /**
     * 查询邀请人与被邀人的记录
     *
     * @param userId 邀请人id
     * @param openId 被邀人openid
     * @return 邀请记录
     */
    GdsInvite findByUserIdAndOpenId(@Param("userId") Long userId, @Param("openId") String openId);
}
