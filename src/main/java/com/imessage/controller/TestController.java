package com.imessage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imessage.model.MessageInfo;
import com.imessage.util.lang.JSONResponse;
import com.imessage.util.lang.JSONView;

@Controller
public class TestController {
	/**
	 * 
	 * @param request
	 * @return
	 */
	String message = "荼靡们说，你是那身着一袭白衣，温润如玉的少年，手撑一柄油纸伞，踏过青石板，千里迢迢的去寻找故事中的长安。我说，你是从泼墨山水画中走出来的妖孽，妖娆而鬼魅的一笑，时光在便一那瞬间停滞，耳边唯剩下一句，反反复复：原来，真的有人可以惊艳了时光";

	// 请求url地址映射，类似Struts的action-mapping
	@RequestMapping("test/login")
	public ModelAndView testLogin(HttpServletRequest request) {
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		MessageInfo m = new MessageInfo();
		m.setCreateUserId("1");
		m.setMessageId("1");
		m.setTitle("angular JS之$apply()方法");
		m.setMessageContext("这次与之前不同的是，页面上先会显示：Waiting 2000ms for update，等待2秒后内容会被更改为：Timeout called! 。显然数据的更新被angular JS觉察到了");
		list.add(m);

		m = new MessageInfo();
		m.setCreateUserId("1");
		m.setMessageId("2");
		m.setTitle("荼靡们说，你是那身着一袭白衣，温润如玉的少年");
		m.setMessageContext(message);
		list.add(m);

		JSONResponse json = new JSONResponse(list);

		ModelAndView mv = new ModelAndView(new JSONView(json));
		return mv;
	}
}
