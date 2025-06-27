package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DataSourceConfig.class /* , ServiceConfig.class など */ })
public class RootConfig {
    /* 何も書かなくて OK。@Import で “インフラ層” を束ねるだけ */
}
