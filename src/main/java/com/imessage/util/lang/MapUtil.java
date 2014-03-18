package com.imessage.util.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapUtil {

	/**
	 * 
	 * @param params
	 * @return
	 */
	public static Map toMap(Object... params) {
		Map map = new HashMap();
		if (params.length % 2 == 0) {
			for (int i = 0; i < params.length; i = i + 2) {
				map.put(params[i], params[i + 1]);
			}
		}
		return map;
	}
}
