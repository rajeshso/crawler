package com.wd.crawler;

public class Main {
    private final static String BASE_URL = "https://wiprodigital.com";

    public static void main(String[] args) {
        PageCrawler crawler = new PageCrawler(BASE_URL);
        crawler.crawl();
    }
}