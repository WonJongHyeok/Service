package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper( (HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper( (HttpServletResponse) response);
        log.info("INIT URI : {}",req.getRequestURI());

        chain.doFilter(req, res); // 여길 기준으로 위가 실행 전, 아래가 실행 후 이다.

        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            // authorization-token : ??? , user-agent : ??
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>>>>> uri : {} , method : {} , header : {} , body : {}",uri, method, headerValues,requestBody); // 들어왔을때 로그

        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(" ] ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<<<uri : {} , method : {} , header : {}, body : {}",uri, method, responseHeaderValues, responseBody); // 나갈때 로그

        res.copyBodyToResponse(); // 안쓰면 responseBody가 비워져 나옴

    }


}
