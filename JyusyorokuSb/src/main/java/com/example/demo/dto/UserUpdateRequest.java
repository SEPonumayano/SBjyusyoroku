package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserUpdateRequest extends UserRequest implements Serializable{
	//ID
	@NotNull
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

}
