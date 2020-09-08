package com.twu.entity;

import com.twu.exception.BaseException;

public class HotSearch implements Comparable<HotSearch>{

    String hotSearchName;

    Integer heat;

    int heatBase;

    int purchaseRank;

    Integer purchasePrice;

    public HotSearch(String hotSearchName) {
        this.hotSearchName = hotSearchName;
        this.heat = 0;
        this.heatBase = 1;
        this.purchaseRank = 0;
        this.purchasePrice = 0;
    }

    @Override
    public int compareTo(HotSearch o) {
        if (this.purchaseRank == 0 && o.purchaseRank == 0) {
            return o.heat.compareTo(this.heat);
        }else if (this.purchaseRank != 0 && o.purchaseRank == 0)
            return -1;
        else if (this.purchaseRank != 0 && this.purchaseRank < o.purchaseRank)
            return -1;
        else if (this.purchaseRank == o.purchaseRank)
            throw new BaseException("出现相同的购买排名，系统故障！");
        else return 1;
    }
}
