<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/load.css"/>

<div class="handle">
	<div class="handle-btn">
		<a href="javascript:void(0);" class="do-trip">发车</a>
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;提货单号 / 订单号 / 车牌号" />
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
				<th width="170">提货单号</th>
				<th width="115">车牌号</th>
				<th width="160">订单号</th>
				<th>线路</th>
				<th width="120" >线路纯运费(元)</th>
				<th width="115" class="bus_sel_single">
					提货单状态
					<ul class="statu_s load_status" >
						<li value="">全部</li>
						<li value="04">待配载</li>
						<li value="13">已配载</li>
					</ul>
				</th>
				<th width="140"  class="bus_select sortBtn">创建时间</th>
			</tr>
		</thead>
		<tbody>                                                      
			
		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSLoadList.js?v=${sessionId}"></script>
 