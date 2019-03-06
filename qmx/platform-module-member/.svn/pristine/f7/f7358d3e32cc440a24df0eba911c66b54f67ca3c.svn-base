package com.qmx.member.service;
import com.baomidou.mybatisplus.plugins.Page;
import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.api.dto.PageDto;
import com.qmx.base.core.base.BaseService;
import com.qmx.base.core.utils.InstanceUtil;
import com.qmx.member.model.SysApiLog;
import com.qmx.member.query.SysApiLogVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * @Author liubin
 * @Description 接口日志服务类
 * @Date Created on 2018/7/4 16:11.
 * @Modified By
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER",cacheName = "SysApiLog")
public class SysApiLogService extends BaseService<SysApiLog> {

    /**
     * 分页查询日志
     * @param sysApiLogVO
     * @return
     */
    public PageDto<SysApiLog> findPage(SysApiLogVO sysApiLogVO){
        Map<String,Object> params = InstanceUtil.transBean2StringMap(sysApiLogVO);
        Page<SysApiLog> page = super.query(params);
        PageDto<SysApiLog> pageDto = new PageDto<>(page.getTotal(),page.getSize(),page.getCurrent(),page.getRecords());
        return pageDto;
    }

    /**
     * 异步保存
     * @param apiLog
     */
    @Async
    public void saveApiLog(SysApiLog apiLog){
        try{
            //try出错不影响接口
            apiLog.setCreateBy(1L);
            apiLog.setUpdateBy(1L);
            super.save(apiLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}