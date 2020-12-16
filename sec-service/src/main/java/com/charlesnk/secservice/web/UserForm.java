package com.charlesnk.secservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserForm {

    private String username;
    private String password;
    private String confirmedPassword;


}
