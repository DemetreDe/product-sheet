package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Product;
import exception.ResourceNotFoundException;
import repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	//get all products
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	//create product
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	//get product by id
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product with id: "+id+" does not exist"));
		return ResponseEntity.ok(product);
	}
	
	//update product
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product with id: "+id+" does not exist"));
		
		product.setProductName(productDetails.getProductName());
		product.setProductDescription(productDetails.getProductDescription());
		product.setSalesPrice(productDetails.getSalesPrice());
		product.setActualPrice(productDetails.getActualPrice());
		
		Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	//delete product
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("product with id: "+id+" does not exist"));
		
		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
