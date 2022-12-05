package com.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

  String currentPassword;
  String newPassword;
  String confirmPassword;
}
