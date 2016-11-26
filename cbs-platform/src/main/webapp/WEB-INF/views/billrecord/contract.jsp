<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/listTable.css"/>

<div class="handle">
	<div class="handle-btn">
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;司机合同号 / 提货单号 / 车牌号" />
		<span class="advance-sea">高级搜索</span>
		<span id="btn_search"  class="title_search"></span>
		<div class="search-content"></div>
	</div>
</div>

<div class="handle-content">
	<table>
		<thead>
			<tr>
				<th width="188">司机合同单号</th>
				<th width="132">车牌号</th>
				<th>运输线路</th>
				<th width="127">总运费(元)</th>
				<th width="170">订单号</th>
				<th width="113"  class="bus_sel_single">单据状态</th>
				<th width="147"  class="bus_select longs">货主合同生成时间</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/billrecord/driverContract.js?v=${sessionId}"></script>