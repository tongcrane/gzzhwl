package org.apache.jsp.WEB_002dINF.views.business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class orderInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<div class=\"banner\"></div>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("\t<div class=\"section info\">\r\n");
      out.write("\t\t<h2 class=\"title\">客户合同</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">订单号</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n detailInfo-column\" data-column=\"orderNo\" ></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">用车要求</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"needLength,needType\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">线路属性</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"lineAttribute\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">客户单号</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"customerBillNo\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">订单类型</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"orderType\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">始发地</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n lineInfo-column\" data-column=\"startCodePCn,startCodeCCn\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">客户全称</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-column\" data-column=\"customerName\" ></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">是否允许外请</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"needOwnVehicles\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">目的地</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n lineInfo-column\" data-column=\"endCodePCn,endCodeCCn\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">客户合同</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n agreementName\" ></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">是否进口</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"needImportedVehicles\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"section info\">\r\n");
      out.write("\t\t<h2 class=\"title\">发货人信息</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发货人公司</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-column\" data-column=\"customerName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout long\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">货场地址</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-addr\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发货联系人</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-column\" data-column=\"contactName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发货人手机</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-column\" data-column=\"mobile\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发货人电话</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n senderInfo-column\" data-column=\"telphone\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"section info\">\r\n");
      out.write("\t\t<h2 class=\"title\">收货人信息</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">收货人公司</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n receiverInfo-column\" data-column=\"customerName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout long\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">收货人地址</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n receiverInfo-addr\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">收货联系人</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n receiverInfo-column\" data-column=\"contactName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">收货人手机</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n receiverInfo-column\" data-column=\"mobile\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">收货人电话</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n receiverInfo-column\" data-column=\"telphone\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"section info\">\r\n");
      out.write("\t\t<h2 class=\"title\">货物及时效</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">货物品名</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"goodsName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">单价</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"unitPrice\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">计价方式</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n chargeInfo-column\" data-column=\"name\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">提货时间</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"pickUpTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">公里数</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"miles\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">结算方式</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"paymentType\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">计划发车</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"needStartTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">预估体积</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"goodsVolume\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">回单要求</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"orderAdvice\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">到达时限</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"needArriveTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">预估重量</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"goodsWeight\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">是否已开单</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n hasConsign\" ></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<p class=\"stage\">备注</p>\r\n");
      out.write("\t\t\t<span class=\"spa_n baseInfo-column\" data-column=\"remark\"></span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"operation clearfix\">\r\n");
      out.write("\t\t<div class=\"record fl\">\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\">操作纪录</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"btn fr actions\">\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"load_btn action_05 disable\">约车</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"cancel_btn action_03 disable\">取消订单</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"close_btn action_08 disable\">关闭订单</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"edit_btn action_02 disable\">修改</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"consign_btn action_99 disable\">生成运单</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"apply_return_btn action_98 disable\">申请退回</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"push_btn action_07 disable\">推送运势界</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/CBSOrderPush.js?v=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/CBSOrderInfo.js?v=");
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
