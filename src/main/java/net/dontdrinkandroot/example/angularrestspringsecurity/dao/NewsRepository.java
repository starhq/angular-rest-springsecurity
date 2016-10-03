package net.dontdrinkandroot.example.angularrestspringsecurity.dao;

import net.dontdrinkandroot.example.angularrestspringsecurity.entity.NewsEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by win7 on 2016/10/2.
 */
public interface NewsRepository extends JpaRepository<NewsEntry, Long> {
}
