package com.itheima.security.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.security.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{

    @Autowired
    private OrderSettingDao orderSettingDao;

    public void add(List<OrderSetting> list) {
        if(list != null && list.size()>0){
            for(OrderSetting orderSetting : list){
                long countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(countByOrderDate > 0){
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else{
                    orderSettingDao.add(orderSetting);
                }

            }

        }
    }

    public List<Map> getOrderSettingByMonth(String date) {
        System.out.println(date);
        String[] getYearAndMouth = date.split("-");
        Integer yyyy = Integer.parseInt(getYearAndMouth[0]);
        Integer mm = Integer.parseInt(getYearAndMouth[1]);
        String begin = date + "-1";
        Integer last;
        if(mm==1||mm==3||mm==5||mm==7||mm==8||mm==10||mm==12){
            last = 31;
        }else if(mm==4||mm==6||mm==9||mm==11){
            last = 30;
        }else{
            if(yyyy % 4 == 0 && yyyy % 100 != 0 || yyyy % 400 ==0){
                last = 29;
            }else{
                last = 28;
            }
        }
        String end = date + "-" + Integer.toString(last);


        Map<String, String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<>();

        if(list != null && list.size() > 0){
            for(OrderSetting orderSetting : list){
                Map<String, Object> m = new HashMap<>();
                m.put("date",orderSetting.getOrderDate().getDate());
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }

        return result;
    }

    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
