package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import net.dontdrinkandroot.example.angularrestspringsecurity.service.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by win7 on 2016/10/3.
 */
@RestController
@RequestMapping("/news")
public class NewsEntryResource {

    private final Logger logger = LoggerFactory.getLogger(NewsEntryResource.class);
    @Autowired
    private NewsServiceImpl newsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<NewsEntry> list() {
        return newsService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public NewsEntry read(@PathVariable Long id) {
        return newsService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public NewsEntry create(@RequestBody NewsEntry newsEntry) {
        newsService.saveOrUpdate(newsEntry);
        return newsEntry;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public NewsEntry update(@PathVariable Long id, @RequestBody NewsEntry newsEntry) {
        newsService.saveOrUpdate(newsEntry);
        return newsEntry;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }
}
