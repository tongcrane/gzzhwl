<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/listTable.css"/>

<div class="handle">
	<div class="handle-btn">
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;车牌号 / 提货单号 / 司机姓名 / 司机手机号" />
		<span class="advance-sea">高级搜索</span>
		<span id="btn_search"  class="title_search"></span>
		<div class="search-content"></div>
	</div>
</div>

<div class="handle-content">
	<table>
		<thead>
			<tr>
				<th width="185">提货单号</th>
				<th width="140">车牌号</th>
				<th width="115">司机姓名</th>
				<th width="150">司机手机</th>
				<th>运输线路</th>
				<th width="100" class="bus_sel_single">
					发车状态
					<ul class="statu_s trip_status">
						<li value="">全部</li>
						<li value="01">待车检</li>
						<li value="02">已车检</li>
						<li value="03">已靠台</li>
					</ul>
				</th>
				<th width="130"  class="bus_select sortBtn">到达时间</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSTripList.js?v=${sessionId}"></script>