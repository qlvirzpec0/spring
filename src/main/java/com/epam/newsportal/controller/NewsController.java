package com.epam.newsportal.controller;

import com.epam.newsportal.dto.News;
import com.epam.newsportal.security.AuthorPrincipal;
import com.epam.newsportal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    private final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(path = "/news/{date}/{newsUri}", method = RequestMethod.GET)
    public ModelAndView showNewsByUri(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final Date date,
                                      @PathVariable @NotEmpty final String newsUri) {
        ModelAndView modelAndView = new ModelAndView("news");
        modelAndView.addObject("news", newsService.getNewsByUri(date, newsUri));
        return modelAndView;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView showNewsByPage() {
        return showNewsByPage(0);
    }

    @RequestMapping(path = "/{pageNumber:\\d*}", method = RequestMethod.GET)
    public ModelAndView showNewsByPage(@PathVariable @Min(0) final int pageNumber) {
        ModelAndView modelAndView = new ModelAndView("news-catalog");
        modelAndView.addObject("news",
                newsService.getNewsListByPage(pageNumber)
        );
        return modelAndView;
    }

    @RequestMapping(path = "/manage/news/add", method = RequestMethod.GET)
    public String showAddNewsPage(Model model) {
        model.addAttribute("news", new News());
        return "news-change-page";
    }

    @RequestMapping(path = "/manage/news/add", method = RequestMethod.POST)
    public String addNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult, @AuthenticationPrincipal AuthorPrincipal authorPrincipal) {
        if(bindingResult.hasErrors()){
            return "news-change-page";
        }
        news.setAuthor(authorPrincipal.getAuthor());
        newsService.addNews(news);
        return "redirect:/news/" + FORMATTER.format(news.getChangeDate()) + '/' + news.getUri();
    }

    @RequestMapping(path = "/manage/news/edit/{id}", method = RequestMethod.GET)
    public String showEditNewsPage(@PathVariable final long id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "news-change-page";
    }

    @RequestMapping(path = "/manage/news/edit", method = RequestMethod.POST)
    public String editNews(@ModelAttribute("news") @Valid News news, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "news-change-page";
        }
        newsService.editNews(news);
        return "redirect:/news/" + FORMATTER.format(news.getChangeDate()) + '/' + news.getUri();
    }

    @RequestMapping(path = "/manage/news/remove", method = RequestMethod.POST)
    public String removeNews(@RequestParam(value = "id") List<Long> newsId) {
        newsService.removeNews(newsId);
        return "redirect:/";
    }

}