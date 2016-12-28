package com.tst.casper.client;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class App {
	public static void main(String[] args) throws UnsupportedEncodingException {
		long b = System.currentTimeMillis();
		/**
		 * 这段代码开始调用casperJS脚本
		 */
		List<String> paramList = new ArrayList<>();
		//paramList.add("https://user.geetest.com/login?url=http:%2F%2Faccount.geetest.com%2Freport");//url
		//paramList.add("http://bj.gsxt.gov.cn/sydq/loginSydqAction!sydq.dhtml");//url
		paramList.add("https://user.geetest.com/login?url=http:%2F%2Faccount.geetest.com%2Freport");//url
		paramList.add("http://192.168.99.52:8080/casper/Casper");
		//paramList.add("http://localhost:8080/casper/Casper");
		/*paramList.add(URLEncoder.encode("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:50.0) Gecko/20100101 Firefox/50.0", "utf-8"));//userAgent
		paramList.add("user.geetest.com");//domain
		paramList.add(String.valueOf(true));//isUserGzip
		paramList.add(String.valueOf(3));//retryTimes
		paramList.add("");//cookie
*/		String result = Casperjs.launch("geetest_refresh.js", "utf-8", paramList);
		System.out.println(result);
		long e = System.currentTimeMillis();
		System.out.println("time:"+(e-b));
	}
}
