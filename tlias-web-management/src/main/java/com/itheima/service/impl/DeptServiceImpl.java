package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> list() {
       return deptMapper.list();
    }

    @Override
    // spring事务管理
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        try {
            // 根据id删除部门数据
            deptMapper.deleteById(id);
            // 模拟异常
            // int i = 1/0;
            // 删除部门之后删除emp员工表关联删除部门的员工
            empMapper.deleteByDeptId(id);
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是"+ id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        // 新增的时候dept对象里面只有前端传递封装的一个参数name
        // 所以在这里要补全对象其他信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public void update( Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        //dept.setId(id);
        deptMapper.update(dept);
    }

    @Override
    public Dept show(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }
}
