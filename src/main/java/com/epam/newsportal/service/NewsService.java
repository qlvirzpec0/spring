package com.epam.newsportal.service;

import com.epam.newsportal.dto.News;

import java.util.Date;
import java.util.List;

public interface NewsService {
    News getNewsById(final long id);

    News getNewsByUri(final Date date, final String uri);

    List<News> getNewsListByPage(final int pageNumber);

    void addNews(News news);

    void editNews(News news);

    void removeNews(List<Long> newsId);
}
