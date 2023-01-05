package com.js.controller;

import com.js.client.AlbumsClient;
import com.js.vo.AlbumsReq;
import com.js.vo.AlbumsResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController implements AlbumsClient {
    @Override
    @GetMapping("getAll")
    public List<AlbumsResp> getAll() {
        List<AlbumsResp> albumsResps = new ArrayList<>();
        AlbumsResp albumsResp = new AlbumsResp();
        albumsResp.setId(100L);
        albumsResp.setTitle("testTitle");
        albumsResp.setUserId(200L);
        albumsResps.add(albumsResp);
        return albumsResps;
    }

    @Override
    @GetMapping("getById")
    public AlbumsResp getById(Long id) {
        AlbumsResp albumsResp = new AlbumsResp();
        albumsResp.setId(100L);
        albumsResp.setTitle("testTitle");
        albumsResp.setUserId(200L);
        return albumsResp;
    }

    @Override
    @PostMapping("add")
    public AlbumsResp add(AlbumsReq req) {
        AlbumsResp albumsResp = new AlbumsResp();
        albumsResp.setId(100L);
        albumsResp.setTitle("testTitle");
        albumsResp.setUserId(200L);
        return albumsResp;
    }
}
