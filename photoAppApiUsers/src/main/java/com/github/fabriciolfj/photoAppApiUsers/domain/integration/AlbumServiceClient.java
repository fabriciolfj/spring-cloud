package com.github.fabriciolfj.photoAppApiUsers.domain.integration;

import com.github.fabriciolfj.photoAppApiUsers.api.model.AlbumResponseModel;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "albums-ws", decode404 = false, fallbackFactory = AlbumFallbackFactory.class)
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}

@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable cause) {
        return new AlbumServiceClientFallback(cause);
    }
}

@Slf4j
@RequiredArgsConstructor
class AlbumServiceClientFallback implements AlbumServiceClient {

    private final Throwable cause;

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            log.error("404 error: {}.", cause.getLocalizedMessage());
        } else {
            log.error("Other error took place: {}", cause.getLocalizedMessage());
        }

        return Collections.EMPTY_LIST;
    }
}