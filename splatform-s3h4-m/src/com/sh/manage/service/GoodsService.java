package com.sh.manage.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.manage.dao.GoodsDao;
import com.sh.manage.entity.Goods;
import com.sh.manage.entity.Vipcard;
import com.sh.manage.module.page.Page;

@Service
public class GoodsService extends BaseService{
	@Autowired
	private GoodsDao goodsDao;
	
	public Page findAllGoods(String name, String type,int pageNo, int pageSize) {
		return goodsDao.getAllCard( name, type, pageNo, pageSize);
	}
	
	public Goods getById(Integer id){
		return goodsDao.get(Goods.class, id);
	}
	
	public void delGoods(Goods goods){
		goodsDao.delete(goods);
	}
	
	public void addGoods(Goods goods){
		goodsDao.save(goods);
	}
	
	public void updateGoods(Goods goods){
		Goods oldGoods = goodsDao.getObject(goods);
		if(oldGoods != null){
			oldGoods.setName(goods.getName());
			oldGoods.setType(goods.getType());
			oldGoods.setPrice(goods.getPrice());
			oldGoods.setDescription(goods.getDescription());
			goodsDao.update(oldGoods);
		}
	}
	
	public List<Goods> findAll(String name){
		return goodsDao.findAll(name);
	}
	
	
}
