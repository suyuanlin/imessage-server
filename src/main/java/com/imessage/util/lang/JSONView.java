package com.imessage.util.lang;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;

/**
 * 简单话
 * 
 * 2013-12-26 下午4:05:21
 * 
 * @author Tkk
 */
public class JSONView extends FastJsonJsonView {

	private JSONResponse jsonResponse;

	public JSONView() {
		this(new JSONResponse());
	}

	public JSONView(JSONResponse jsonResponse) {
		super();
		this.jsonResponse = jsonResponse;
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	protected Object filterModel(Map<String, Object> model) {
		return jsonResponse;
	}

}
