package com.wd.crawler;

import static java.util.stream.Collectors.toSet;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ImagesCrawler {

  private static final String REGEX_FOR_IMAGES = "(http.*?(jpg|png|gif|bmp|img|jpeg|ico))";
  public static final String A_STYLE_PATTERN = "a[style]";
  public static final String DIV_STYLE_PATTERN = "div[style]";
  public static final String IMAGE_SRC_PATTERN = "img[src~=(?i)\\.(png|jpe?g|gif)]";
  public static final String STYLE_PATTERN = "style";
  public static final String SRC_PATTERN = "src";
  private static final String EMPTY_STRING = "";

  public Set<String> getImagesOnPage(Document document){
    Set<String> imagesOfDocument = new HashSet<>();
    Elements images = document.select(A_STYLE_PATTERN);
    Elements divs = document.select(DIV_STYLE_PATTERN);
    images.addAll(divs);
    imagesOfDocument = images.stream()
        .map(i -> findImageInString(i.attr(STYLE_PATTERN)))
        .filter(i -> !i.isEmpty())
        .collect(toSet());

    Elements imagesSrc = document.select(IMAGE_SRC_PATTERN);
    imagesOfDocument.addAll(imagesSrc.stream()
        .map(i -> i.attr(SRC_PATTERN))
        .filter(i -> !i.isEmpty())
        .collect(toSet()));

    return imagesOfDocument;
  }

  private static String findImageInString(String str){
    Matcher matcher = Pattern.compile(REGEX_FOR_IMAGES).matcher(str);
    if (matcher.find()) {
      int matchStart = matcher.start(1);
      int matchEnd = matcher.end();
      return str.substring(matchStart,matchEnd);
    }
    return EMPTY_STRING;
  }
}
