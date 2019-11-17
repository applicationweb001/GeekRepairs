package com.geek.service;



import com.geek.model.Adviser;


public interface AdviserService  extends CrudService<Adviser,Long>{

	boolean AdviserIndValid(Adviser tec);
}
