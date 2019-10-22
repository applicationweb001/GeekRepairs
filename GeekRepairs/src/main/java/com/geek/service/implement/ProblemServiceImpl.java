package com.geek.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.model.entity.Problem;
import com.geek.model.repository.ProblemRepository;
import com.geek.service.ProblemService;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	
	@Override
	public List<Problem> getAll() {
		
		return problemRepository.findAll();
	}

	@Override
	public Problem getOneById(Long id) {
		// TODO Auto-generated method stub
		return problemRepository.findById(id).orElseThrow(()->new RuntimeException("Problem not found!"));
	}

	@Override
	public Long create(Problem entity) {
		// TODO Auto-generated method stub
		problemRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Problem entity) {
		// TODO Auto-generated method stub
		Problem current= getOneById(id);
		current.setName(entity.getName());
		current.setDescription(entity.getDescription());
		
		problemRepository.save(current);
	}

	@Override
	public void delete(Long id) {
		problemRepository.deleteById(id);
		
	}

}
