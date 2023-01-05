package com.js.client;

import com.js.vo.AlbumsReq;
import com.js.vo.AlbumsResp;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

/**
 * 在此处添加备注信息
 *
 * @author YourBatman
 * @since 0.0.1
 */
@HttpExchange("/albums")
public interface AlbumsClient {

    @GetExchange
    List<AlbumsResp> getAll();

    @GetExchange("/{id}")
    AlbumsResp getById(@PathVariable Long id);

    @PostExchange
    AlbumsResp add(@RequestBody @Valid AlbumsReq req);

}
