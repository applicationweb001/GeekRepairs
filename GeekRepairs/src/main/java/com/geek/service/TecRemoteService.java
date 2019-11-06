package com.geek.service;



import com.geek.model.TecRemote;


public interface TecRemoteService  extends CrudService<TecRemote,Long>{

	boolean TechnicianIndValid(TecRemote tec);
}
