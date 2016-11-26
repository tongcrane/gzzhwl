package org.apache.jsp.WEB_002dINF.layout.login;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class layout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/common/taglibs.jsp");
    _jspx_dependants.add("/WEB-INF/tld/platform.tld");
    _jspx_dependants.add("/common/resource.jsp");
  }

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

      out.write('\n');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"pragma\" content=\"no-cache\" />\n");
      out.write("\t\t<meta http-equiv=\"cache-control\" content=\"no-cache\" />\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("        <title>登录</title>\n");
      out.write("        ");
      out.write("\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/img/favicon.ico\" rel=\"Shortcut Icon\"/>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\tvar global_ctxPath = '");
      out.print(request.getContextPath());
      out.write("';\n");
      out.write("\tvar global_server = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${server}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("';\n");
      out.write("</script>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/common.css?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/datepicker.min.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/jquery-ui.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/jquery.multiselect.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/jquery.multiselect.filter.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/jquery-ui-extends.css\"/>\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(request.getContextPath());
      out.write("/css/jquery.autocomplete.css\"/>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery-2.1.1.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.metadata.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.cookie.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/store.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.md5.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/moment.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.nicescroll.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery-ui.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/datepicker.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.multiselect.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.multiselect.filter.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/plugin/jquery.autocomplete.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/global.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBSSelect.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBS-PC-Select.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBS-PCD-Select.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBSConfirm.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/explugin/CBSAutocomplete2.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    \t");
      if (_jspx_meth_tiles_insertAttribute_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_insertAttribute_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_insertAttribute_2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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

  private boolean _jspx_meth_tiles_insertAttribute_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_insertAttribute_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.tiles.jsp.taglib.InsertAttributeTag.class) : new org.apache.tiles.jsp.taglib.InsertAttributeTag();
    _jspx_th_tiles_insertAttribute_0.setJspContext(_jspx_page_context);
    _jspx_th_tiles_insertAttribute_0.setName("header");
    _jspx_th_tiles_insertAttribute_0.doTag();
    return false;
  }

  private boolean _jspx_meth_tiles_insertAttribute_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_insertAttribute_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.tiles.jsp.taglib.InsertAttributeTag.class) : new org.apache.tiles.jsp.taglib.InsertAttributeTag();
    _jspx_th_tiles_insertAttribute_1.setJspContext(_jspx_page_context);
    _jspx_th_tiles_insertAttribute_1.setName("body");
    _jspx_th_tiles_insertAttribute_1.doTag();
    return false;
  }

  private boolean _jspx_meth_tiles_insertAttribute_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_insertAttribute_2 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(org.apache.tiles.jsp.taglib.InsertAttributeTag.class) : new org.apache.tiles.jsp.taglib.InsertAttributeTag();
    _jspx_th_tiles_insertAttribute_2.setJspContext(_jspx_page_context);
    _jspx_th_tiles_insertAttribute_2.setName("footer");
    _jspx_th_tiles_insertAttribute_2.doTag();
    return false;
  }
}
