package com.qantas.webcrawler.api.controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.qantas.webcrawler.api.configurations.AppProperties;
import com.qantas.webcrawler.api.context.TransactionContext;
import com.qantas.webcrawler.api.context.TransactionContextBuilder;
import com.qantas.webcrawler.api.dto.ResponseMapper;
import com.qantas.webcrawler.api.dto.WebcrawlRequest;
import com.qantas.webcrawler.api.dto.WebcrawlRequestBuilder;
import com.qantas.webcrawler.api.dto.WebcrawlerResponse;
import com.qantas.webcrawler.api.exceptions.NoLinksException;
import com.qantas.webcrawler.api.exceptions.WebcrawlerException;
import com.qantas.webcrawler.api.service.WebCrawlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping(value = "/api/v1")
@ApiResponses({
        @ApiResponse(code = 200, message = "Success Response"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found")
})
public class WebCrawlingController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private WebCrawlService webCrawlService;
    private AppProperties appProperties;

    @Autowired
    WebCrawlingController(final WebCrawlService webCrawlService,
                          final AppProperties appProperties) {
        this.webCrawlService = webCrawlService;
        this.appProperties = appProperties;
    }

    @ApiOperation(value = "Displays Page informations", tags = "crawl")
    @RequestMapping(value = "/crawler", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletionStage<ResponseEntity> webPageTreeInfo(
            @ApiParam(value = "Web page url to get page info", required = true)
            @NotNull @RequestParam(value = "url", required = true) final String url,
            @ApiParam(value = "Desired depth for crawling")
            @RequestParam(value = "depth", required = false) final Integer depth,
            final HttpServletRequest request) {

        log.info("Request for deep crawling received for url: {}, depth: {}", url, depth);
        final int newDepth = Integer.min(Optional.ofNullable(depth).orElse(appProperties.getDefaultDepth()),
                appProperties.getMaxDepthAllowed());
        log.info(
                "Depth might be optimized to go upto Max defined in " +
                        "property:'app.max-depth-allowed'. optimized depth: {}", newDepth);

        TransactionContext tranxContext = new TransactionContextBuilder()
                .withHttpRequest(request).build();

        WebcrawlRequest webcrawlRequest = new WebcrawlRequestBuilder()
                .withTransactionContext(tranxContext)
                .withWebUrl(url)
                .withDepth(newDepth)
                .build();


        return webCrawlService.doDeepCrawl(webcrawlRequest, null)
                .handle((oResponse, throwable) -> handleResponse(oResponse, throwable, tranxContext));
    }

    private ResponseEntity handleResponse(final Object oResponse,
                                          final Throwable throwable,
                                          final TransactionContext tranxContext) {

        if (throwable instanceof WebcrawlerException) {
            WebcrawlerException webcrawlerException = (WebcrawlerException) throwable;
            return ResponseMapper.mapExceptionResponse(tranxContext,
                    webcrawlerException);
        } else if (throwable != null) {
            return ResponseMapper.internalServerErrorResponse(tranxContext,
                    "50000", throwable.getMessage());
        }

        if (oResponse instanceof WebcrawlerResponse) {
            WebcrawlerResponse webcrawlerResponse = (WebcrawlerResponse) oResponse;
            JsonNode pageLinks = webcrawlerResponse.getPageInfo();
            return ResponseMapper.crawlerResponse(tranxContext, pageLinks);
        }

        if (oResponse instanceof NoLinksException) {
            NoLinksException noLinksException = (NoLinksException) oResponse;
            return ResponseMapper.badRequestResponse(tranxContext,
                    String.valueOf(noLinksException.getCode()), noLinksException.getMessage());
        }

        return ResponseMapper.internalServerErrorResponse(tranxContext, "50002", oResponse.toString());
    }
}
