package com.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.find.model.Feedback;
import com.find.model.JsonResult;
import com.find.model.Listener;
import com.find.service.FeedbackService;

@Controller
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;

	@RequestMapping(value = "feedback", method = RequestMethod.POST)
	public @ResponseBody Object addFeedback(Feedback feedback) {
		JsonResult result = new JsonResult();
		feedback.setDatetime(System.currentTimeMillis());
		feedbackService.add(feedback);
		if (feedback.getId() > 0) {
			result.setMsg("添加成功！");
			result.setStatus(200);
			result.setValue(null);
		} else {
			result.setMsg("添加失败！");
			result.setValue(null);
			result.setStatus(500);
		}
		return result;
	}

}
