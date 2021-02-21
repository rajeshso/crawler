package com.wd.crawler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.jupiter.api.Test;

public class TestPageCrawler {

  @Test
  void shouldGetAllLinks() {
    PageCrawler pageCrawler = new PageCrawler("http://www.google.com/");
    pageCrawler.crawl();
    final Set<WebPage> pages = pageCrawler.getPagesToVisit();
    assertThat(pages.size()).isOne();
    final WebPage webPage = pages.iterator().next();
    assertThat(webPage.isVisited()).isTrue();
    assertThat(webPage.getPageUrl().equals("http://www.google.com/"));
    assertThat(webPage.getChildUrls()).hasSize(0);
    assertThat(webPage.getExternalUrls()).isNotEmpty();
    assertThat(webPage.getImagesOnPage()).isNotEmpty();
  }
}
