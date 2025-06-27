package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;

import com.example.dao.GreetingDao;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HelloController {
    private final GreetingDao dao;

    public HelloController(GreetingDao dao) {
        this.dao = dao;
    }

    @GetMapping("/dbtest")
    @ResponseBody
    public String dbtest() {
        return dao.findFirst();   // → “Hello from Postgres!”
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        // INFOレベルのログを出力
        log.info("'/hello' エンドポイントが呼び出されました。");
        model.addAttribute("message", "Thymeleaf!");
        return "hello";  // ← hello.html に対応
    }
}
