package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.UserPrinsipals;
import com.DiagramToDb.LMDToDB.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyuserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String usernameoremail) throws UsernameNotFoundException {
      UserEntity user  = repo.findByUsername(usernameoremail);
      if(user==null){
           user = repo.findByEmail(usernameoremail);
          if(user!=null){
              return new UserPrinsipals(user);
          }else{System.out.println("user not found");
              throw new UsernameNotFoundException("USER NOT FOUND");

          }
      }else{
          return new UserPrinsipals(user);
      }
    }
}
