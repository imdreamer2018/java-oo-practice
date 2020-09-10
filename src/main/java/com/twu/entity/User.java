package com.twu.entity;

import com.twu.exception.BaseException;

import java.util.Collections;

public class User extends Person{
    public int votes;


    public User(String name) {
        super(name);
        this.votes = 10;
    }

    public void voting(String hotSearchName, int votes) {
        if (this.votes < votes) {
            throw new BaseException("投票失败！");
        }
        int findHotSearch = this.findHotSearch(hotSearchName);
        if (findHotSearch == 0) {
            throw new BaseException("找不到你需要投票的热搜事件，请重新输入！");
        }
        if (findHotSearch == 1) {
            for (HotSearch val : commonHotSearchRankings) {
                if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                    val.heat += val.heatBase * votes;
                    System.out.println("投票成功！");
                    Collections.sort(commonHotSearchRankings);
                    Person.sortHotSearchRankings();
                    break;
                }
            }
        } else {
            for (HotSearch val : purchaseHotSearchRankings) {
                if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                    val.heat += val.heatBase * votes;
                    System.out.println("投票成功！");
                    Collections.sort(purchaseHotSearchRankings);
                    Person.sortHotSearchRankings();
                    break;
                }
            }
        }

        this.votes -= votes;
    }

    public void purchase(String hotSearchName, Integer purchaseRank, Integer purchasePrice) {
        int findHotSearch = this.findHotSearch(hotSearchName);
        if (findHotSearch == 0) {
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
        if (!purchaseHotSearchRankings.isEmpty()) {
            for (HotSearch purchaseHotSearch : purchaseHotSearchRankings) {
                if (purchaseHotSearch.purchaseRank == purchaseRank && purchaseHotSearch.purchasePrice >= purchasePrice) {
                    System.out.println("金额不够，无法购买当前排名热搜！");
                    return;
                } else if (purchaseHotSearch.purchaseRank == purchaseRank) {
                    purchaseHotSearchRankings.remove(purchaseHotSearch);
                    break;
                }
            }
        }
        if (findHotSearch == 1) {
            for (HotSearch val : commonHotSearchRankings) {
                if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                    if (purchaseRank > purchaseHotSearchRankings.size() + commonHotSearchRankings.size()){
                        val.purchaseRank = purchaseHotSearchRankings.size() + commonHotSearchRankings.size();
                    }else
                        val.purchaseRank = purchaseRank;
                    val.purchasePrice += purchasePrice;
                    purchaseHotSearchRankings.add(val);
                    commonHotSearchRankings.remove(val);
                    System.out.println("购买成功！");
                    Collections.sort(commonHotSearchRankings);
                    Collections.sort(purchaseHotSearchRankings);
                    sortHotSearchRankings();
                    break;
                }
            }
        } else if (findHotSearch == 2) {
            for (HotSearch val : purchaseHotSearchRankings) {
                if (val.hotSearchName.equalsIgnoreCase(hotSearchName)) {
                    if (purchaseRank > purchaseHotSearchRankings.size() + commonHotSearchRankings.size()){
                        val.purchaseRank = purchaseHotSearchRankings.size() + commonHotSearchRankings.size();
                    }else
                        val.purchaseRank = purchaseRank;
                    val.purchasePrice += purchasePrice;
                    System.out.println("购买成功！");
                    Collections.sort(purchaseHotSearchRankings);
                    sortHotSearchRankings();
                    break;
                }
            }

        }

    }
}
