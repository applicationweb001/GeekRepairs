package com.geek.service;

import com.geek.model.Request;

public interface RequestService extends CrudService<Request, Long>{

	boolean RequestValid(Request request);

}
