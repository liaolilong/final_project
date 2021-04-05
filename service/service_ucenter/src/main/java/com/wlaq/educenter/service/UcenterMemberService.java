package com.wlaq.educenter.service;

import com.wlaq.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlaq.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登陆的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);

    //根据openid判断
    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
