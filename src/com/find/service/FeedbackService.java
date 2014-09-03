package com.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.dao.FeedbackDao;
import com.find.model.Feedback;

@Service("feedbackService")
public class FeedbackService {
	/**
	 * 添加一条意见反馈
	 */
	@Autowired
	FeedbackDao feedbackDao;

	public void add(Feedback feedback) {
		feedbackDao.add(feedback);
	}
}
