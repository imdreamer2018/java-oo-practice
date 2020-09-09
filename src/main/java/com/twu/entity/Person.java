package com.twu.entity;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public String name;

    static List<HotSearch> commonHotSearchRankings = new ArrayList<>();

    static List<HotSearch> purchaseHotSearchRankings = new ArrayList<>();

    static List<HotSearch> hotSearchRankings = new ArrayList<>();

    Person(String name) {
        this.name = name;
    }

    static int findHotSearch(String hotSearchName) {
        if (commonHotSearchRankings.stream().anyMatch(hotSearch -> hotSearch.hotSearchName.equals(hotSearchName)))
            return 1;
        else if (purchaseHotSearchRankings.stream().anyMatch(hotSearch -> hotSearch.hotSearchName.equals(hotSearchName)))
            return 2;
        else
            return 0;
    }

    public void getHotSearchRankings() {
        int count = 1;
        System.out.println("排名  描述  热度");
        for (HotSearch val : hotSearchRankings) {
            System.out.println(count++ + "  " + val.hotSearchName + "  " + val.heat);
        }
    }

    public void addHotSearch(HotSearch hotSearch) {
        for (HotSearch val : commonHotSearchRankings) {
            if (val.hotSearchName.equalsIgnoreCase(hotSearch.hotSearchName)) {
                System.out.println("该热搜已存在，请勿重复提交！");
                return;
            }
        }
        commonHotSearchRankings.add(hotSearch);
        hotSearchRankings.add(hotSearch);
        System.out.println("添加成功！");
    }

    static void sortHotSearchRankings() {
        hotSearchRankings.clear();
        int count = 0;
        for (int i = 0; i < commonHotSearchRankings.size() + purchaseHotSearchRankings.size(); i++) {
            if (!purchaseHotSearchRankings.isEmpty() && purchaseHotSearchRankings.size() > (i - count) && purchaseHotSearchRankings.get(i - count).purchaseRank == i + 1) {
                hotSearchRankings.add(purchaseHotSearchRankings.get(i - count));
            } else {
                hotSearchRankings.add(commonHotSearchRankings.get(count));
                count++;
            }
        }
    }

}
