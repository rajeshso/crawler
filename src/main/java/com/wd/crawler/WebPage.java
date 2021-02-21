package com.wd.crawler;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebPage {
    private final String pageUrl;
    private boolean visited = false;
    private Set<WebPage> childUrls = new HashSet<>();
    private Set<String> externalUrls = new HashSet<>();
    private Set<String> imagesOnPage = new HashSet<>();

    public WebPage(String url){
        this.pageUrl = url;
    }

    public void addChildPage(WebPage childPage){
        childUrls.add(childPage);
    }

    public void addExternalUrl(String externalUrl){
        externalUrls.add(externalUrl);
    }

    public String summary(){
        StringBuilder content = new StringBuilder();
        content.append("Base page: ")
                .append(pageUrl)
                .append(" URLs to child pages:")
                .append(System.getProperty("line.separator"));
        for(WebPage childUrl : childUrls){
            content.append("    child page: ")
                    .append(childUrl.getPageUrl())
                    .append(System.getProperty("line.separator"));
        }
        content.append("the external links to:")
                .append(System.getProperty("line.separator"));
        for(String externalUrl : externalUrls){
            content.append("    external link: ")
                    .append(externalUrl)
                    .append(System.getProperty("line.separator"));
        }
        content.append("the images:")
                .append(System.getProperty("line.separator"));
        for(String imageOnPage : imagesOnPage){
            content.append("    image: ")
                    .append(imageOnPage)
                    .append(System.getProperty("line.separator"));
        }
        return content.toString();
    }
}