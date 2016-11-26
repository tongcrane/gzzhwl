<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="handle">
	<div class="handle-btn">
		<a href="${ pageContext.request.contextPath}/info/business/orderAdd" target="_blank">添加</a>
		<a href="javascript:void(0);" class="cancel-order">取消</a>
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;订单号 / 客户单号 / 客户全称 / 发货联系人手机 / 电话" />
		<span class="advance-sea">高级搜索</span>
		<span id="btn_search"  class="title_search"></span>
		<div class="search-content"></div>
	</div>
</div>

<div class="handle-content">
	<table>
		<thead>
			<tr>
				<th width="46" ><span class="check-box all-check"></span></th>
				<th width="150">订单号</th>
				<th>客户全称</th>
				<th width="165">线路</th>
				<th width="100" >线路属性</th>
				<th width="115" class="bus_sel_single">
					订单状态
					<ul class="statu_s order_status" >
						<li value="">全部</li>
						<li value="01">已受理</li>
						<li value="03">YSJ审核中</li>
						<li value="05,10,11">YSJ关闭</li>
						<li value="07">发布竞标</li>
						<li value="08">已中标 </li>
						<li value="12">流标</li>
					</ul>
				</th>
				<th width="115" class="bus_sel_single sin_long">
					申请退回状态
					<ul class="statu_s return_status">
						<li value="">全部</li>
						<li value="01">审批中</li>
						<li value="02">申请通过</li>
						<li value="03">申请不通过</li>
					</ul>
				</th>
				<th width="140"  class="bus_select sortBtn">下单时间</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSSourceList.js?v=${sessionId}"></script>