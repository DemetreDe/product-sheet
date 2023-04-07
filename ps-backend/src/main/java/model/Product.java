package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productID;
	
	@Column(name = "productname")
	private String productName;
	
	@Column(name = "productdescription")
	private String productDescription;
	
	@Column(name = "salesprice")
	private int salesPrice;
	
	@Column(name = "actualprice")
	private int actualPrice;
	
	public Product() {
		
	}
	
	public Product(String productName, String productDescription, int salesPrice, int actualPrice) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.salesPrice = salesPrice;
		this.actualPrice = actualPrice;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}

	public int getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}
	
	
}
