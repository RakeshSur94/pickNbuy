/*
package com.ecomm.protal.service.api;

import com.ecomm.protal.service.entity.Product;
import com.ecomm.protal.service.entity.ProductCategory;
import com.ecomm.protal.service.repo.ProductCategoryRepository;
import com.ecomm.protal.service.repo.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class api {

    private final ProductCategoryRepository productRepository;
    private final ProductRepository repository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @GetMapping
    public String test() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:"+"rest.json");
        try(InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)){
            String j = FileCopyUtils.copyToString(reader);
            List<ProductCategory> restaurantEntities = objectMapper.readValue(j, new TypeReference<List<ProductCategory>>() {

            });


            this.productRepository.saveAll(restaurantEntities);
        }
        return "Restaurant Service accessed";
    }





}
*/
