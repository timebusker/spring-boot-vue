package com.timebusker.common.redis.key;

public interface KeyPrefix {
		
	public int getExpire();
	
	public String getPrefix();
	
}
