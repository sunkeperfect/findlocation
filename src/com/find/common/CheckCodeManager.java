package com.find.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CheckCodeManager {
	private static CheckCodeManager instance = null;
	public static final long PAST_TIME = 2 * 60 * 1000;
	Map<String, CheckCode> map = new HashMap<String, CheckCodeManager.CheckCode>();

	private CheckCodeManager() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						synchronized (map) {
							if (map.size() > 0) {
								Iterator<Map.Entry<String, CheckCode>> it = map
										.entrySet().iterator();
								while (it.hasNext()) {
									Map.Entry<String, CheckCode> entry = it
											.next();
									CheckCode checkCode = entry.getValue();
									// 确认过期时间
									if (checkCode.deleteTime < System
											.currentTimeMillis()) {
										it.remove();
									}
								}
							}
						}
						// 2分钟清理1次
						Thread.sleep(PAST_TIME);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static synchronized CheckCodeManager getInstance() {
		if (instance == null) {
			instance = new CheckCodeManager();
		}
		return instance;
	}

	public synchronized void addCheckCode(String mobile, String code) {
		CheckCode cc = new CheckCode(code, System.currentTimeMillis()
				+ PAST_TIME);
		synchronized (map) {
			map.put(mobile, cc);
		}
	}

	public synchronized String getCheckCode(String mobile) {
		synchronized (map) {
			return map.get(mobile).code;
		}
	}

	public class CheckCode {
		public CheckCode(String code, long deleteTime) {
			this.code = code;
			this.deleteTime = deleteTime;
		}

		public String code;
		public long deleteTime;
	}
}
