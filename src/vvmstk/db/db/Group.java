package vvmstk.db.db;


import vvmstk.config.CustomDate;

import java.util.Date;

public class Group  {
    private String groupNum;
    private String kateg;
    private Date dateBegin;
    private Date dateEnd;
    private Integer price;

    public Group(String groupNum, String kateg, Date dateBegin, Date dateEnd, Integer price) {
        this.groupNum = groupNum;
        this.kateg = kateg;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.price = price;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getKateg() {
        return kateg;
    }

    public void setKateg(String kateg) {
        this.kateg = kateg;
    }

    public Date getDateBegin() {
        return new CustomDate(dateBegin.getTime());
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return new CustomDate(dateEnd.getTime());
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
