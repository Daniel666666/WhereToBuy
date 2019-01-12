package cdtu.wheretobuy.service.impl;


import cdtu.wheretobuy.mapper.GoodsMapper;
import cdtu.wheretobuy.pojo.Goods;
import cdtu.wheretobuy.pojo.GoodsExample;
import cdtu.wheretobuy.pojo.PageResult;
import cdtu.wheretobuy.pojo.Seller;
import cdtu.wheretobuy.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Goods goods) {
        goods.setCreateTime(new Date());
        goods.setStatus("0");
        goodsMapper.insert(goods);
    }


    /**
     * 修改
     */
    @Override
    public void update(Goods goods) {
        goods.setUpdateTime(new Date());
        goods.setStatus("0");
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Goods findOne(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            goodsMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(Goods goods, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();

        if (goods != null) {
            if (goods.getName() != null && goods.getName().length() > 0) {
                criteria.andNameLike("%" + goods.getName() + "%");
            }
            if (goods.getIntroduction() != null && goods.getIntroduction().length() > 0) {
                criteria.andIntroductionLike("%" + goods.getIntroduction() + "%");
            }
            if (goods.getImages() != null && goods.getImages().length() > 0) {
                criteria.andImagesLike("%" + goods.getImages() + "%");
            }
            if (goods.getStatus() != null && goods.getStatus().length() > 0) {
                criteria.andStatusLike("%" + goods.getStatus() + "%");
            }

        }

        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void updateGoodsStatus(Integer[] goodsIds, String status) {
        if (goodsIds == null || goodsIds.length == 0) {
            return;
        }
        for (Integer id : goodsIds) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setStatus(status);
            goodsMapper.updateByPrimaryKey(goods);
        }
    }

    @Override
    public List<Goods> newGoods() {
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo("3");
        example.setOrderByClause("create_time DESC");

        List<Goods> goodsList = goodsMapper.selectByExample(example);
        return goodsList;
    }

    @Override
    public PageResult userSearchByPage(String keywords, int pageNum, int pageSize, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);

        if (orderBy != null && orderBy != "") {
            if (orderBy.equals("comment")) {
                Page<Goods> page = (Page<Goods>) goodsMapper.selectByComment(keywords);
                return new PageResult(page.getTotal(), page.getResult());
            } else if (orderBy.equals("score")) {
                Page<Goods> page = (Page<Goods>) goodsMapper.selectByScore(keywords);
                return new PageResult(page.getTotal(), page.getResult());
            }
        }
        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + keywords + "%");
        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());

    }

}
