package com.tst.casper.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 管理Casperjs的启动和执行
 * Created by ucs_yuananyun on 2016/6/23.
 */
public class CasperjsProgramManager {
    public static String launch(String jsFileName, List<String> params) {
        return launch(jsFileName, null, params);
    }
    public static String launch(String jsFileName, String pageCharset,List<String> params) {
        if (StringUtils.isBlank(jsFileName)) {
        	System.out.println("待执行的js文件名不能为空！");
            return null;
        }
        BufferedReader br = null;
        try {
            if(pageCharset==null) pageCharset = "utf-8";
            String path = CasperjsProgramManager.class.getResource("/").getPath();
            path = path.substring(1, path.lastIndexOf("/") + 1);
            String os = System.getProperties().getProperty("os.name");
            String casperJsPath = "";
            String phantomJsPath = "";
            if (StringUtils.startsWithIgnoreCase(os, "win")) {
                casperJsPath = path + "casperjs/bin/casperjs.exe";
                phantomJsPath = path + "phantomjs/window/phantomjs.exe";
            } else {
                path = "/" + path;
                casperJsPath = path + "casperjs/bin/casperjs";
                phantomJsPath = path + "phantomjs/linux/phantomjs";
            }
            System.out.println("CasperJs程序地址:{"+casperJsPath+"}");
            ProcessBuilder processBuilder = new ProcessBuilder(casperJsPath, jsFileName);//casperjsDownload.js
            if (params != null) {
                for (String param : params) {
                    processBuilder.command().add(param);
                }
            }
            processBuilder.command().add("--output-encoding="+pageCharset);
            processBuilder.command().add("--web-security=no");
            processBuilder.command().add("--ignore-ssl-errors=true");

            processBuilder.directory(new File(path + "casperjs/js"));
            processBuilder.environment().put("PHANTOMJS_EXECUTABLE", phantomJsPath);

            Process p = processBuilder.start();
            InputStream is = p.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, pageCharset));
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                sbf.append(tmp).append("\r\n");
            }
            br.close();
            p.destroy();
            return sbf.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            if(br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
