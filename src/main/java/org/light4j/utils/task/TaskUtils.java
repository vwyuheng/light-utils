package org.light4j.utils.task;

import java.util.List;

/**
 * Java多线程、队列实现任务调度
 * 
 * @author longjiazuo
 */
public class TaskUtils {
	
	
    /**
     * 添加异步任务(任务列表)
     * @param taskList
     */
	public static void addTaskList(List<TaskEntity> taskList){
		TaskPoolManager.newInstance().addTasks(taskList);
	}
	
	 /**
     * 添加异步任务(单个任务)
     * @param taskList
     */
	public static void addTask(TaskEntity task){
		TaskPoolManager.newInstance().addTask(task);
	}
	
}
