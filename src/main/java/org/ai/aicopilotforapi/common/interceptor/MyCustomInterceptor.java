package org.ai.aicopilotforapi.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 自定义拦截器示例：在 Controller 调用前后打印日志
 */
@Component
public class MyCustomInterceptor implements HandlerInterceptor {

    // 进入 Controller 方法前调用
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        System.out.println("[MyCustomInterceptor] Incoming request: " +
                           request.getMethod() + " " +
                           request.getRequestURI());
        // 继续执行后续拦截器及 Controller
        return true;
    }

    // Controller 方法执行后但在渲染视图前调用
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           org.springframework.web.servlet.ModelAndView modelAndView)
                           throws Exception {
        System.out.println("[MyCustomInterceptor] Handler executed.");
    }

    // 完全渲染视图后调用（通常用于资源清理）
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        System.out.println("[MyCustomInterceptor] Request completed.");
    }
}
