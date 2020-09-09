package com.twu.entity;

public class Admin extends Person {

    public String password;

    public Admin(String name, String password) {
        super(name);
        this.password = password;
    }

    public void addSuperHotSearch(HotSearch hotSearch) {
        for (HotSearch val : hotSearchRankings) {
            if (val.hotSearchName.equalsIgnoreCase(hotSearch.hotSearchName)) {
                System.out.println("该热搜已存在，请勿重复提交！");
                return;
            }
        }
        hotSearch.heatBase = 2;
        hotSearchRankings.add(hotSearch);
        commonHotSearchRankings.add(hotSearch);
    }
}
