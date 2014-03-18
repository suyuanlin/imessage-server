package com.imessage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedController {

	/**
	 * 获取所有feed
	 * @param request
	 * @return
	 */
	@RequestMapping("feed/getFeed")
	public ModelAndView getFeed(HttpServletRequest request) {
		return new ModelAndView();
	}
}
