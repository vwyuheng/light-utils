package org.light4j.utils.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.light4j.utils.task.TaskEntity;
import org.light4j.utils.task.TaskUtils;

public class TaskTest {

	/**
	 * 测试多任务队列
	 * @param args
	 */
	public static void main(String[] args){
		/**设置任务列表**/
		List<TaskEntity> taskList = new ArrayList<TaskEntity>();
		TaskEntity task = null;
		Map<String, String> taskParam = null;
		for(int i=0;i<5;i++){
			task = new TaskEntity();
			task.setTaskClass(SendSMS.class);
			task.setTaskMethod("send");
			taskParam = new HashMap<String,String>();
			taskParam.put("phone", "15201392949" + i);
			taskParam.put("content", "测试内容" + i);
			task.setTaskParam(taskParam);
			taskList.add(task);
		}
		/**将任务添加到队列**/
		TaskUtils.addTaskList(taskList);
	}
}
