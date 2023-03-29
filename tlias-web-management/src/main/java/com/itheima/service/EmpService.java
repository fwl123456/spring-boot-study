package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 分页查询
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    // 批量删除
    void delete(List<Integer> ids);

    // 新增员工
    void save(Emp emp);

    // 根据id查询员工
    Emp getById(Integer id);

    // 修改员工信息
    void update(Emp emp);

    // 员工登录
    Emp login(Emp emp);
}
