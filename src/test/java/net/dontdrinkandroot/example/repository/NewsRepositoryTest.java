package net.dontdrinkandroot.example.repository;

import net.dontdrinkandroot.example.AngularRestSpringsecurityApplicationTests;
import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by win7 on 2016/10/2.
 */
public class NewsRepositoryTest extends AngularRestSpringsecurityApplicationTests {

    @Autowired
    private NewsRepository newsRepository;

    @Test
    public void testGetByOrder() {
        newsRepository.findAll(new Sort(Sort.Direction.ASC, "date"));
    }
}
