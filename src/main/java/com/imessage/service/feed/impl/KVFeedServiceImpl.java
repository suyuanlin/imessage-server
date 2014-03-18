package com.imessage.service.feed.impl;

import com.imessage.model.PK;
import com.imessage.model.Pagination;
import com.imessage.model.ReplyInfo;
import com.imessage.model.feed.IFeedProvider;
import com.imessage.service.feed.FeedService;

public class KVFeedServiceImpl implements FeedService{

	public String addComment(String serivceName, String sid, ReplyInfo comment) {
		// TODO Auto-generated method stub
		return null;
	}

	public long commentCount(String serviceName, String sid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Pagination getFeedComment(String serviceName, String sid,
			int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public String publishFeed(IFeedProvider feedProvider, String feedLink, PK pk) {
		// TODO Auto-generated method stub
		return null;
	}

}
