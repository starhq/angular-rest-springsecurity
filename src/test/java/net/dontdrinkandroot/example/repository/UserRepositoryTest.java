package net.dontdrinkandroot.example.repository;

import net.dontdrinkandroot.example.AngularRestSpringsecurityApplicationTests;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by win7 on 2016/10/2.
 */
public class UserRepositoryTest extends AngularRestSpringsecurityApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetOne() {
        userRepository.findByName("123");
    }
}
