package formDesigner.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        //指定允许其他域名访问
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        // 支持的头信息
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            httpServletResponse.getWriter().println("ok");
            return;
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
