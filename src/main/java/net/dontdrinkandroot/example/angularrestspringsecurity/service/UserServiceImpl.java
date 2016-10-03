package net.dontdrinkandroot.example.angularrestspringsecurity.service;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.UserRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by win7 on 2016/10/3.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("The user with name " + s + " was not found");
        }
        return user;
    }


}
