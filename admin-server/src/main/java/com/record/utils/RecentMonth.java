package com.record.utils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class RecentMonth {

    public static List<Integer> getRecentMonth(Integer n){
        List<Integer> list = new ArrayList<>();
        // 获取当前月份
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;

        month = month + 1; //  6 7 8 9 10 11 12 1 2 3 4 5(当前月份为 5)

        // 将月份添加到一个 list 中
        for (int i = 0; i < n; i++) {
            month --;
            if (month <= 0) month += 12;
            // if (month < 10) month = '0' + month;
            list.add(month);
        }
        // 反转 list 得到 需要的月份，按照从远到近排列
        Collections.reverse(list);
        return list;
    }
}
