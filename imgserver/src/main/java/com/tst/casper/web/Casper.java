package com.tst.casper.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class Casper
 */
public class Casper extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(Casper.class);
	private String tmpFolder = System.getProperty("user.dir") + "/tmp/";
	private static  final boolean dele = false;
	private String imageSubfix = "jpg";
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void init() throws ServletException {
        File file = new File(tmpFolder);
        if (!file.exists() && !file.isDirectory())
            file.mkdirs();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String params = request.getParameter("params");
        String fullbgImagePath = null;
        String bgImagePath = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fullbgImagePath = tmpFolder + uuid + "_fullbg." + imageSubfix;
        bgImagePath = tmpFolder + uuid + "_bg." + imageSubfix;
        try {
	        CasperBean paramMap = toBean(params);
	        List<String> fullbgSrcList = paramMap.getFullbgSrcArray();
	        List<String> fullbgPositionList = paramMap.getFullbgPositionArray();
	        List<String> bgSrcList = paramMap.getBgSrcArray();
	        List<String> bgPositionList = paramMap.getBgPositionArray();
	        int itemWidth = paramMap.getItemWidth();
	        int itemHeight = paramMap.getItemHeight();
	        int lineItemCount = paramMap.getLineItemCount();
        	checkObj(fullbgSrcList, fullbgPositionList, bgSrcList, bgPositionList, itemWidth, itemHeight, lineItemCount);
            List<String[]> fullbgPointList = toConvertorArray(fullbgPositionList);
            List<String[]> bgPointList = toConvertorArray(bgPositionList);
            ImageUtils.combineImages(fullbgSrcList, fullbgPointList, lineItemCount, itemWidth, itemHeight, fullbgImagePath, imageSubfix);
          	ImageUtils.combineImages(bgSrcList, bgPointList, lineItemCount, itemWidth, itemHeight, bgImagePath, imageSubfix);
            int deltaX = ImageUtils.findXDiffRectangeOfTwoImage(fullbgImagePath, bgImagePath);
            //deltaX-=7;
            resultMap.put("status", 1);
            resultMap.put("position", deltaX);
            resultMap.put("error", "0");
        } catch (Exception e) {
        	resultMap.put("status", 0);
        	resultMap.put("position", -1);
        	resultMap.put("error", e.getMessage());
        }finally {
        	response.getWriter().write(JSON.toJSONString(resultMap));
        	logger.info(uuid+"_参数："+params);
            logger.info(uuid+"_结果："+JSON.toJSONString(resultMap));
        	//删除缓存的图片
        	if(dele){
        		deleteImage(fullbgImagePath);
        		deleteImage(bgImagePath);
        	}
		}
	}
	private List<String[]> toConvertorArray(List<String> bgPositionList) {
		List<String[]> bgPointList = new ArrayList<String[]>();
		for (String positionStr : bgPositionList) 
		    bgPointList.add(positionStr.replace("px", "").split(" "));
		return bgPointList;
	}

	private CasperBean toBean(String params) throws IllegalArgumentException{
		if (StringUtils.isBlank(params))
        	throw new IllegalArgumentException("params参数不能为空");
		CasperBean bean = JSON.parseObject(params, CasperBean.class);
		if (bean == null)
        	throw new RuntimeException("params.all参数不能为空");
		return bean;
	}

	private void checkObj(List<String> fullbgSrcList, List<String> fullbgPositionList, List<String> bgSrcList, List<String> bgPositionList, int itemWidth, int itemHeight, int lineItemCount)throws IllegalArgumentException{
		if(fullbgSrcList==null||fullbgSrcList.size()!=52) 
			throw new IllegalArgumentException("params.fullbgSrcList参数不能为空");
		if(fullbgPositionList==null) 
			throw new IllegalArgumentException("params.fullbgPositionList参数不能为空");
		if(bgSrcList==null||bgSrcList.size()!=52) 
			throw new IllegalArgumentException("params.bgSrcList参数不能为空");
		if(bgPositionList==null) 
			throw new IllegalArgumentException("params.bgPositionList参数不能为空");
		if(itemWidth<=0||lineItemCount<=0||itemHeight<=0) 
			throw new IllegalArgumentException("params.itemWidth||params.lineItemCount||params.itemHeight参数不能为空");
	}

	public void deleteImage(String fullbgImagePath) {
		if(StringUtils.isBlank(fullbgImagePath))
			return;
		File file = new File(fullbgImagePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			 file.delete();
		}
	}
}
