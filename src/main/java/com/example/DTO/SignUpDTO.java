package com.example.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
  @NotBlank
  private String username;

  @NotBlank
  @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "wrong format, should be abc@abc.com ")
  private String email;

  private Set<String> role;

  @NotBlank
  private String password;
  @NotBlank
  private String fullName;
  @NotBlank
  private Boolean gender;

  private Boolean active;


}