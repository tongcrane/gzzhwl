<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="handle">
	<div class="handle-btn">
		<a href="javascript:void(0);">新增</a>
		<a href="javascript:void(0);" class="del-supplier">删除</a>
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;供应商名称 / 手机号" />
		<span class="advance-sea">高级搜索</span>
		<span id="btn_search"  class="title_search"></span>
		<div class="search-content"></div>
	</div>
</div>

<div class="handle-content">
	<table>
		<thead>
			<tr>
				<th width="46"><span class="check-box all-check"></span></th>
				<th>供应商全称</th>
				<th width="165">手机</th>
				<th width="112">类型</th>
				<th width="122">结算方式</th>
				<th width="110">开户名</th>
				<th width="144">开户行</th>
				<th width="152">银行账号</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/information/supplyInfoList.js?v=${sessionId}"></script>