package com.geek.service;

import com.geek.model.Specialty;

public interface SpecialtyService extends CrudService<Specialty, Long>{

	boolean SpecialtyValid(Specialty specialty);
	
}
