package com.wd.crawler;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

public class TestImagesCrawler {

    private ImagesCrawler imagesCrawler = new ImagesCrawler();

    @Test
    public void givenAPageWithAnImageWhenCrawledShouldFind1Image() throws Exception {
        String page = Files.readString(Paths.get("/pageWithImage.html"));
        assertNotNull(page);

        Document document = Jsoup.parse(page);

        Set<String> imagesOnPage = imagesCrawler.getImagesOnPage(document);

        assertThat(imagesOnPage).hasSize(1);
    }


}
