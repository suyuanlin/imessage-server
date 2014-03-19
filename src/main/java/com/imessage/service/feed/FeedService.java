package com.imessage.service.feed;

import com.imessage.model.IFeedProvider;
import com.imessage.model.MessageInfo;
import com.imessage.model.PK;
import com.imessage.model.Pagination;

public interface FeedService extends FeedCommentService {
	/**
	 * 发表新鲜事情
	 * @param feedProvider
	 * @param feedLink
	 * @param pk
	 * @return
	 */
	String publishFeed(IFeedProvider feedProvider, String feedLink, PK pk);
	
	Pagination<MessageInfo> getFeedMessage(int pageIndex, int pageSize);
}
