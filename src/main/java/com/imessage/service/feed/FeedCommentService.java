package com.imessage.service.feed;

import com.imessage.model.Pagination;
import com.imessage.model.ReplyInfo;

public interface FeedCommentService {
	/**
	 * 回复feed
	 * 
	 * @param feedId
	 * @param comment
	 * @return
	 * @author Tkk
	 */
	String addComment(String serivceName, String sid, ReplyInfo comment);

	/**
	 * 回复数
	 * @param serivceName
	 * @param sid
	 * @return
	 */
	long commentCount(String serviceName, String sid);
	
	/**
	 * 获取消息评论
	 * @param serivceName	业务名称
	 * @param sid			业务ID
	 * @param pageIndex		
	 * @param pageSize
	 * @return
	 */
	Pagination getFeedComment(String serviceName, String sid, int pageIndex, int pageSize);
}
