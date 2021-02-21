package com.wd.crawler;

import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;

public class LinkCrawler {

    public static final String A_HREF = "a[href]";
    public static final String ABS_HREF = "abs:href";
    public static final String INPUT_LINK_URL = "input.linkURL";

    public Set<String> getLinks(Document document){
        Set<String> links = document.select(A_HREF).stream()
            .map(link -> link.attr(ABS_HREF)).collect(Collectors.toSet());
        document.select(INPUT_LINK_URL).stream().map(link -> link.attr("abs:value"))
            .forEach(links::add);
        return links;
    }
}
