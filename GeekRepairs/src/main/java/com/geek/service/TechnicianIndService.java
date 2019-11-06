package com.geek.service;

import com.geek.model.TechnicianInd;

public interface TechnicianIndService extends CrudService<TechnicianInd, Long> {
	boolean TechnicianIndValid(TechnicianInd technicianInd);
}
