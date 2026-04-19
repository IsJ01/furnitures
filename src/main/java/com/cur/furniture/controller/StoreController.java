package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.config.StoreProperties;
import com.cur.furniture.dto.StoreInfoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreProperties storeProperties;
    
    @GetMapping("/info")
    public StoreInfoDto info() {
        return new StoreInfoDto(
            storeProperties.getName(), 
            storeProperties.getPhone(), 
            storeProperties.getAddress()
        );
    }
    

}
