package com.epam.newsportal.service;

import com.epam.newsportal.dao.NewsDao;
import com.epam.newsportal.dto.News;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotEmpty;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;

    private final int NEWS_ON_PAGE_LIMIT = 10;

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public News getNewsById(final long id) {
        return newsDao.findOneById(id);
    }

    public News getNewsByUri(final Date date, @NotEmpty final String uri) {
        return newsDao.findNewsByUri(date, uri);
    }

    public List<News> getNewsListByPage(final int pageNumber) {
        return newsDao.findNewsListByPage(pageNumber, NEWS_ON_PAGE_LIMIT);
    }

    public void addNews(News news) {
        if (news.getUri() == null) {
            byte[] rndBytes = new byte[16];
            new Random().nextBytes(rndBytes);
            news.setUri(new String(rndBytes, Charset.forName("utf-8")));
        }
        newsDao.insert(news);
    }

    public void editNews(News news) {
        newsDao.update(news);
    }

    @Transactional
    public void removeNews(List<Long> newsId) {
        newsId.forEach(id -> {
            News n = new News();
            n.setId(id);
            newsDao.delete(n);
        });
    }
}