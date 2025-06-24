package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        // INFOレベルのログを出力
        log.info("'/hello' エンドポイントが呼び出されました。");
        model.addAttribute("message", "Thymeleaf!");
        return "hello";  // ← hello.html に対応
    }
}
