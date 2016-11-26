package org.apache.jsp.WEB_002dINF.views.business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ordermanager_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<div class=\"handle\">\n");
      out.write("\t<div class=\"handle-btn\">\n");
      out.write("\t\t<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/info/business/orderAdd\" target=\"_blank\">添加</a>\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"cancel-order\">取消</a>\n");
      out.write("\t</div>\n");
      out.write("\t<div class=\"handle-search\">\n");
      out.write("\t\t<input type=\"text\" id=\"query_content\" class=\"list-search\" placeholder=\"搜索&nbsp;&nbsp;订单号 / 客户单号 / 客户全称 / 发货联系人手机 / 电话\" />\n");
      out.write("\t\t<span class=\"advance-sea\">高级搜索</span>\n");
      out.write("\t\t<span id=\"btn_search\"  class=\"title_search\"></span>\n");
      out.write("\t\t<div class=\"search-content\"></div>\n");
      out.write("\t</div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"handle-content\">\n");
      out.write("\t<table>\n");
      out.write("\t\t<thead>\n");
      out.write("\t\t\t<tr>\n");
      out.write("\t\t\t\t<th width=\"46\" ><span class=\"check-box all-check\"></span></th>\n");
      out.write("\t\t\t\t<th width=\"150\">订单号</th>\n");
      out.write("\t\t\t\t<th>客户全称</th>\n");
      out.write("\t\t\t\t<th width=\"165\">线路</th>\n");
      out.write("\t\t\t\t<th width=\"100\" >线路属性</th>\n");
      out.write("\t\t\t\t<th width=\"115\" class=\"bus_sel_single\">\n");
      out.write("\t\t\t\t\t订单状态\n");
      out.write("\t\t\t\t\t<ul class=\"statu_s order_status\" >\n");
      out.write("\t\t\t\t\t\t<li value=\"\">全部</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"01\">已受理</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"03\">YSJ审核中</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"05,10,11\">YSJ关闭</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"07\">发布竞标</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"08\">已中标 </li>\n");
      out.write("\t\t\t\t\t\t<li value=\"12\">流标</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</th>\n");
      out.write("\t\t\t\t<th width=\"115\" class=\"bus_sel_single sin_long\">\n");
      out.write("\t\t\t\t\t申请退回状态\n");
      out.write("\t\t\t\t\t<ul class=\"statu_s return_status\">\n");
      out.write("\t\t\t\t\t\t<li value=\"\">全部</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"01\">审批中</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"02\">申请通过</li>\n");
      out.write("\t\t\t\t\t\t<li value=\"03\">申请不通过</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</th>\n");
      out.write("\t\t\t\t<th width=\"140\"  class=\"bus_select sortBtn\">下单时间</th>\n");
      out.write("\t\t\t</tr>\n");
      out.write("\t\t</thead>\n");
      out.write("\t\t<tbody>                                                      \n");
      out.write("\n");
      out.write("\t\t</tbody>\n");
      out.write("\t</table>\n");
      out.write("\t<div class=\"handle-page\"></div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/CBSSourceList.js?v=");
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
