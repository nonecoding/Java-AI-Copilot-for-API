package org.ai.aicopilotforapi.common.config;

import org.springframework.context.annotation.Bean;                                  // CorsFilter Bean
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;                               // CorsConfiguration 类
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;               // UrlBasedCorsConfigurationSource 类
import org.springframework.web.filter.CorsFilter;                                   // CorsFilter 类
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;       // 拦截器注册
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;          // WebMvcConfigurer 接口

import org.ai.aicopilotforapi.common.interceptor.MyCustomInterceptor;              // 你的自定义拦截器实现

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 全局 CORS 过滤器：支持通配 localhost:517* 端口，并对 Swagger 相关路径也生效
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Spring Boot 2.4+ 支持通配 port
        config.addAllowedOriginPattern("http://localhost:517*");                    // 允许 5171、5172 … 5179
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对所有应用路径生效
        source.registerCorsConfiguration("/**", config);
        // 单独再注册 Swagger UI、资源、API docs 路径，确保它们也被 CORS 过滤
        source.registerCorsConfiguration("/v2/api-docs", config);
        source.registerCorsConfiguration("/swagger-resources/**", config);
        source.registerCorsConfiguration("/swagger-ui/**", config);
        source.registerCorsConfiguration("/webjars/**", config);

        return new CorsFilter(source);                                                // :contentReference[oaicite:0]{index=0}
    }

    /**
     * 注册自定义拦截器并排除 Swagger 相关路径，避免拦截到接口文档
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyCustomInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/webjars/**"
                );                                                                      // :contentReference[oaicite:1]{index=1}
    }
}
