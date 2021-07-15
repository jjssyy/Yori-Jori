package com.web.curation.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@Valid
@ToString

public class ChangePassword {
   @ApiModelProperty(required = true)
    @NotNull
    String email;
    @ApiModelProperty(required = true)
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
   String newPassword;
    
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getNewPassword() {
      return newPassword;
   }
   public void setNewPassword(String newPassword) {
      this.newPassword = newPassword;
   }
    
}