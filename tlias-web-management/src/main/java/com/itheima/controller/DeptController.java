package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// @Slf4j等同于----->> private static Logger log = LoggerFactory.getLogger(DeptController.class);
@Slf4j
// 公共路径
@RequestMapping("/depts")
@RestController
public class DeptController {
    // 固定代码 通过slf4j包里的logger 记录日志
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    // 部门列表
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    // 删除部门
    // 参数如果是路径参数 传递的时候要增加一个注解@pathVariable
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除数据:{}", id);
        // 调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    // 新增部门
    // 把请求封装到实体类对象里要使用注解@requestbody
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}", dept);
        // 调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

    // 根据id查看部门
    @GetMapping("/{id}")
    public Result show(@PathVariable Integer id){
        log.info("修改部门: {}", id);
        // 调用service查看部门
        Dept dept = deptService.show(id);
        return Result.success(dept);
    }

    // 修改部门
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门: {}", dept);
        // 调用service修改部门
        deptService.update(dept);
        return Result.success();
    }
}
