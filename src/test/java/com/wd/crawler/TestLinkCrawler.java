package com.wd.crawler;



import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;


class TestLinkCrawler {

    private LinkCrawler crawler = new LinkCrawler();

    @Test
    void shouldFind4Links() throws Exception {
        Path path = Paths.get("src/test/resources/pageWithMultipleLinks.html");
        String page = Files.readString(path, ISO_8859_1);
        Document document = Jsoup.parse(page);
        Set<String> findedLinks = crawler.getLinks(document);
        assertThat(findedLinks).hasSize(4);
    }

    @Test
    void shouldGetEmptySet() throws Exception {
        Path path = Paths.get("src/test/resources/pageWithoutLinks.html");
        String page = Files.readString(path, ISO_8859_1);
        Document document = Jsoup.parse(page);
        Set<String> links = crawler.getLinks(document);
        assertThat(links).hasSize(0);
    }
}
