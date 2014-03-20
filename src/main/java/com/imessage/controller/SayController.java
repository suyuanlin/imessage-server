package com.imessage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.imessage.model.MessageInfo;
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
	public ModelAndView shareSay(HttpServletRequest request, String messageContext,String title) {
		MessageInfo say = new MessageInfo();
		say.setMessageContext(messageContext);
		say.setCreateUserId(request.getLocalAddr()+request.getLocalName());
		say.setTitle(title);
		feedService.publishFeed(messageInfoService, StringUtils.EMPTY, say);
		return new ModelAndView();
	}
}
