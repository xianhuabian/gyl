package com.sdibt.gyl.query.business.kcgl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.sdibt.gyl.query.BaseQuery;

public class KcglQuery extends BaseQuery {

	private String ckspbh;
	private String repository;
	private String product;
	private Long quantity;
	private String spbm;
	private Long pid;
	
	
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	public String getCkspbh() {
		return ckspbh;
	}

	public void setCkspbh(String ckspbh) {
		this.ckspbh = ckspbh;
	}

	public String getRepository() {
		return repository;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public Map<String, Object> buildWhere() {
		if (StringUtils.isNotBlank(repository)) {
			this.keyValues.put("repository.name", this.repository);
		}
		if (StringUtils.isNotBlank(product)) {
			this.keyValues.put("product.spmc", this.product);
		}
		if (StringUtils.isNotBlank(ckspbh)) {
			this.keyValues.put("ckspbh", this.ckspbh);
		}
		if (StringUtils.isNotBlank(spbm)) {
			this.keyValues.put("product.spbm", this.spbm);
		}
		if (pid != null) {
			this.keyValues.put("product.pid", this.pid);
		}
		
		return this.keyValues;
	}

}
