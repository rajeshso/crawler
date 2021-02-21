package com.wd.crawler;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ImageCrawler {

    private static final String REGEX_FOR_IMAGES = "(http.*?(jpg|png|gif|bmp|img|jpeg|ico))";
    public static final String A_STYLE = "a[style]";
    public static final String DIV_STYLE = "div[style]";

    public Set<String> getImages(Document document){
        Set<String> imagesOnPage;
        Elements elementsWithImages = document.select(A_STYLE);
        Elements divElementsWithImages = document.select(DIV_STYLE);
        elementsWithImages.addAll(divElementsWithImages);
        imagesOnPage = elementsWithImages.stream()
            .map(e -> findImageInString(e.attr("style")))
            .filter(i -> !i.isEmpty())
            .collect(toSet());

        Elements imagesFromSrc = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
        imagesOnPage.addAll(imagesFromSrc.stream()
            .map(e -> e.attr("src"))
            .filter(a -> !a.isEmpty())
            .collect(toSet()));

        return imagesOnPage;
    }

    private static String findImageInString(String str){
        Matcher matcher = Pattern.compile(REGEX_FOR_IMAGES).matcher(str);
        if (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end();
            return str.substring(start,end);
        }
        return "";
    }
}
