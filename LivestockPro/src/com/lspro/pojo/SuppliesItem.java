package com.lspro.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="物资储备条目管理表")
public class SuppliesItem implements Serializable {
	
	@Id @Column(name="物资编号")
	private String suppliesId;	//物资编号
	@Column(name="物资名称")
	private String suppliesName;	//物资名称
	@Column(name="物资价格")
	private String suppliesPrice;	//物资价格
	@Column(name="物资生产商")
	private String producter;		//物资生产商
	@Column(name="物资数量")
	private String number;		//物资数量
	@Column(name="有效期")
	private String validDate;	//有效期
	@Column(name="失效情况")
	private String failSitution;	//失效情况
	@Column(name="生产日期")
	private String productDate;
	@ManyToOne(targetEntity=Supplies.class,fetch=FetchType.EAGER)
	@JoinColumn(name="储备点编号",referencedColumnName="储备点编号",nullable=false)
	private Supplies supplies;
	
	@OneToMany(targetEntity=SuppliesDispatch.class,mappedBy="item",fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<SuppliesDispatch> supDis = new ArrayList<SuppliesDispatch>();
	
	public String getSuppliesId() {
		return suppliesId;
	}
	public void setSuppliesId(String suppliesId) {
		this.suppliesId = suppliesId;
	}
	public String getSuppliesName() {
		return suppliesName;
	}
	public void setSuppliesName(String suppliesName) {
		this.suppliesName = suppliesName;
	}
	public String getSuppliesPrice() {
		return suppliesPrice;
	}
	public void setSuppliesPrice(String suppliesPrice) {
		this.suppliesPrice = suppliesPrice;
	}
	public String getProducter() {
		return producter;
	}
	public void setProducter(String producter) {
		this.producter = producter;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getFailSitution() {
		return failSitution;
	}
	public void setFailSitution(String failSitution) {
		this.failSitution = failSitution;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public Supplies getSupplies() {
		return supplies;
	}
	public void setSupplies(Supplies supplies) {
		this.supplies = supplies;
	}
	public List<SuppliesDispatch> getSupDis() {
		return supDis;
	}
	public void setSupDis(List<SuppliesDispatch> supDis) {
		this.supDis = supDis;
	}
	
	
}
