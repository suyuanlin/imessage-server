package com.imessage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imessage.model.AuthContext;
import com.imessage.model.AuthContextHolder;
import com.imessage.model.AuthContextImpl;
import com.imessage.model.MessageInfo;
import com.imessage.model.User;
import com.imessage.service.feed.FeedService;
import com.imessage.service.say.IMessageInfoService;

@Controller
public class SayController {
	@Autowired
	private FeedService feedService;

	@Autowired
	private IMessageInfoService messageInfoService;

	/**
	 * 发表日志
	 * @param request
	 * @param messageContext
	 * @return
	 */
	@RequestMapping("manage/shareSay")
	public String shareSay(HttpServletRequest request, String messageContext,String title) {
		setUser(request);
		MessageInfo say = new MessageInfo();
		say.setMessageContext(messageContext);
		say.setCreateUserId(AuthContextHolder.getAuthContext().getUser().getUserId());
		say.setTitle(title);
		feedService.publishFeed(messageInfoService, StringUtils.EMPTY, say);
		return "index";
	}
	
	@RequestMapping("say")
	public String say(){
		return "say";
	}
	//临时获取用户信息
	private void setUser(HttpServletRequest request){
		User user = new User();
		user.setUserId(request.getLocalAddr());
		user.setUserName(request.getLocalName());
		AuthContext context = new AuthContextImpl(user, null, null);
		AuthContextHolder.setAuthContext(context);
	}
}
