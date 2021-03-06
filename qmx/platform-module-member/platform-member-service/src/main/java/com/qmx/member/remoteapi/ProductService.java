package com.qmx.member.remoteapi;

import com.qmx.base.api.base.RestResult;
import com.qmx.base.api.dto.PageDto;
import com.qmx.shop.model.ticket.Product;
import com.qmx.shop.query.ticket.ProductVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 查询门票
 */
@FeignClient("${com.qmx.ticketservice.name}")
@RequestMapping("/internal-api/mpProductApi")
@Component
public interface ProductService {

    @RequestMapping(value = "queryList", method = RequestMethod.POST)
    RestResult<PageDto<Product>> queryList(@RequestBody ProductVO productVO,
                                           @RequestParam("currentUserId") Long currentUserId);

}
