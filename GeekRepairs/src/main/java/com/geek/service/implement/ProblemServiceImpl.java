package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;

import com.geek.model.Problem;
import com.geek.repository.ProblemRepository;
import com.geek.service.ProblemService;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	
	@Override
	public List<Problem> getAll() {
		List<Problem> problems= new ArrayList<>();
		problemRepository.findAll().iterator().forEachRemaining(problems::add);
		return problems;
	}

	@Override
	public Problem findById(Long id) {
		Optional<Problem> problem= problemRepository.findById(id);

		if (!problem.isPresent()) {
            throw new ResourceNotFoundException("There is no Problem with ID = " + id);
        }

		return problem.get();
	}

	@Override
	public Problem create(Problem entity) {
		Problem prob;
		prob = problemRepository.save(entity);
		return prob;
	}

	@Override
	public Problem update(Long id, Problem entity) {
		Problem  prob= findById(id);
		
		prob.setName(entity.getName());
		
		problemRepository.save(prob);	
		return prob;
	}

	@Override
	public void delete(Long id) {
		problemRepository.deleteById(id);
		
	}

	
	@Override
	public Problem getLatestEntry() {
		 List<Problem> prob= getAll();
	        if(prob.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestCategoryID = problemRepository.findTopByOrderByIdDesc();
	            return findById(latestCategoryID);
	        }
	}

	@Override
	public Page<Problem> findAll(Pageable pageable) {
		 return problemRepository.findAll(pageable);
	}

	@Override
	public boolean ProblemValid(Problem prob) {
		List<Problem> probs= new ArrayList<>();
        problemRepository.findByProblemName(prob.getName())
                .iterator().forEachRemaining(probs::add);
        if (!probs.isEmpty()) { return false;}
        else {return true;}
	}

}
