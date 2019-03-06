package com.qmx.member.remoteapi;

import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.dto.PageDto;
import com.qmx.shop.model.commodity.Release;
import com.qmx.shop.query.commodity.ReleaseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 查询商品
 */
@FeignClient("${com.qmx.ticketservice.name}")
@RequestMapping("/internal-api/releaseApi")
@Component
public interface ReleaseService {

    @RequestMapping(value = "findPage", method = RequestMethod.POST)
    RestResult<PageDto<Release>> findPage(@RequestBody ReleaseVO releaseVO,
                                          @RequestParam("currentUserId") Long currentUserId);

}
