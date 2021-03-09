package com.wlaq.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wlaq.oss.service.OssService;
import com.wlaq.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    //上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流。
            InputStream inputStream = file.getInputStream();

            //1 通过uuid随机创建名字
            String uuid = UUID.randomUUID().toString().replaceAll("-","");

            //2 把文件按照日期进行分类
            // 2021/2/21/.jpg
            String datePath = new DateTime().toString("yyyy/MM/dd");


            //获取文件名称
            String OriginalFilename = file.getOriginalFilename();
            String fileName = datePath+"/"+uuid+OriginalFilename.substring(OriginalFilename.lastIndexOf("."));



            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径何文件名称
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            //https://edu-wlaq.oss-cn-beijing.aliyuncs.com/01.jpg

            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
