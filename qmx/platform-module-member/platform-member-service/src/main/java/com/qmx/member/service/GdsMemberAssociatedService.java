package com.qmx.member.service;

import com.qmx.base.api.annotation.GdsCacheConfig;
import com.qmx.base.core.base.BaseService;
import com.qmx.member.enumerate.ProductType;
import com.qmx.member.mapper.GdsMemberAssociatedMapper;
import com.qmx.member.model.GdsMemberAssociated;
import com.qmx.member.query.GdsMemberActivityIntegralVO;
import com.qmx.member.query.GdsMemberConsumptionVO;
import com.qmx.member.query.GdsMemberDiscountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员优惠规则
 */
@Service
@GdsCacheConfig(cacheNS = "GDS-MEMBER", cacheName = "GdsMemberAssociated")
public class GdsMemberAssociatedService extends BaseService<GdsMemberAssociated> {

    @Autowired
    private GdsMemberAssociatedMapper gdsMemberAssociatedMapper;

    /**
     * 获得会员优惠消费关联产品
     *
     * @param id
     * @param type
     * @return
     */
    public List<GdsMemberAssociated> findByConsumptionId(Long id, ProductType type) {
        return gdsMemberAssociatedMapper.findByConsumptionId(id, type);
    }

    /**
     * 删除会员优惠消费关联产品
     *
     * @param id
     */
    @Transactional
    public void delByConsumptionId(Long id) {
        gdsMemberAssociatedMapper.delByConsumptionId(id);
    }

    /**
     * 查询相同等级是否有重复关联产品(消费)
     *
     * @param dto
     * @return
     */
    public Integer selectCountByConsumptionIds(GdsMemberConsumptionVO dto) {
        List<String> ids = dto.getIds();
        List<String> list = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (String s : ids) {
                String substring = s.substring(0, s.indexOf("&"));
                list.add(substring);
            }
            return gdsMemberAssociatedMapper.selectCountByConsumptionIds(dto, list);
        } else {
            return 0;
        }
    }

    /**
     * 查询相同等级是否有重复关联产品(优惠)
     *
     * @param dto
     * @return
     */
    public Integer selectCountByDiscountIds(GdsMemberDiscountVO dto) {
        List<String> ids = dto.getIds();
        List<String> list = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (String s : ids) {
                String substring = s.substring(0, s.indexOf("&"));
                list.add(substring);
            }
            return gdsMemberAssociatedMapper.selectCountDiscountByIds(dto, list);
        } else {
            return 0;
        }
    }

    /**
     * 查询相同等级是否有重复关联产品(多倍积分)
     *
     * @param vo
     * @return
     */
    public Integer selectCountByActivityIntegralIds(GdsMemberActivityIntegralVO vo) {
        List<String> ids = vo.getIds();
        List<String> list = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (String s : ids) {
                String substring = s.substring(0, s.indexOf("&"));
                list.add(substring);
            }
            return gdsMemberAssociatedMapper.selectCountActivityIntegralIds(vo, list);
        } else {
            return null;
        }
    }

    @Transactional
    public void savaAssociated(Long id, List<String> ids) {
        //1016983326951444482&测试抢购&menpiao   产品ID&产品名称&产品类型
        GdsMemberAssociated associated = new GdsMemberAssociated();
        if (ids != null && ids.size() != 0) {
            for (String s : ids) {
                String[] split = s.split("&");
                associated.setIds(id);
                associated.setProductId(Long.valueOf(split[0].trim()));
                associated.setProductName(split[1].trim());
                for (ProductType type : ProductType.values()) {
                    if (type.name().equals(split[2].trim()))
                        associated.setProductType(type);
                }
                this.save(associated);
            }
        }
    }


}
