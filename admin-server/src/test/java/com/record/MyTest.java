package com.record;

import com.record.utils.RecentMonth;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class MyTest {

    @Test
    public void test(){
        String str = "2022-03";
        String substring = str.substring(5);
        System.out.println(substring);

        Integer integer = new Integer(10);
        System.out.println("integer = " + integer);

        Integer[] integers = new Integer[10];
        System.out.println(integers);

        List<Integer> list = Arrays.asList(new Integer(10));
        System.out.println(list);
    }

    @Test
    public void test1(){
        Map<String, Integer> map = new HashMap<>();
        map.put("01",234);
        map.put("10",3223);
        map.put("04",32313);

        List<Integer> list = new ArrayList<>();
        int month = 5 + 1; //  6 7 8 9 10 11 12 1 2 3 4 5

        // 将月份添加到一个 list 中
        for (int i = 0; i < 12; i++) {
            month --;
            if (month <= 0) month += 12;
            // if (month < 10) month = '0' + month;
            list.add(month);
        }
        // 反转 list 得到 需要的月份，按照从远到近排列
        Collections.reverse(list);
        System.out.println("list = " + list);

        // 数据处理
        list.forEach(x ->{
            int index = list.indexOf(x);
            list.set(index,0);
            map.forEach((k,v) -> {
                int key = Integer.parseInt(k);
                if (x == key) list.set(index,v);
            });
        });
        System.out.println("list = " + list);

    }

    @Test
    public void test2(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("month = " + month);

        List<Integer> list = RecentMonth.getRecentMonth(8);
        System.out.println(list);
    }

    @Test
    public void test3(){
        Map<String, Integer> map = new HashMap<>();
        map.put("01",234);
        map.put("10",3223);
        map.put("04",32313);


        List<Integer> list = Arrays.asList(new Integer[12]);

        List<Integer> month = RecentMonth.getRecentMonth(12);
        System.out.println("month = " + month);
        month.forEach(x ->{
            int index = month.indexOf(x);
            list.set(index,0);
            map.forEach((k,v) -> {
                int key  = Integer.parseInt(k);
                if (x == key) list.set(index,v);
            });
        });
        System.out.println("list = " + list);

    }

    @Test
    public void test4(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("当前星期(日期)：" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_WEEK, 0);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        System.out.println("当前星期第一天(日期)：" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.DAY_OF_WEEK, 0);
        System.out.println("当前星期最后一天(日期)：" + sdf.format(calendar.getTime()));
    }

    @Test
    public void test5(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("当前月份：" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("当前月份第一天：" + sdf.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        System.out.println("当前月份最后一天：" + sdf.format(calendar.getTime()));
    }
}
