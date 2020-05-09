
package com.fourfaith.baseManager.filters;

import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    protected FilterConfig filterConfig;

    public LoginFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String enabled = this.filterConfig.getInitParameter("enabled");
        if ("false".equals(enabled)) {
            chain.doFilter(request, response);
        } else {
            if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
                throw new ServletException("OncePerRequestFilter just supports HTTP requests");
            }

            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute(CommonUtil.mSessionLoginUser) != null) {
                chain.doFilter(request, response);
            } else {
                String keywords = this.filterConfig.getInitParameter("keywords");
                String uri = httpRequest.getRequestURI();
                if (!StringUtils.contains(uri, keywords.split(";"))) {
                    chain.doFilter(request, response);
                    return;
                }

                String ignored = this.filterConfig.getInitParameter("ignored");
                if (StringUtils.contains(uri, ignored.split(";"))) {
                    chain.doFilter(request, response);
                    return;
                }

                PrintWriter out = httpResponse.getWriter();
                response.setContentType("text/html");
                String serverName = httpRequest.getContextPath();
                if (serverName != null && !serverName.equals("")) {
                    out.print("top.location.href='" + httpRequest.getContextPath() + "'");
                } else {
                    String rootPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                    out.print("top.location.href='" + rootPath + "'");
                }
            }
        }

    }

    public void destroy() {
        this.filterConfig = null;
    }
}
