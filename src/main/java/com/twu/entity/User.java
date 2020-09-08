package com.twu.entity;

import java.util.Collections;

public class User extends Person{
    public int votes;


    public User(String name) {
        super(name);
        this.votes = 10;
    }

    public void voting(String hotSearchName, int votes) {
        if (this.votes < votes) {
            System.out.println("投票失败！");
            return;
        }
        if (!Person.findHotSearch(hotSearchName)) {
            System.out.println("找不到你需要投票的热搜事件，请重新输入！");
            return;
        }

        for (HotSearch val : hotSearchRankings) {
            if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                val.heat += val.heatBase * votes;
                System.out.println("投票成功！");
                Collections.sort(hotSearchRankings);
                break;
            }
        }

        this.votes -= votes;
    }

    public void purchase(String hotSearchName, Integer purchaseRank, Integer purchasePrice) {
        if (!Person.findHotSearch(hotSearchName)) {
            System.out.println("找不到你需要购买的热搜事件，请重新输入！");
            return;
        }
        if (purchaseRank > hotSearchRankings.size()) {
            System.out.println("暂无此排名热搜，请重新输入");
            return;
        }
        if (purchasePrice <= 0) {
            System.out.println("金额输入错误，请重新输入！");
            return;
        }
        if (hotSearchRankings.get(purchaseRank).purchaseRank != 0) {
            if (hotSearchRankings.get(purchaseRank).purchasePrice >= purchasePrice) {
                System.out.println("金额不够，无法购买当前排名热搜！");
                return;
            }else {
                hotSearchRankings.remove(hotSearchRankings.get(purchaseRank));
            }
        }
        for (HotSearch val : hotSearchRankings) {
            if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                val.purchaseRank = purchaseRank;
                val.purchasePrice += purchasePrice;
                System.out.println("购买成功！");
                Collections.sort(hotSearchRankings);
            }
        }

    }


}
