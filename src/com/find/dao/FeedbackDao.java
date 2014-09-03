package com.find.dao;

import org.springframework.stereotype.Repository;

import com.find.model.Feedback;

@Repository("feedbackDao")
public class FeedbackDao extends BaseDao {
	/**
	 * 添加意见反馈
	 */
	public void add(Feedback feedback) {
		feedback.setId(super.saveAndReturnKey(feedback).intValue());
	}
}
