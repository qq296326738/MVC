//package com.qmx.member.front.listener;
//
//import com.alibaba.fastjson.JSONObject;
//import com.aliyun.openservices.ons.api.Action;
//import com.aliyun.openservices.ons.api.ConsumeContext;
//import com.aliyun.openservices.ons.api.Message;
//import com.aliyun.openservices.ons.api.MessageListener;
//import com.qmx.base.core.service.UserTokenService;
//import com.qmx.coreservice.service.SysUserService;
//import com.qmx.facade.enumerate.shop.common.OrderPaymentStatusEnum;
//import com.qmx.facade.enumerate.teamticket.PaymentStatus;
//import com.qmx.facade.model.core.SysUser;
//import com.qmx.facade.model.shop.ticket.Order;
//import com.qmx.facade.model.ticket.SysOrder;
//import com.qmx.marketing.service.WxIssuingCodeService;
//import com.qmx.marketing.service.WxIssuingCouponsService;
//import com.qmx.marketing.service.WxShareCouponService;
//import com.qmx.shop.service.qrcode.OrderService;
//import com.qmx.shop.service.qrcode.ProductService;
//import com.qmx.ticket.service.SysOrderService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Author liubin
// * @Description 阿里云通知基类
// * @Date Created on 2018/1/10 11:45.
// * @Modified By
// */
//@Component
//public class FrontNotifyListener implements MessageListener {
//
//    private static final Logger logger = LoggerFactory.getLogger(FrontNotifyListener.class);
//    @Autowired
//    private UserTokenService userTokenService;
//    @Autowired
//    private SysOrderService sysOrderService;
//    @Autowired
//    private SysUserService sysUserRemoteService;
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private com.qmx.shop.service.ticket.OrderService ticketOrderService;
//    @Autowired
//    private WxShareCouponService wxShareCouponRemoteService;
//    @Autowired
//    private WxIssuingCouponsService wxIssuingCouponsService;
//
//
//    @Override
//    public Action consume(Message message, ConsumeContext consumeContext) {
//        logger.info("Receive-start=> msgId:" + message.getMsgID() + ",tag:" + message.getTag() + ",ReconsumeTimes:" + message.getReconsumeTimes());
//        long s = System.currentTimeMillis();
//        byte[] content = message.getBody();
//        String contentStr = null;
//        try {
//            Thread.sleep(5000);
//            contentStr = new String(content, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JSONObject jsonObject = JSONObject.parseObject(contentStr);
//        try {
//            logger.info("Receive==> content:" + contentStr);
//            Long orderId = jsonObject.getLong("orderId");
//            String type = jsonObject.getString("saleChannel");
//            SysOrder order = null;
//            if (message.getTag().equals("TAG_TICKET_PAY_ORDER")) {
//                try {
//                    if ("WAP_SHOP".equals(type) || "QR_CODE_PAY".equals(type) || "PC_SHOP".equals(type)) {
//                        order = sysOrderService.find(orderId);
//                        if (order == null) {
//                            throw new BusinessException("订单不存在");
//                        }
//                        Long userId = order.getMemberId();
//                        SysUser sysUser = sysUserRemoteService.find(userId);
//                        if (sysUser == null) {
//                            throw new BusinessException("不存在的userId");
//                        }
//                        sysOrderService.orderShipping(orderId, sysUser);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                //销售统计
//                if ("QR_CODE_PAY".equals(type)) {
//                    productService.salesFigures(orderId);
//                }
//                //优惠券状态修改
//                if ("WAP_SHOP".equals(type)) {
//                    Long id = Long.valueOf(order.getOutSn());
//                    Order order1 = ticketOrderService.find(id);
//                    order1.setPaymentStatus(OrderPaymentStatusEnum.paid);
//                    ticketOrderService.update(order1);
//                    if (order1 != null && order1.getCouponState() != null && order1.getCouponTypeState() != null) {
//                        if (order1.getCouponTypeState().equals("coupon")) {
//                            wxShareCouponRemoteService.changeShareCouponsType(order.getCouponCode());
//                        } else if (order1.getCouponTypeState().equals("promotionCode")) {
//                            wxIssuingCouponsService.changeTypeByCode(order1.getCouponCode(), order1.getMemberId(), order1.getProductId());
//                        }
//                    }
//                }
//            } else if (message.getTag().equals("TAG_TICKET_AGREE_REFUND_ORDER")) {
//                Long refundId = jsonObject.getLong("refundId");
//                if ("QR_CODE_PAY".equals(type)) {
//                    orderService.refund(orderId, refundId);
//                    productService.salesFigures(orderId);
//                } else if ("WAP_SHOP".equals(type) || "PC_SHOP".equals(type)) {
//                    ticketOrderService.refund(orderId, refundId);
//                }
//            }
//        } catch (Exception e) {
//            logger.warn("消息消费出错:{}", e);
//            return Action.CommitMessage;
//        }
//        //url通知
//        logger.info("Receive-end=> msgId:" + message.getMsgID() + ",tag:" + message.getTag() + ",cost:" + (System.currentTimeMillis() - s) + "ms");
//        return Action.CommitMessage;
//    }
//}
