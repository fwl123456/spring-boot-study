package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {

    @Autowired
    private EmpService empService;

    // @RequestParam用在前端没有传递这个值的时候 给参数赋了一个默认值
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询: {},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        // 调用service分页查询
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    // 通过id或ids删除员工
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作: ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    // 新增员工
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    // 查询员工
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工id: {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    // 根据id修改员工信息
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
