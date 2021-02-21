package com.wd.crawler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PageCrawler {

  private String baseUrl;

  private Set<WebPage> pagesToVisit = new CopyOnWriteArraySet<>();

  private LinkCrawler linkCrawler = new LinkCrawler();
  private ImageCrawler imageCrawler = new ImageCrawler();
  private WebPagesPrinter webPagesPrinter = new WebPagesPrinter();

  public PageCrawler(String baseurl) {
    this.baseUrl = baseurl;
  }

  public void crawl() {
    WebPage basePage = new WebPage(baseUrl);
    pagesToVisit.add(basePage);
    addWebPages(basePage);
    for (WebPage page : pagesToVisit) {
      addWebPages(page);
    }
    print();
  }

  public Set<WebPage> getPagesToVisit() {
    return pagesToVisit;
  }

  private void addWebPages(WebPage webPage) {
    if (webPage.isVisited()) {
      return;
    }
    try {
      webPage.setVisited(true);
      Document document = Jsoup.connect(webPage.getPageUrl()).get();

      Set<String> allLinks = linkCrawler.getLinks(document);

      for (String link : allLinks) {
        if (isNotExternalLink(link)) {
          WebPage childPage = getChildPage(link);
          pagesToVisit.add(childPage);
          webPage.addChildPage(childPage);
        } else {
          webPage.addExternalUrl(link);
        }
      }

      webPage.setImagesOnPage(imageCrawler.getImages(document));

    } catch (IOException e) {
      System.err.println("For '" + webPage.getPageUrl() + "': " + e.getMessage());
    }
  }

  private boolean isNotExternalLink(String url) {
    if (url.length() >= baseUrl.length()) {
      return url.startsWith(baseUrl);
    }
    return false;
  }

  private WebPage getChildPage(String link) {
    for (WebPage page : pagesToVisit) {
      if (page.getPageUrl().equals(link)) {
        return page;
      }
    }
    return new WebPage(link);
  }

  private void print() {
    for (WebPage page : pagesToVisit) {
      webPagesPrinter.print(page);
    }
  }
}