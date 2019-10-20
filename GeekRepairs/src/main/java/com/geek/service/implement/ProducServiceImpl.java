package com.geek.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.model.entity.Product;
import com.geek.model.repository.ProductRepository;
import com.geek.service.ProductService;

@Service
public class ProducServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getOneById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found!"));
	}

	@Override
	public Long create(Product entity) {
		productRepository.save(entity);
        return entity.getId();
	}

	@Override
	public void update(Long id, Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
		
	}

}
