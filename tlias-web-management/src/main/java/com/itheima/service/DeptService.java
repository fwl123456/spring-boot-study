package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;

import java.util.List;

public interface DeptService {

    // 查询全部部门数据
    List<Dept> list();

    // 删除部门
    void delete(Integer id);

    // 新增部门
    void add(Dept dept);

    void update(Dept dept);

    Dept show(Integer id);
}
