package com.lc.zy.ball.boss.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.lc.zy.ball.domain.oa.mapper.DicItemMapper;
import com.lc.zy.ball.domain.oa.mapper.DicMapper;
import com.lc.zy.ball.domain.oa.po.Dic;
import com.lc.zy.ball.domain.oa.po.DicItem;
import com.lc.zy.ball.domain.oa.po.DicItemCriteria;

@Component
@Lazy
public class DicProvider extends AbstractProvider {
	
	@Autowired
	private DicMapper dicMapper;
	
	@Autowired
	private DicItemMapper dicItemMapper;
	
	public DicProvider() {
		Providers.add(this);
	}
	
	/**
	 * key = dicCode.
	 */
	private Map<String, List<DicItem>> itemsMap = null;
	
	/**
	 * key = itemCode.
	 */
	private Map<String, DicItem> itemMap = null;
	
	/**
	 * key = dicCode.
	 */
	private Map<String, Dic> dicMap = null;
	
	/**
	 * key = dicId.
	 */
	private Map<String, String> dicCodeMap = null;
	

	/**
	 * key = dicCode.
	 */
	public Map<String, List<DicItem>> getItemsMap() {
		tryLoad();
		return itemsMap;
	}

	/**
	 * key = itemCode.
	 */
	public Map<String, DicItem> getItemMap() {
		tryLoad();
		return itemMap;
	}

	public Map<String, Dic> getDicMap() {
		tryLoad();
		return dicMap;
	}

	/**
	 * key = dicId.
	 */
	public Map<String, String> getDicCodeMap() {
		tryLoad();
		return dicCodeMap;
	}

	
	protected void load() {
		
		clear();
		
		List<Dic> dics = dicMapper.selectByExample(null);
		for (Dic dic : dics) {
			dicMap.put(dic.getDicCode(), dic);
			dicCodeMap.put(dic.getDicId(), dic.getDicCode());
		}
		
		DicItemCriteria dc = new DicItemCriteria();
		dc.setOrderByClause("seq_num asc");
		List<DicItem> items = dicItemMapper.selectByExample(dc);
		for (DicItem item : items) {
			addItem(item);
		}
	}

	public void clear() {
		dicMap = new HashMap<>();
		dicCodeMap = new HashMap<>();
		itemMap = new HashMap<>();
		itemsMap = new HashMap<>();
	}

	private void addItem(DicItem item) {
		getDicItems(item.getDicId()).add(item);
		itemMap.put(item.getItemCode(), item);
	}

	private List<DicItem> getDicItems(String dicId) {
		String dicCode = dicCodeMap.get(dicId);
		List<DicItem> items = itemsMap.get(dicCode);
		if (items == null) {
			items = new ArrayList<DicItem>();
			itemsMap.put(dicCode, items);
		}
		return items;
	}
	
}
