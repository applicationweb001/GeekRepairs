package com.geek.service;


import com.geek.model.Problem;


public interface ProblemService extends CrudService<Problem,Long> {

	boolean ProblemValid(Problem prob);
	
}
