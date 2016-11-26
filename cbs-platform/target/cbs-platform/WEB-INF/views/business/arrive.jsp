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
				<th width="190">订单号</th>
				<th width="125">车牌号</th>
				<th width="100">司机姓名</th>
				<th width="150">司机手机</th>
				<th>运输线路</th>
				<th width="150"  class="bus_sel_single">到达状态</th>
				<th width="130"  class="bus_select long">实际到达时间</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/CBSArriveList.js"></script>