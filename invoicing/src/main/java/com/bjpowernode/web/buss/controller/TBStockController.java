package com.bjpowernode.web.buss.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bjpowernode.core.common.controller.BaseController;
import com.bjpowernode.core.common.exception.BusinessException;
import com.bjpowernode.core.common.hibernate.qbc.CriteriaQuery;
import com.bjpowernode.core.common.model.json.DataGrid;
import com.bjpowernode.tag.core.easyui.TagUtil;
import com.bjpowernode.web.buss.entity.base.TBStockEntity;
import com.bjpowernode.web.buss.service.TBStockServiceI;
import com.bjpowernode.web.system.service.SystemService;


/**   
 * @Title: Controller
 * @Description: 库存表
 * @author onlineGenerator
 * @date 2014-03-25 10:46:32
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tBStockController")
public class TBStockController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TBStockController.class);

	@Autowired
	private TBStockServiceI tBStockService;
	@Autowired
	private SystemService systemService;


	/**
	 * 库存表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tBStock")
	public ModelAndView tBStock(HttpServletRequest request) {
		return new ModelAndView("buss/stock/tBStockList");
	}

	/**
	 * 选择货品
	 * 
	 * @return
	 */
	@RequestMapping(params = "tBStockSelect")
	public String goods() {
		return "buss/stock/tBStockSelect";
	}
	
	/**
	 * 库存货品显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridStock")
	public void datagridGoods(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBStockEntity.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TBStockEntity tBStock,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TBStockEntity.class, dataGrid);
		//查询条件组装器
		com.bjpowernode.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tBStock, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tBStockService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	

}
