package com.atguigu.demo.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.demo.common.exception.UserLoginFailedException;
import com.atguigu.demo.common.exception.UserRegistFailedException;
import com.atguigu.demo.common.global.GlobalMessage;
import com.atguigu.demo.common.util.FastDFSStorageClientUtil;
import com.atguigu.demo.pojo.entities.DUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author panpala
 * @date 2017年9月6日
 */
@Controller
public class UserController {
	
	@Autowired
	private HttpClient client;
	
	@Autowired
	private RequestConfig config;
	
	
	@RequestMapping("/update")
	public String update(DUser user,@RequestParam("imgFile")MultipartFile imgFile,HttpSession session) throws IOException {
		if (!imgFile.isEmpty()) {
			byte[] filebytes = imgFile.getBytes();
			String filename = imgFile.getOriginalFilename();
			System.out.println(filename);
			FastDFSStorageClientUtil clientUtil = new FastDFSStorageClientUtil("classpath:tracker.conf");
			String[] result = clientUtil.upload(filebytes, filename);
			//将fastdfs服务器生成的组名和文件名封装进user，一并保存到数据库
			user.setPictureGroupName(result[0]);
			user.setPictureRemoteName(result[1]);
		}
		//发送webservice请求
		HttpEntity responseEntity = sendRequestToUserWebService("update", user);
		//更新session中user对象的数据
		saveUserDataToSession(responseEntity, session);
		return "user-center";
	}
	
	@RequestMapping("/regist")
	public String regist(DUser user) throws ClientProtocolException, IOException {
		HttpEntity responseEntity = sendRequestToUserWebService("regist", user);
		//用工具类将响应返回的信息取出
		String result = EntityUtils.toString(responseEntity, "UTF-8");
		
		if (GlobalMessage.USER_NAME_ALREADY_EXISTS.equals(result)) {
			throw new UserRegistFailedException("注册失败，用户名已经存在");
		}
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(DUser user,HttpSession session) throws ClientProtocolException, IOException {
		HttpEntity responseEntity = sendRequestToUserWebService("login", user);
		if(responseEntity == null) {
			throw new UserLoginFailedException("登录失败，用户名或者密码错误");
		}
		saveUserDataToSession(responseEntity, session);
		return "user-center";
	}
	
	/*
	 * 将json转换为user对象，并存入session域对象中
	 * */
	private void saveUserDataToSession(HttpEntity responseEntity,HttpSession session) throws ParseException, IOException {
		String string = EntityUtils.toString(responseEntity, "UTF-8");
		//将返回信息转换为DUser对象
		TypeToken<Map<String, DUser>> token = new TypeToken<Map<String,DUser>>(){};
		Type type = token.getType();
		Map<String, DUser> resultMap = new Gson().fromJson(string, type);
		//这里的key和@XmlRootElement（name）中的name值有关
		DUser dUser = resultMap.get(GlobalMessage.USER_INFO);
		if (session.getAttribute(GlobalMessage.USER_INFO) != null) {
			session.removeAttribute(GlobalMessage.USER_INFO);
		}
		session.setAttribute(GlobalMessage.USER_INFO, dUser);
		System.out.println(dUser);
	}
	
	/*
	 * 将利用Webservice处理数据发送请求代码封装起来
	 * @Param action 传入将要访问的webservice的方法的@path映射
	 * @Param user 传入要操作的用户信息
	 * */
	private HttpEntity sendRequestToUserWebService(String action,DUser user) throws ClientProtocolException, IOException {
		//准备httpPost对象，用于携带请求信息
				String url = "http://localhost:8083/demo-user-web-service/user-service/remote/user/" + action;//参数action
				HttpPost post = new HttpPost(url);
				post.setConfig(config);
				
				//准备请求需要带的信息
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(GlobalMessage.USER_INFO, user);//参数user
				//实体类上必须加@XmlRootElement,map中key的值默认和实体类的类名小写一致，或者自定义name值要和key一致
				//创建gson将请求信息转换为json
				Gson gson = new Gson();
				String json = gson.toJson(map);
				System.out.println(json);
				//用实体对象StringEntity封装请求信息
				StringEntity requestEntity = new StringEntity(json, "UTF-8");
				
				//将封装好的参数对象放入httpPost对象，然后设置请求头
				post.setEntity(requestEntity);
				post.setHeader("content-type", "application/json");
				
				//发送webservice请求
				HttpResponse response = client.execute(post);
				
				HttpEntity reponseEntity = response.getEntity();
		return reponseEntity;
	}
	
}
