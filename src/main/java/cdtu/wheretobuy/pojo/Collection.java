package cdtu.wheretobuy.pojo;

import java.util.Date;

public class Collection {
    private Integer id;

    private Integer userId;

    private String type;

    private Integer sellerGoodsId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSellerGoodsId() {
        return sellerGoodsId;
    }

    public void setSellerGoodsId(Integer sellerGoodsId) {
        this.sellerGoodsId = sellerGoodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}