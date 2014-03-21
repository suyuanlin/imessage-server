package com.imessage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imessage.model.AuthContext;
import com.imessage.model.AuthContextHolder;
import com.imessage.model.AuthContextImpl;
import com.imessage.model.MessageInfo;
import com.imessage.model.Pagination;
import com.imessage.model.User;
import com.imessage.service.feed.FeedService;
import com.imessage.util.lang.JSONResponse;
import com.imessage.util.lang.JSONView;

@Controller
public class FeedController {

	@Autowired
	private FeedService feedService;

	@RequestMapping("messages")
	public ModelAndView getMessages(HttpServletRequest request, Pagination<?> pagination) {
		setUser(request);
		Pagination<MessageInfo> feedMessage = feedService.getFeedMessage(pagination.getPageNo(),
				pagination.getPageSize());
		JSONResponse jsonResponse = new JSONResponse(feedMessage);
		ModelAndView modelAndView = new ModelAndView(new JSONView(jsonResponse));
		return modelAndView;
	}

	/**
	 * 获取所有feed
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("feed/getFeed")
	public ModelAndView getFeed(HttpServletRequest request) {
		return new ModelAndView();
	}

	// 临时获取用户信息
	private void setUser(HttpServletRequest request) {
		User user = new User();
		user.setUserId(request.getLocalAddr());
		user.setUserName(request.getLocalName());
		AuthContext context = new AuthContextImpl(user, null, null);
		AuthContextHolder.setAuthContext(context);
	}
}
