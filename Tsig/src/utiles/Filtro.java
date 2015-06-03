package utiles;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import presentacion.UsuarioMB;

@WebFilter("/views/*")
public class Filtro implements Filter {

	public Filtro() {
		
	}

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		resp.setHeader("Cache-Control", "no-cache");

		UsuarioMB loginBean = (UsuarioMB) req.getSession().getAttribute("usuarioMB");	

		if (loginBean != null && loginBean.isLog()) {
			// User is logged in, so just continue request.
			chain.doFilter(request, response);
		} else {
			// User is not logged in, so redirect to index.			
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}