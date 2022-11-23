package com.geryon.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.geryon.dto.CustomerType;

@Entity
public class Customer {
	
	@Id
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	@Enumerated(value=EnumType.STRING)
	private CustomerType customerType;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	@Override
	public String toString() {
		return "CustomerDTO [customerId = " + this.getCustomerId() + 
				", emailId = " + this.getEmailId() + 
				", name = " + this.getName() + 
				", dateOfBirth = " + this.getDateOfBirth() + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getCustomerId() == null) ? 0 : this.getCustomerId().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null) return false;
		if (this.getClass() != object.getClass()) return false;
		
		Customer customerObject = (Customer) object;
		
		if (this.getCustomerId() == null) {
			if (customerObject.getCustomerId() != null) {
				return false;
			}
		} else if (!this.getCustomerId().equals(customerObject.getCustomerId())) return false;
		
		return true;
	}

}

/*
 * NOTE:	
 * 			Spring Boot uses "SpringPhysicalNamingStrategy" for physical naming. 
 * 		This means that fields in an entity, when camelCased, are replaced by 
 * 		underscores (i.e., "dateOfBirth" is replaced by "date_of_birth"), and all 
 * 		table names are generated in lower-case. This makes it possible to avoid using 
 * 		the @Column annotation of an entity's field has a corresponding column in it's
 * 		corresponding database table that matches that field, with underscores.
 * 		
 * 			@Column can still be used to explicitly map an entity's field to its 
 * 		corresponding table column name (i.e., the field dateOfBirth can be explicitly 
 * 		mapped with @Column(name="date_of_birth") annotation).
 * 		
 */
