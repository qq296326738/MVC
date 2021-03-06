package com.qmx.member.mapper;

import com.qmx.base.core.base.IBaseMapper;
import com.qmx.coreservice.model.SysUser;
import com.qmx.member.model.GdsMemberLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GdsMemberLevelMapper extends IBaseMapper<GdsMemberLevel> {
    /**
     * 根据等级名字查询等级数据
     *
     * @param currentUser
     * @param name
     * @return
     */
    List<GdsMemberLevel> findByName(@Param("cm") SysUser currentUser, @Param("name") String name);

    /**
     * 获得所有等级
     *
     * @param currentUser
     * @return
     */
    List<GdsMemberLevel> findLevelAll(@Param("cm") SysUser currentUser);

    /**
     * 查询默认等级
     *
     * @param userId
     * @return
     */
    List<GdsMemberLevel> selectionLevel(@Param("id") Long userId);

    /**
     * 删除升级到本等级的升级id及升级状态
     *
     * @param id
     */
    void delUpgradeId(@Param("id") Long id);

    List<GdsMemberLevel> selectionLevelList(@Param("id") Long userId);


//    Integer delUpgradeByLevelId(@Param("id") Long id);
}
