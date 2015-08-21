package com.mybatis.demo.base.web;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.mybatis.demo.base.service.BaseService;



/**
 * 
 * 类BaseController.java的实现描述：基础控制层
 * @author yuezhihua 2014年12月24日 下午5:55:12
 */
public class BaseController {
    
    private static final Logger logger = Logger.getLogger(BaseController.class);
    
    private static final int MAX_RESULT=10;
    
    
    /**
     * 封装分页操作所需元素，对应pager.jsp
     * @param request
     * @param model
     * @param list
     */
    public List pageController(String page,Model model,BaseService baseService){
    	int pagenum = StringUtils.isEmpty(page) ? 1 :Integer.parseInt(page);
    	model.addAttribute("page", pagenum);
		int firstIndex = (pagenum - 1) * MAX_RESULT;

		QueryResult<Object> result = null;
    	result = baseService.getScrollData(firstIndex,MAX_RESULT);
    	
    	long pcount = result.getTotalrecord();
		model.addAttribute("pagecount", pcount);
		return result.getResultlist();
    }
    
    @SuppressWarnings("rawtypes")
    public List pageController(String page,Model model,BaseService baseService,Integer maxResult){
        int pagenum = StringUtils.isEmpty(page) ? 1 :Integer.parseInt(page);
        model.addAttribute("page", pagenum);
        int firstIndex = (pagenum - 1) * maxResult;

        QueryResult<Object> result = null;
        result = baseService.getScrollData(firstIndex,maxResult);
        
        long pcount = result.getTotalrecord();
        model.addAttribute("pagecount", pcount);
        return result.getResultlist();
    }
    
    public List pageController(String page,Model model,BaseService baseService,LinkedHashMap<String, String> orderby){
    	
    	int pagenum = StringUtils.isEmpty(page) ? 1 :Integer.parseInt(page);
    	model.addAttribute("page", pagenum);
		int firstIndex = (pagenum - 1) * MAX_RESULT;

    	QueryResult<Object> result = baseService.getScrollData(firstIndex,MAX_RESULT,orderby);
    	
    	long pcount = result.getTotalrecord();
		model.addAttribute("pagecount", pcount);
		return result.getResultlist();
    }
    
    public List pageController(String page,Model model,BaseService baseService,String countSql , String querySql){
        
        int pagenum = StringUtils.isEmpty(page) ? 1 :Integer.parseInt(page);
        model.addAttribute("page", pagenum);
        int firstIndex = (pagenum - 1) * MAX_RESULT;
        querySql = querySql + " limit "+firstIndex+","+MAX_RESULT;
        QueryResult<Object> result = baseService.executeQuery(countSql, querySql);
        
        long pcount = result.getTotalrecord();
        model.addAttribute("pagecount", pcount);
        return result.getResultlist();
    }
    
    
    
    public List pageController(String page,Model model,BaseService baseService,String wheresql,LinkedHashMap<String, String> orderby){
    	
    	int pagenum = StringUtils.isEmpty(page) ? 1 :Integer.parseInt(page);
    	model.addAttribute("page", pagenum);
		int firstIndex = (pagenum - 1) * MAX_RESULT;

    	QueryResult<Object> result = baseService.getScrollData(firstIndex,MAX_RESULT,wheresql,orderby);
    	
    	long pcount = result.getTotalrecord();
		model.addAttribute("pagecount", pcount);
		return result.getResultlist();
    }
}
