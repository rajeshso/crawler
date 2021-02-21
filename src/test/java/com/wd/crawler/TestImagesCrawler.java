package com.wd.crawler;


import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

class TestImagesCrawler {

    private ImagesCrawler imagesCrawler = new ImagesCrawler();

    @Test
    void givenAPageWithAnImageWhenCrawledShouldFind1Image() throws Exception {
        Path path = Paths.get("src/test/resources/pageWithSingleImage.html");
        String page = Files.readString(path, ISO_8859_1);
        assertThat(page).isNotNull();
        Document document = Jsoup.parse(page);
        assertThat(imagesCrawler.getImages(document)).hasSize(1);
    }

    @Test
    void givenAPageWithAnImageWhenCrawledShouldFind3Image() throws Exception {
        Path path = Paths.get("src/test/resources/pageWithMultipleImages.html");
        String page = Files.readString(path, ISO_8859_1);
        assertThat(page).isNotNull();
        Document document = Jsoup.parse(page);
        assertThat(imagesCrawler.getImages(document)).hasSize(4);
    }


}
