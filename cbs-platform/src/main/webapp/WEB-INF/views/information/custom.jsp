<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="handle">
	<div class="handle-btn">
		<a href="javascript:void(0);">新增</a>
		<a href="javascript:void(0);" class="del-customer">删除</a>
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;客户全称 / 联系人姓名 / 手机 / 电话" />
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
				<th>客户全称</th>
				<th width="177">联系人</th>
				<th width="225">手机</th>
				<th width="223" >电话</th>
				<th width="85" class="bus_sel_single short">
					类型
					<ul class="statu_s" >
					</ul>
				</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/information/customInfoList.js?v=${sessionId}"></script>