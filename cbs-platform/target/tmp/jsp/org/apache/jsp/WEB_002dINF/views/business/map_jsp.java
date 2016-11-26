package org.apache.jsp.WEB_002dINF.views.business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class map_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/map.css?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("\r\n");
      out.write("<div class=\"banner\">\r\n");
      out.write("\t<div class=\"wrap\">\r\n");
      out.write("\t\t<div class=\"map-search\">\r\n");
      out.write("\t\t\t<select id=\"search_type\" class=\"selec_t\">\r\n");
      out.write("\t\t\t\t<option value=\"01\" data-placeholder=\"请输入车牌号\" selected>车牌号</option>\r\n");
      out.write("\t\t\t\t<option value=\"02\" data-placeholder=\"请输入提货单号\">提货单号</option>\r\n");
      out.write("\t\t\t\t<option value=\"03\" data-placeholder=\"请输入订单号\">订单号</option>\r\n");
      out.write("\t\t\t</select>\r\n");
      out.write("\t\t\t<input type=\"text\" class=\"inpu_t\" id=\"search_input\" placeholder=\"请输入车牌号\" />\r\n");
      out.write("\t\t\t<span class=\"map-icon\"></span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<div class=\"contain\">\r\n");
      out.write("\t<div id=\"map\"><div class=\"current-toolbar\"><div class=\"current-tips\">获取当前位置</div></div></div>\r\n");
      out.write("\t<!--  异常点 -->\r\n");
      out.write("<!-- <div class=\"map-status\">\r\n");
      out.write("\t<span class=\"close\"></span>\r\n");
      out.write("\t<span class=\"map-icon\"></span>\r\n");
      out.write("\t<h3>订单号：<span>123456789</span></h3>\r\n");
      out.write("\t<div class=\"status-cont\">\r\n");
      out.write("\t\t<p class=\"title\">粤A88888</p>\r\n");
      out.write("\t\t<p>行驶速度：120km/h</p>\r\n");
      out.write("\t\t<div class=\"abnormal\">\r\n");
      out.write("\t\t\t<p class=\"reason\">异常类型：<span>堵车</span></p>\r\n");
      out.write("\t\t\t<p>异常开始时间：<span>2016-10-08 12:30</span></p>\r\n");
      out.write("\t\t\t<p>异常结束时间：<span>2016-10-08 12:30</span></p>\r\n");
      out.write("\t\t\t<p>位置：<span>123.3434</span></p>\r\n");
      out.write("\t\t</div>   \r\n");
      out.write("\t</div>\r\n");
      out.write("</div> -->\r\n");
      out.write("\t<!--  正常点 -->\r\n");
      out.write("<!-- \t<div class=\"map-status\">\r\n");
      out.write("\t\t<span class=\"close\"></span>\r\n");
      out.write("\t\t<span class=\"map-icon\"></span>\r\n");
      out.write("\t\t<h3>订单号：<span>123456789</span></h3>\r\n");
      out.write("\t\t<div class=\"status-cont\">\r\n");
      out.write("\t\t\t<p class=\"title\">粤A88888</p>\r\n");
      out.write("\t\t\t<p>行驶速度：120km/h</p>\r\n");
      out.write("\t\t\t<div class=\"normal\">\r\n");
      out.write("\t\t\t\t<p>获取时间：<span>2016-10-08 12:30</span></p>\r\n");
      out.write("\t\t\t\t<p>位置：<span>123.3434</span></p>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write(" -->\t \r\n");
      out.write("\t <!--  起点终点位置 -->\r\n");
      out.write("\t<!-- <div class=\"location\"> \r\n");
      out.write("\t\t<span class=\"close\"></span>\r\n");
      out.write("\t\t<span class=\"location-cont\">浦东软件园浦东软件园浦东软件园浦东软件园浦东软件园浦东软件园</span>\r\n");
      out.write("\t</div> -->\r\n");
      out.write("\t   <!-- 当前位置 -->\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"http://webapi.amap.com/maps?v=1.3&key=6c1b0d3baab9c9a1631d88a86f8eb3d5\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBSMap.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/ViewMap.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
