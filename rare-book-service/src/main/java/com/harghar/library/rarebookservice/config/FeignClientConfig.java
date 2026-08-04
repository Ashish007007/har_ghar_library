package com.harghar.library.rarebookservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.harghar.library.rarebookservice.exception.UpstreamServiceException;

import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                int status = response.status();
                if (status == 404) {
                    return new UpstreamServiceException(status, "Upstream resource not found for " + methodKey);
                }
                if (status >= 500) {
                    return new UpstreamServiceException(status, "Upstream service error for " + methodKey);
                }
                return new UpstreamServiceException(status, "Upstream call failed for " + methodKey);
            }
        };
    }
}
