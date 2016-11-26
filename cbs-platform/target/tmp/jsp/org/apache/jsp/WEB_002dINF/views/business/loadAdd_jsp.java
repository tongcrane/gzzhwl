package org.apache.jsp.WEB_002dINF.views.business;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loadAdd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t<h2 class=\"title\">联系人信息</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">订单号</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"orderNo\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发货人姓名</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"contactName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">提货时间</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"pickUpTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">运单号</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"consignNo\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">始发站</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"startCodePCn,startCodeCCn\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">发车时间</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"needStartTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">货物名称</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"goodsName\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">目的地</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"endCodePCn,endCodeCCn\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">要求到达时间</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"needArriveTime\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">用车要求</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"needType,needLength\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout long\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">接货地址</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"sendRegion,sendAddress\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">是否进口</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"needImportedVehicles\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout long\">\r\n");
      out.write("\t\t\t\t<p class=\"stage\">送货地址</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n order-column\" data-column=\"receiveRegion,receiveAddress\"></span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"section\">\r\n");
      out.write("\t\t<h2 class=\"title\">车次信息</h2>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<p class=\"stage high\">提货单号</p>\r\n");
      out.write("\t\t\t\t<span class=\"spa_n high gray\">系统自动生成</span>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">司机姓名1</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t autocomplete driver driver-column-01\" data-show-column=\"realName\" data-column=\"driver1\" type=\"text\" placeholder=\"请输入\" />\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">结款方式</label>\r\n");
      out.write("\t\t\t\t<select disabled class=\"selec_t multiselect supply-column\" data-show-column=\"paymentType\" data-width=\"232\">\r\n");
      out.write("\t\t\t\t\t<option value=\"\">请选择</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"月结\">月结</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"回单\">回单</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"现付\">现付</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"提付\">提付</option>\r\n");
      out.write("\t\t\t\t\t<option value=\"临欠\">临欠</option>\r\n");
      out.write("\t\t\t\t</select>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">车牌号</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t autocomplete vehicle load-column\" data-show-column=\"plateNumber\" data-column=\"vehicleInfoId\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">司机电话1</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t driver-column-01 driver1\" data-show-column=\"telphone\" readonly type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout m_short\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">纯运费</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t short pright column load-column\" data-show-column=\"freightPrice\" data-column=\"freightPrice\" type=\"text\" placeholder=\"请输入\" />\r\n");
      out.write("\t\t\t\t<div class=\"radio\" data-column=\"isPredict\">\r\n");
      out.write("\t\t\t\t\t<span class=\"isPredict_01\"></span>准确\r\n");
      out.write("\t\t\t\t\t<span class=\"isPredict_02\"></span>预估\r\n");
      out.write("\t\t\t\t</div>\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">车辆属性</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t load-column vehicle-column\" data-show-column=\"attributes\" readonly></select>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">司机姓名2</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t autocomplete driver driver-column-02\" data-show-column=\"realName\" data-column=\"driver2\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout money\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">预付现金</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t pright column load-column\" data-show-column=\"prePay\" data-column=\"prePay\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">车头类型</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t one short load-column vehicle-column column\" readonly data-column=\"headstockType\" data-show-column=\"headstockType\"></select>\r\n");
      out.write("\t\t\t\t<div class=\"radio\" data-column=\"bridgeType\">\r\n");
      out.write("\t\t\t\t\t<span class=\"bridgeType_01\"></span>单桥\r\n");
      out.write("\t\t\t\t\t<span class=\"bridgeType_02\"></span>双桥\r\n");
      out.write("\t\t\t\t</div>\t\t\t\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">司机电话2</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t driver-column-02 driver2\" readonly data-show-column=\"telphone\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout money\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">预付油卡</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t pright column load-column\" data-show-column=\"oilPay\" data-column=\"oilPay\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line clearfix\">\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">挂车号</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t autocomplete hung load-column\" data-show-column=\"loadPlateNumber\" data-column=\"loadInfoId\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">结款对象</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t autocomplete supply supply-column\" data-show-column=\"fullName\" data-column=\"supplyId\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"layout\">\r\n");
      out.write("\t\t\t\t<label class=\"labe_l need\">油卡卡号</label>\r\n");
      out.write("\t\t\t\t<input class=\"inpu_t column load-column\" data-show-column=\"oilCardNo\" data-column=\"oilCardNo\" type=\"text\" placeholder=\"请输入\"/>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"line mar-top clearfix\">\r\n");
      out.write("\t\t\t<label class=\"labe_l\">备注</label>\r\n");
      out.write("\t\t\t<textarea maxlength=\"200\" class=\"column load-column\" data-show-column=\"remark\" data-column=\"remark\"></textarea>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"operation clearfix\">\r\n");
      out.write("\t\t<div class=\"btn fr actions\">\r\n");
      out.write("\t\t\t<!-- <a href=\"javascript:void(0);\">取消配载</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\">提交</a> -->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"action_12 disable\">取消配载单</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"action_15 disable\">取消</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"conserve action_13 disable\">提交</a>\r\n");
      out.write("\t\t\t<a href=\"javascript:void(0);\" class=\"conserve action_14 disable\">提交</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/business/CBSAddLoad.js?v=");
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
