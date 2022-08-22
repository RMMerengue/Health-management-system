package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.zookeeper.Op;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> findByCondition(String queryString);
    public CheckGroup findById(Integer id);
    public List<Integer> findCheckItemByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup);
    public void deleteAssociation(Integer id);
    public void deleteById(Integer id);
    public List<CheckGroup> findAll();
}
