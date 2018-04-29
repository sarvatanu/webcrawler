package com.qantas.webcrawler.api.service;

import com.qantas.webcrawler.api.configurations.AppProperties;
import com.qantas.webcrawler.api.dto.Page;
import com.qantas.webcrawler.api.dto.PageInfo;
import com.qantas.webcrawler.api.dto.WebcrawlRequest;
import com.qantas.webcrawler.api.dto.WebcrawlerResponse;
import com.qantas.webcrawler.api.exceptions.NoLinksException;
import com.qantas.webcrawler.api.exceptions.WebcrawlerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class WebCrawlService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final Integer EXCEPTION_ERROR_CODE = 4000;
    private AppProperties appProperties;

    @Autowired
    public WebCrawlService(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public CompletableFuture<Object> doDeepCrawl(final WebcrawlRequest webcrawlRequest,
                                                 final List<String> processedUrls) {
        try {
            Page page = doDeepCrawl(webcrawlRequest.getWebUrl(), webcrawlRequest.getDepth(), processedUrls);

            if (page != null) {
                return CompletableFuture.completedFuture(new WebcrawlerResponse(page));
            } else {
                return CompletableFuture.completedFuture(
                        new NoLinksException("No linked pages are found.", NOT_FOUND.value()));
            }
        } catch (WebcrawlerException wcE) {
            CompletableFuture completableFuture = new CompletableFuture();
            completableFuture.completeExceptionally(wcE);
            return completableFuture;
        }
    }

    private Page doDeepCrawl(final String url,
                             final int depth,
                             final List<String> processedUrls) {

        try {
            log.debug("Starting crawler for url {} for depth {}", url, depth);
            if (depth < 0) {
                log.info("Maximum depth reached, backing out for url {}", url);
                return null;
            } else {
                final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());
                if (!updatedProcessedUrls.contains(url)) {
                    updatedProcessedUrls.add(url);
                    final Page page = new Page(url);
                    crawl(url).ifPresent(pageInfo -> {
                        page.title(pageInfo.getTitle()).valid(true);
                        log.info("Found {} links on the web page: {}", pageInfo.getLinks().size(), url);
                        pageInfo.getLinks().forEach(link -> {
                            page.addNodesItem(doDeepCrawl(link.attr("abs:href"), depth - 1, updatedProcessedUrls));
                        });
                    });
                    return page;
                } else {
                    return null;
                }
            }
        } catch (Exception ex) {
            throw new WebcrawlerException(ex.getMessage(), EXCEPTION_ERROR_CODE);
        }
    }

    public Optional<PageInfo> crawl(final String url) {
        log.info("Fetching contents for url: {}", url);
        try {
            final Document doc = Jsoup.connect(url).timeout(appProperties.getConnectionTimeOut())
                    .followRedirects(appProperties.isFollowRedirects()).get();

            final Elements links = doc.select("a[href]");
            final String title = doc.title();
            log.debug("Fetched title: {}, links[{}] for url: {}", title, links.nextAll(), url);
            return Optional.of(new PageInfo(title, url, links));
        } catch (final IOException | IllegalArgumentException e) {
            log.error(String.format("Error getting contents of url %s", url), e);
            throw new WebcrawlerException(e.getMessage() + " : " + url, EXCEPTION_ERROR_CODE);
        }
    }
}
