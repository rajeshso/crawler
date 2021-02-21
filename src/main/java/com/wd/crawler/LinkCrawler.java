package com.wd.crawler;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import org.jsoup.nodes.Document;

public class LinkCrawler {

    private static final String A_HREF = "a[href]";
    private static final String INPUT_LINK_URL = "input.linkURL";
    private static final String ABS_HREF = "abs:href";
    private static final String ABS_VALUE = "abs:value";

    public Set<String> getLinks(Document document){
        Set<String> linkSet = document.select(A_HREF).stream().map(link -> link.attr(ABS_HREF))
            .collect(toSet());
        document.select(INPUT_LINK_URL).stream().map(link -> link.attr(ABS_VALUE))
            .forEach(linkSet::add);
        return linkSet;
    }
}
