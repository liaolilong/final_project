package com.wlaq.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlaq.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wlaq.eduservice.entity.frontvo.CourseFrontVo;
import com.wlaq.eduservice.entity.vo.CourseInfoVo;
import com.wlaq.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程方法
    void removeCourse(String courseId);

    //1条件查询带分页查询课程前台
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);
}
