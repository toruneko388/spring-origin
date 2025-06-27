package com.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ─ Root ＝  DB / Service などインフラ層（RootConfig）
 * ─ Web  ＝  MVC / Thymeleaf / Controller（WebMvcConfig）
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /** Root ApplicationContext（インフラ層） */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /** Servlet ApplicationContext（Web 層） */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebMvcConfig.class };
    }

    /** DispatcherServlet の URL マッピング */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };        // ルート配下すべて
    }
}
