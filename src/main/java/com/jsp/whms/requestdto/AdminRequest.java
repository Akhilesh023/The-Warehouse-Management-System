package com.jsp.whms.requestdto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

public class AdminRequest {
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$" , message = "Username must contain only"
			+ "alphabets and no special characters or numbers")
	private String name;
	@NotNull
	@NotBlank
	@Email(regexp ="^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must end"
			+ "with gmail.com")
	private String email;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
	, message = "Enter a valid password")
	private String password;
	
	

}
