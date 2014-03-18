package com.imessage.service.feed;

import com.imessage.model.PK;
import com.imessage.model.feed.IFeedProvider;

public interface FeedService extends FeedCommentService {
	/**
	 * 发表新鲜事情
	 * @param feedProvider
	 * @param feedLink
	 * @param pk
	 * @return
	 */
	String publishFeed(IFeedProvider feedProvider, String feedLink, PK pk);
}
