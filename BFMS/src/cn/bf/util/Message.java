package cn.bf.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Message {
	public static String getJSONMsg(Map<String, Object> info) {
		
		StringBuilder sb = new StringBuilder("{'");
		Iterator<Entry<String, Object>> it = info.entrySet().iterator();
		
		while(it.hasNext()) {
			Entry<String, Object> entry = it.next();
			sb.append(entry.getKey());
			sb.append("' : '");
			sb.append(entry.getValue());
			sb.append("'");
			if(it.hasNext()) {
				sb.append(", '");
			} else {
				sb.append("}");
			}
		}
		
		return sb.toString();
	}
	
	public static Map<String, Object> getInitInfo() {
		return new HashMap<String, Object>();
	}
}
