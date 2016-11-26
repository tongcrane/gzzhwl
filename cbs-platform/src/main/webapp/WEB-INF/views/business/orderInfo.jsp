<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="banner"></div>
<div class="container">
	<div class="section info">
		<h2 class="title">客户合同</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">订单号</p>
				<span class="spa_n detailInfo-column" data-column="orderNo" ></span>
			</div>
			<div class="layout">
				<p class="stage">用车要求</p>
				<span class="spa_n baseInfo-column" data-column="needLength,needType"></span>
			</div>
			<div class="layout">
				<p class="stage">线路属性</p>
				<span class="spa_n baseInfo-column" data-column="lineAttribute"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">客户单号</p>
				<span class="spa_n baseInfo-column" data-column="customerBillNo"></span>
			</div>
			<div class="layout">
				<p class="stage">订单类型</p>
				<span class="spa_n baseInfo-column" data-column="orderType"></span>
			</div>
			<div class="layout">
				<p class="stage">始发地</p>
				<span class="spa_n lineInfo-column" data-column="startCodePCn,startCodeCCn"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">客户全称</p>
				<span class="spa_n senderInfo-column" data-column="customerName" ></span>
			</div>
			<div class="layout">
				<p class="stage">是否允许外请</p>
				<span class="spa_n baseInfo-column" data-column="needOwnVehicles"></span>
			</div>
			<div class="layout">
				<p class="stage">目的地</p>
				<span class="spa_n lineInfo-column" data-column="endCodePCn,endCodeCCn"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">客户合同</p>
				<span class="spa_n agreementName" ></span>
			</div>
			<div class="layout">
				<p class="stage">是否进口</p>
				<span class="spa_n baseInfo-column" data-column="needImportedVehicles"></span>
			</div>
		</div>
	</div>
	<div class="section info">
		<h2 class="title">发货人信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">发货人公司</p>
				<span class="spa_n senderInfo-column" data-column="customerName"></span>
			</div>
			<div class="layout long">
				<p class="stage">货场地址</p>
				<span class="spa_n senderInfo-addr"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">发货联系人</p>
				<span class="spa_n senderInfo-column" data-column="contactName"></span>
			</div>
			<div class="layout">
				<p class="stage">发货人手机</p>
				<span class="spa_n senderInfo-column" data-column="mobile"></span>
			</div>
			<div class="layout">
				<p class="stage">发货人电话</p>
				<span class="spa_n senderInfo-column" data-column="telphone"></span>
			</div>
		</div>
	</div>
	<div class="section info">
		<h2 class="title">收货人信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">收货人公司</p>
				<span class="spa_n receiverInfo-column" data-column="customerName"></span>
			</div>
			<div class="layout long">
				<p class="stage">收货人地址</p>
				<span class="spa_n receiverInfo-addr"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">收货联系人</p>
				<span class="spa_n receiverInfo-column" data-column="contactName"></span>
			</div>
			<div class="layout">
				<p class="stage">收货人手机</p>
				<span class="spa_n receiverInfo-column" data-column="mobile"></span>
			</div>
			<div class="layout">
				<p class="stage">收货人电话</p>
				<span class="spa_n receiverInfo-column" data-column="telphone"></span>
			</div>
		</div>
	</div>
	<div class="section info">
		<h2 class="title">货物及时效</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">货物品名</p>
				<span class="spa_n baseInfo-column" data-column="goodsName"></span>
			</div>
			<div class="layout">
				<p class="stage">单价</p>
				<span class="spa_n baseInfo-column" data-column="unitPrice"></span>
			</div>
			<div class="layout">
				<p class="stage">计价方式</p>
				<span class="spa_n chargeInfo-column" data-column="name"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">提货时间</p>
				<span class="spa_n baseInfo-column" data-column="pickUpTime"></span>
			</div>
			<div class="layout">
				<p class="stage">公里数</p>
				<span class="spa_n baseInfo-column" data-column="miles"></span>
			</div>
			<div class="layout">
				<p class="stage">结算方式</p>
				<span class="spa_n baseInfo-column" data-column="paymentType"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">计划发车</p>
				<span class="spa_n baseInfo-column" data-column="needStartTime"></span>
			</div>
			<div class="layout">
				<p class="stage">预估体积</p>
				<span class="spa_n baseInfo-column" data-column="goodsVolume"></span>
			</div>
			<div class="layout">
				<p class="stage">回单要求</p>
				<span class="spa_n baseInfo-column" data-column="orderAdvice"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">到达时限</p>
				<span class="spa_n baseInfo-column" data-column="needArriveTime"></span>
			</div>
			<div class="layout">
				<p class="stage">预估重量</p>
				<span class="spa_n baseInfo-column" data-column="goodsWeight"></span>
			</div>
			<div class="layout">
				<p class="stage">是否已开单</p>
				<span class="spa_n hasConsign" ></span>
			</div>
		</div>
		<div class="line clearfix">
			<p class="stage">备注</p>
			<span class="spa_n baseInfo-column" data-column="remark"></span>
		</div>
	</div>

	<div class="operation clearfix">
		<div class="record fl">
			<a href="javascript:void(0);">操作纪录</a>
		</div>
		<div class="btn fr actions">
			<a href="javascript:void(0);" class="load_btn action_05 disable">约车</a>
			<a href="javascript:void(0);" class="cancel_btn action_03 disable">取消订单</a>
			<a href="javascript:void(0);" class="close_btn action_08 disable">关闭订单</a>
			<a href="javascript:void(0);" class="edit_btn action_02 disable">修改</a>
			<a href="javascript:void(0);" class="consign_btn action_99 disable">生成运单</a>
			<a href="javascript:void(0);" class="apply_return_btn action_98 disable">申请退回</a>
			<a href="javascript:void(0);" class="push_btn action_07 disable">推送运势界</a>
		</div>
	</div>

</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSOrderPush.js?v=${sessionId}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSOrderInfo.js?v=${sessionId}"></script>