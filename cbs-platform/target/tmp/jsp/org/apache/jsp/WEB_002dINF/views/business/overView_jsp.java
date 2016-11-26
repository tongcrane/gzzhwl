package org.apache.jsp.WEB_002dINF.views.business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class overView_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/css/listTable.css\"/>\r\n");
      out.write("\r\n");
      out.write("<div class=\"handle\">\r\n");
      out.write("\t<div class=\"handle-btn\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"handle-search\">\r\n");
      out.write("\t\t<input type=\"text\" id=\"query_content\" class=\"list-search\" placeholder=\"搜索&nbsp;&nbsp;订单号 / 客户单号 / 运单号 / 提货单号 / 客户全称 /车牌号\" />\r\n");
      out.write("\t\t<span class=\"advance-sea\">高级搜索</span>\r\n");
      out.write("\t\t<span id=\"btn_search\"  class=\"title_search\"></span>\r\n");
      out.write("\t\t<div class=\"search-content\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<div class=\"handle-content\">\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<thead>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th width=\"150\">订单号</th>\r\n");
      out.write("\t\t\t\t<th>客户全称</th>\r\n");
      out.write("\t\t\t\t<th width=\"155\">运单号</th>\r\n");
      out.write("\t\t\t\t<th width=\"160\">提货单号</th>\r\n");
      out.write("\t\t\t\t<th width=\"110\">车牌号</th>\r\n");
      out.write("\t\t\t\t<th width=\"120\"  class=\"bus_sel_single\">单据状态</th>\r\n");
      out.write("\t\t\t\t<th width=\"130\"  class=\"bus_select long\">订单创建时间</th>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</thead>\r\n");
      out.write("\t\t<tbody>                                                      \r\n");
      out.write("\r\n");
      out.write("\t\t</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div class=\"handle-page\"></div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/CBSOverViewList.js?v=");
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
