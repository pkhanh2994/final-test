package com.finaltest.app.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsRequestModel {

   private String fistName;
   private String lastName;
   private String email;
   private String password;
}
