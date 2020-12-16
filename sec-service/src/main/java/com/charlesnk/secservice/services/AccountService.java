package com.charlesnk.secservice.services;

import com.charlesnk.secservice.entities.AppRole;
import com.charlesnk.secservice.entities.AppUser;

public interface AccountService {

    public AppUser saveUser(String username, String password, String confirmedPassword);
    public AppRole save (AppRole role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String role);

}
