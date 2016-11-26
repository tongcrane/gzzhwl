<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="banner"></div>
<div class="container">
	<div class="section info">
		<h2 class="title">联系人信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">订单号</p>
				<span class="spa_n order-column" data-column="orderNo"></span>
			</div>
			<div class="layout">
				<p class="stage">发货人姓名</p>
				<span class="spa_n order-column" data-column="contactName"></span>
			</div>
			<div class="layout">
				<p class="stage">提货时间</p>
				<span class="spa_n order-column" data-column="pickUpTime"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">运单号</p>
				<span class="spa_n order-column" data-column="consignNo"></span>
			</div>
			<div class="layout">
				<p class="stage">始发站</p>
				<span class="spa_n order-column" data-column="startCodePCn,startCodeCCn"></span>
			</div>
			<div class="layout">
				<p class="stage">发车时间</p>
				<span class="spa_n order-column" data-column="needStartTime"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">货物名称</p>
				<span class="spa_n order-column" data-column="goodsName"></span>
			</div>
			<div class="layout">
				<p class="stage">目的地</p>
				<span class="spa_n order-column" data-column="endCodePCn,endCodeCCn"></span>
			</div>
			<div class="layout">
				<p class="stage">要求到达时间</p>
				<span class="spa_n order-column" data-column="needArriveTime"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">用车要求</p>
				<span class="spa_n order-column" data-column="needType,needLength"></span>
			</div>
			<div class="layout long">
				<p class="stage">接货地址</p>
				<span class="spa_n order-column" data-column="sendRegion,sendAddress"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage">是否进口</p>
				<span class="spa_n order-column" data-column="needImportedVehicles"></span>
			</div>
			<div class="layout long">
				<p class="stage">送货地址</p>
				<span class="spa_n order-column" data-column="receiveRegion,receiveAddress"></span>
			</div>
		</div>
	</div>
	<div class="section">
		<h2 class="title">车次信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage high">提货单号</p>
				<span class="spa_n high gray">系统自动生成</span>
			</div>
			<div class="layout">
				<label class="labe_l need">司机姓名1</label>
				<input class="inpu_t autocomplete driver driver-column-01" data-show-column="realName" data-column="driver1" type="text" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">结款方式</label>
				<select disabled class="selec_t multiselect supply-column" data-show-column="paymentType" data-width="232">
					<option value="">请选择</option>
					<option value="月结">月结</option>
					<option value="回单">回单</option>
					<option value="现付">现付</option>
					<option value="提付">提付</option>
					<option value="临欠">临欠</option>
				</select>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">车牌号</label>
				<input class="inpu_t autocomplete vehicle load-column" data-show-column="plateNumber" data-column="vehicleInfoId" type="text" placeholder="请输入"/>
			</div>
			<div class="layout">
				<label class="labe_l need">司机电话1</label>
				<input class="inpu_t driver-column-01 driver1" data-show-column="telphone" readonly type="text" placeholder="请输入"/>
			</div>
			<div class="layout m_short">
				<label class="labe_l need">纯运费</label>
				<input class="inpu_t short pright column load-column" data-show-column="freightPrice" data-column="freightPrice" type="text" placeholder="请输入" />
				<div class="radio" data-column="isPredict">
					<span class="isPredict_01"></span>准确
					<span class="isPredict_02"></span>预估
				</div>			
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">车辆属性</label>
				<input class="inpu_t load-column vehicle-column" data-show-column="attributes" readonly></select>
			</div>
			<div class="layout">
				<label class="labe_l need">司机姓名2</label>
				<input class="inpu_t autocomplete driver driver-column-02" data-show-column="realName" data-column="driver2" type="text" placeholder="请输入"/>
			</div>
			<div class="layout money">
				<label class="labe_l need">预付现金</label>
				<input class="inpu_t pright column load-column" data-show-column="prePay" data-column="prePay" type="text" placeholder="请输入"/>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">车头类型</label>
				<input class="inpu_t one short load-column vehicle-column column" readonly data-column="headstockType" data-show-column="headstockType"></select>
				<div class="radio" data-column="bridgeType">
					<span class="bridgeType_01"></span>单桥
					<span class="bridgeType_02"></span>双桥
				</div>			
			</div>
			<div class="layout">
				<label class="labe_l need">司机电话2</label>
				<input class="inpu_t driver-column-02 driver2" readonly data-show-column="telphone" type="text" placeholder="请输入"/>
			</div>
			<div class="layout money">
				<label class="labe_l need">预付油卡</label>
				<input class="inpu_t pright column load-column" data-show-column="oilPay" data-column="oilPay" type="text" placeholder="请输入"/>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">挂车号</label>
				<input class="inpu_t autocomplete hung load-column" data-show-column="loadPlateNumber" data-column="loadInfoId" type="text" placeholder="请输入"/>
			</div>
			<div class="layout">
				<label class="labe_l need">结款对象</label>
				<input class="inpu_t autocomplete supply supply-column" data-show-column="fullName" data-column="supplyId" type="text" placeholder="请输入"/>
			</div>
			<div class="layout">
				<label class="labe_l need">油卡卡号</label>
				<input class="inpu_t column load-column" data-show-column="oilCardNo" data-column="oilCardNo" type="text" placeholder="请输入"/>
			</div>
		</div>
		<div class="line mar-top clearfix">
			<label class="labe_l">备注</label>
			<textarea maxlength="200" class="column load-column" data-show-column="remark" data-column="remark"></textarea>
		</div>
	</div>
	<div class="operation clearfix">
		<div class="btn fr actions">
			<!-- <a href="javascript:void(0);">取消配载</a>
			<a href="javascript:void(0);">提交</a> -->
			
			<a href="javascript:void(0);" class="action_12 disable">取消配载单</a>
			<a href="javascript:void(0);" class="action_15 disable">取消</a>
			<a href="javascript:void(0);" class="conserve action_13 disable">提交</a>
			<a href="javascript:void(0);" class="conserve action_14 disable">提交</a>
		</div>
	</div>

</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSAddLoad.js?v=${sessionId}"></script>