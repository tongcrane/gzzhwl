<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="handle">
	<div class="handle-btn">
		<a href="javascript:void(0);">新增</a>
		<a href="javascript:void(0);" class="del-driver">删除</a>
	</div>
	<div class="handle-search">
		<input type="text" id="query_content" class="list-search" placeholder="搜索&nbsp;&nbsp;司机姓名 / 司机属性 / 准驾车型 / 从业资格证类型" />
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
				<th width="188">姓名</th>
				<th width="238">手机</th>
				<th width="174">准驾车型</th>
				<th width="217">司机类型</th>
				<th>从业资格证类型</th>
			</tr>
		</thead>
		<tbody>                                                      

		</tbody>
	</table>
	<div class="handle-page"></div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/information/driverInfoList.js?v=${sessionId}"></script>