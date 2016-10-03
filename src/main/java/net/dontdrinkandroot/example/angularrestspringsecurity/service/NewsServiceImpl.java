package net.dontdrinkandroot.example.angularrestspringsecurity.service;

import net.dontdrinkandroot.example.angularrestspringsecurity.dao.NewsRepository;
import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by win7 on 2016/10/3.
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl {

    @Autowired
    private NewsRepository newsRepository;

    @Transactional(readOnly = true)
    public List<NewsEntry> findAll() {
        return newsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public NewsEntry findOne(Long id) {
        return newsRepository.findOne(id);
    }

    public void saveOrUpdate(NewsEntry newsEntry) {
        newsRepository.saveAndFlush(newsEntry);
    }

    public void delete(Long id) {
        newsRepository.delete(id);
    }
}
