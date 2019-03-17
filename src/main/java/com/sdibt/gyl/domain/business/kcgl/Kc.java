package com.sdibt.gyl.domain.business.kcgl;

import java.util.Set;

import com.sdibt.gyl.domain.basedata.Product;
import com.sdibt.gyl.domain.basedata.Repository;

public class Kc {

	private Long ckspbh;
	private Repository repository;
	private Product product;
	private Long quantity;

	public Long getCkspbh() {
		return ckspbh;
	}

	public void setCkspbh(Long ckspbh) {
		this.ckspbh = ckspbh;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
