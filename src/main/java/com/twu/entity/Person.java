package com.twu.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public String name;

    public static List<HotSearch> hotSearchRankings = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public static boolean findHotSearch(String hotSearchName) {
        return hotSearchRankings.stream().anyMatch(hotSearch -> hotSearch.hotSearchName.equals(hotSearchName));
    }

    public void getHotSearchRankings() {
        int count = 1;
        for (HotSearch val : hotSearchRankings) {
            System.out.println(count++ + "  " + val.hotSearchName + "  " + val.heat);
        }
    }

    public void addHotSearch(HotSearch hotSearch) {
        for (HotSearch val : hotSearchRankings) {
            if (val.hotSearchName.equalsIgnoreCase(hotSearch.hotSearchName)) {
                System.out.println("该热搜已存在，请勿重复提交！");
                return;
            }
        }
        hotSearchRankings.add(hotSearch);
        System.out.println("添加成功！");
    }

}
