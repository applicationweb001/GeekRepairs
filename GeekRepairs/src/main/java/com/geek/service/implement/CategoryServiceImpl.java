package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Article;
import com.geek.model.Category;
import com.geek.repository.CategoryRepository;
import com.geek.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().iterator().forEachRemaining(categories::add);
		return categories;
	}

	@Override
	public Category create(Category object) {
		Category newcategory;
		newcategory = categoryRepository.save(object);
		return newcategory;
	}

	@Override
	public Category update(Long id, Category objectupdate) {
		Category category = findById(id);
		
		category.setName(objectupdate.getName());
		
		categoryRepository.save(category);	
		return category;
	}

	@Override
	public void delete(Long id) {
		categoryRepository.delete(findById(id));
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (!category.isPresent()) {
            throw new ResourceNotFoundException("There is no Category with ID = " + id);
        }

		return category.get();
	}

	@Override
	public Category getLatestEntry() {
		 List<Category> category = getAll();
	        if(category.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestCategoryID = categoryRepository.findTopByOrderByIdDesc();
	            return findById(latestCategoryID);
	        }
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		 return categoryRepository.findAll(pageable);
	}

	@Override
	public boolean CategoryValid(Category category) {
		List<Category> categories= new ArrayList<>();
        categoryRepository.findByCategoryName(category.getName())
                .iterator().forEachRemaining(categories::add);
        if (!categories.isEmpty()) { return false;}
        else {return true;}
		
	}

}
