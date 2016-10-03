package net.dontdrinkandroot.example;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.UserRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Date;

@SpringBootApplication
public class AngularRestSpringsecurityApplication {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private PasswordEncoder standardPasswordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(AngularRestSpringsecurityApplication.class, args);
    }

    @PostConstruct
    public void initDataBase() {
        User userUser = new User("user", standardPasswordEncoder.encode("user"));
        userUser.addRole("user");
        userRepository.save(userUser);

        User adminUser = new User("admin", standardPasswordEncoder.encode("admin"));
        adminUser.addRole("user");
        adminUser.addRole("admin");
        userRepository.save(adminUser);

        long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
        for (int i = 0; i < 10; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setContent("This is example content " + i);
            newsEntry.setDate(new Date(timestamp));
            newsRepository.save(newsEntry);
            timestamp += 1000 * 60 * 60;
        }
    }
}
