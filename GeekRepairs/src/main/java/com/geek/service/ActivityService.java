package com.geek.service;

import com.geek.model.Activity;

public interface ActivityService extends CrudService<Activity, Long>{

	boolean ActivityInValid(Activity act);
	
}
