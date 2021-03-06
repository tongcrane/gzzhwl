<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/listTable.css"/>

<div class="handle">
	<div class="handle-btn">
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;车牌号 / 订单号 / 提货单号" />
		<span class="advance-sea">高级搜索</span>
		<span id="btn_search"  class="title_search"></span>
		<div class="search-content"></div>
	</div>
</div>

<div class="handle-content">
	<table>
		<thead>
			<tr>
				<th width="158">订单号</th>
				<th width="142">提货单号</th>
				<th width="98">车牌号</th>
				<th>客户全称</th>
				<th width="130">运输线路</th>
				<th width="144"  class="bus_select long elec_time_sort">电子回单时间</th>
				<th width="146"  class="bus_select long print_time_sort">纸质回单时间</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/billrecord/receiptList.js?v=${sessionId}"></script>