package com.imessage.service.feed.impl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.imessage.model.AuthContextHolder;
import com.imessage.model.IFeedProvider;
import com.imessage.model.MessageInfo;
import com.imessage.model.PK;
import com.imessage.model.Pagination;
import com.imessage.model.ReplyInfo;
import com.imessage.model.User;
import com.imessage.service.feed.Constants;
import com.imessage.service.feed.FeedService;

@Service
public class KVFeedServiceImpl implements FeedService {

	private static final Log log = LogFactory.getLog(KVFeedServiceImpl.class);

	private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime()
			.availableProcessors());

	/**
	 * [ [serivceName, {uid: '事件用户', un: '事件用户名称', s: '业务名称', d: '业务数据', l:
	 * '链接', c: '创建时间'}, sid] ]
	 */
	@Autowired
	private RedisTemplate<String, String[]> feedMessageTemplate;

	/**
	 */
	@Autowired
	private RedisTemplate<String, String[]> feedLikeTemplate;

	/**
	 * 用于判断用户是否喜欢过了
	 */
	@Autowired
	private RedisTemplate<String, String> feedIsLikeTemplate;

	public String addComment(String serivceName, String sid, ReplyInfo comment) {
		return null;
	}

	public long commentCount(String serviceName, String sid) {
		return 0;
	}

	public Pagination<?> getFeedComment(String serviceName, String sid, int pageIndex, int pageSize) {
		return null;
	}

	public String publishFeed(IFeedProvider feedProvider, String feedLink, PK pk) {
		Assert.notNull(pk.getPK(), "推送消息的时候, 数据的业务id不能为空!!");

		if (log.isDebugEnabled()) {
			log.debug(String.format("业务%s推送消息数据%s模板%s", feedProvider.getServiceName(), pk, feedProvider.getTemplate()));
		}

		User user = AuthContextHolder.getAuthContext().getUser();
		
		Set<String> set = new HashSet<String>();
		KVFeedPushThread thread = new KVFeedPushThread(feedMessageTemplate, user, set, feedProvider, feedLink, pk);
		THREAD_POOL.execute(thread);
		String feedId = thread.publishToSelf();

		// feedTemplateService.setServiceTemplate(feedProvider.getServiceName(),
		// feedProvider.getTemplate());
		return feedId;
	}

	public Pagination<MessageInfo> getFeedMessage(int pageIndex, int pageSize) {
		Pagination<MessageInfo> pagination = new Pagination<MessageInfo>();
		pagination.setPageSize(pageSize);
		pagination.setPageNo(pageIndex);

		long end = pageIndex * pageSize;
		long start = end - pageSize;

		User user = AuthContextHolder.getAuthContext().getUser();
		
		String key = Constants.NS_FEED + user.getUserId();
		BoundZSetOperations<String, String[]> boundZSetOps = feedMessageTemplate.boundZSetOps(key);

		Set<String[]> range = boundZSetOps.range(start, end);
		List<MessageInfo> feedMessages = new LinkedList<MessageInfo>();

		for (String[] strs : range) {
			String serviceName = strs[0];
			String serviceData = strs[1];
			
			//old data has only two item
			String sid = null;
			if(strs.length > 2){
				sid = strs[2];
			}
			else{
				sid = (String) ((Map)JSON.parse(serviceData)).get("sid");
			}
			
			//
			MessageInfo feedMessage = new MessageInfo();
//			MessageInfo feedMessage =getFeedMessage(serviceName, sid);
			feedMessage.setFormatDatas(serviceData);
			feedMessages.add(feedMessage);
		}
		
		pagination.setTotalCount(boundZSetOps.size());
		pagination.setResults(feedMessages);
		return pagination;
	}
}
