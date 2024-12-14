package com.springboot.inventory.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String mobile;
	private String state;
	private Integer pincode;
	private List<ProductDTO> productDTO;
	
	public static enum State {
		AndhraPradesh,ArunachalPradesh,
		Goa,
		Karnataka,
		Kerala,
		Maharashtra,
		Punjab,
		TamilNadu,
		Telangana
	}

}
