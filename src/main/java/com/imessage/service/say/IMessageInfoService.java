package com.imessage.service.say;

import java.util.List;

import com.imessage.model.IFeedProvider;
import com.imessage.model.MessageInfo;

public interface IMessageInfoService extends IFeedProvider {

	List<MessageInfo> findSayByUserIds(List<String> users);

	MessageInfo findLastSay(String userId);
}
