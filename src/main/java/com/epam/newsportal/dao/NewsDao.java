package com.epam.newsportal.dao;

import com.epam.newsportal.dto.News;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface NewsDao  extends AbstractDao<News> {
    News findNewsByUri(Date date, @NotNull String uri);
    List<News> findNewsListByPage(final int offset, final int limit);
}
