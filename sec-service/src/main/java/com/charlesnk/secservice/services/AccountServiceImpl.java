package com.charlesnk.secservice.services;

import com.charlesnk.secservice.dao.AppRoleRepository;
import com.charlesnk.secservice.dao.AppUserRepository;
import com.charlesnk.secservice.entities.AppRole;
import com.charlesnk.secservice.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional // Sauvegarde à chaque fin d'execution d'execution d'une méthode
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //Autre technique d'injections( l'injection par proprietés n'est pas recommandé
    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user != null) {
            throw new RuntimeException("User Already exist");
        }
        if (!password.equals(confirmedPassword)) {
           throw new RuntimeException("Please confirm your password");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setActived(true);
        appUserRepository.save(appUser);
        addRoleToUser(username, "USER");
        return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        //if (user == null) throw new RuntimeException("Pas d'utilisateur");
        return user;
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole role1 = appRoleRepository.findByRoleName(role);
        user.getRoles().add(role1);
    }
}
