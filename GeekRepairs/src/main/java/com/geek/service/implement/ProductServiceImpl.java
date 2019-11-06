
package com.geek.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.geek.exception.ResourceNotFoundException;
import com.geek.model.Product;
import com.geek.repository.ProductRepository;
import com.geek.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().iterator().forEachRemaining(products::add);
		return products;
	}

	@Override
	public Product create(Product object) {
		Product newproduct;
		newproduct = productRepository.save(object);
		return newproduct;
	}

	@Override
	public Product update(Long id, Product objectupdate) {
		Product product = findById(id);
		
		product.setName(objectupdate.getName());
		product.setUnitPrice(objectupdate.getUnitPrice());
		
		productRepository.save(product);	
		return product;
	}

	@Override
	public void delete(Long objectId) {
		productRepository.delete(findById(objectId));
		
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
            throw new ResourceNotFoundException("There is no Product with ID = " + id);
        }

		return product.get();
	}

	@Override
	public Product getLatestEntry() {
		 List<Product> products = getAll();
	        if(products.isEmpty()){
	            return null;
	        }
	        else{
	            Long latestProductID = productRepository.findTopByOrderByIdDesc();
	            return findById(latestProductID);
	        }
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		 return productRepository.findAll(pageable);
	}

	@Override
	public boolean ProductValid(Product product) {
		List<Product> products= new ArrayList<>();
		productRepository.findByProductName(product.getName())
                .iterator().forEachRemaining(products::add);
        if (!products.isEmpty()) { return false;}
        else {return true;}
	}

}
