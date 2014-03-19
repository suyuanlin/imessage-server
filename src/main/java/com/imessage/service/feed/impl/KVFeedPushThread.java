package com.imessage.service.feed.impl;

import java.util.Date;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.imessage.model.IFeedProvider;
import com.imessage.model.PK;
import com.imessage.model.User;
import com.imessage.model.feed.FeedEvent;

public class KVFeedPushThread extends Thread {
	private static final Log log = LogFactory.getLog(KVFeedPushThread.class);

	private static final String NS_FEED = "sirius:feed:";

	private final RedisTemplate<String, String[]> redisTemplate;
	private final User user;
	private final Set<String> followings;
	private final IFeedProvider feedProvider;
	private final String feedLink;
	private final PK feedFormatData;

	KVFeedPushThread(RedisTemplate<String, String[]> redisTemplate, User user, Set<String> followings,
			IFeedProvider feedProvider, String feedLink, PK feedFormatData) {
		super();
		this.redisTemplate = redisTemplate;
		this.user = user;
		this.feedProvider = feedProvider;
		this.feedLink = feedLink;
		this.feedFormatData = feedFormatData;
		this.followings = followings;
	}

	@Override
	public void run() {
		if (log.isInfoEnabled()) {
			log.info(String.format("推送feed, 用户名%s, 关注他的人%d", user.getUserName(), followings.size()));
		}
		for (String following : followings) {
			publishTo(following);
		}
	}

	/**
	 * 推送给自己一下, 用于首页展示
	 */
	public String publishToSelf() {
		return publishTo(user.getUserId());
	}

	/**
	 * 
	 * 
	 * @param otherId
	 * @param isSelf
	 * @return
	 * @author Tkk
	 */
	public String publishTo(String otherId) {
		FeedEvent feedEvent = new FeedEvent();
		String sid = feedFormatData.getPK().toString();

		//
		Date date = new Date();
		feedEvent.setSid(sid); // very important, each service must provider
								// different ID
		feedEvent.setCreateDate(date);
		feedEvent.setServiceData(feedFormatData);
		feedEvent.setUserid(user.getUserId());
		feedEvent.setUsername(user.getUserName());
		feedEvent.setServiceLink(feedLink);

		//
		BoundZSetOperations<String, String[]> boundZSetOps = redisTemplate.boundZSetOps(NS_FEED + otherId);
		boundZSetOps.add(
				new String[] { feedProvider.getServiceName(),
						JSON.toJSONString(feedEvent, SerializerFeature.UseSingleQuotes), feedEvent.getSid() },
				-date.getTime());
		return sid;
	}
}
