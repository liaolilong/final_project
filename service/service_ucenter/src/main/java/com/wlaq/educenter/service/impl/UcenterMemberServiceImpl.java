package com.wlaq.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.security.commonutils.JwtUtils;
import com.security.commonutils.MD5;
import com.wlaq.educenter.entity.UcenterMember;
import com.wlaq.educenter.entity.vo.RegisterVo;
import com.wlaq.educenter.mapper.UcenterMemberMapper;
import com.wlaq.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wlaq.servicebase.exceptionhandler.wlaqException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-13
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
    //登陆的方法
    @Override
    public String login(UcenterMember member) {

        //获取登陆手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new wlaqException(20001,"登陆失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileMember == null){//没有这个手机号
            throw new wlaqException(20001,"登陆失败");
        }

        //判断密码
        //存储到数据库密码加密
        //把输入的密码进行比较，再进行数据库密码进行比较
        //加密方式md5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new wlaqException(20001,"登陆失败");
        }

        //判断用户是否被禁用
        if(mobileMember.getIsDisabled()){
            throw new wlaqException(20001,"登陆失败");
        }

        //登陆成功
        //生成token字符串，使用jwt工具类做到
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }

    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册数据
        //String code = registerVo.getCode();//验证码

        String mobile = registerVo.getMobile();//手机号
        String nickname = registerVo.getNickname();//昵称
        String password = registerVo.getPassword();//密码

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
        ||StringUtils.isEmpty(nickname)){
            throw new wlaqException(20001,"注册失败");
        }

        //判断验证码
        //获取redis验证码
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if (!code.equals(redisCode)){
//            throw new wlaqException(20001,"注册失败");
//        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new wlaqException(20001,"注册失败");
        }

        //数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);//用户不禁用
        member.setAvatar("https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/5805c6cd-c8ad-4a77-aafd-d2e083bfd8a4.png");
        baseMapper.insert(member);
    }

    //根据openid判断
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }
    //查询一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
