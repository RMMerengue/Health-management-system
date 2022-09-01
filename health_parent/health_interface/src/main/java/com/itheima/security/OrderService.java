package com.itheima.security;

import com.itheima.entity.Result;
import java.util.Map;
/**
 * 体检预约服务接口
 */
public interface OrderService {
    //体检预约
    public Result order(Map map) throws Exception;
    public Map findById(Integer id) throws Exception;
}