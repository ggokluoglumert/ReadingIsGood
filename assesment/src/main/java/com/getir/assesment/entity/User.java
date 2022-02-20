package com.getir.assesment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String userName;

	@NotNull
	private String password;

	@Column(nullable = false, unique = true)
	private String eMail;
	
}
