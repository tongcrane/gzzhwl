<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="banner"></div>
<div class="container">
	<div class="section">
		<h2 class="title">合同客户</h2>
		<div class="line clearfix">
			<div class="layout">
				<p class="stage high">订单号</p>
				<span class="spa_n high gray">系统自动生成</span>
			</div>
			<div class="layout">
				<label class="labe_l need">用车要求</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="116" data-column="needType" data-agreement="needType">
					<option value="">请选择</option>
					<option value="低栏车">低栏车</option>
					<option value="中栏车">中栏车</option>
					<option value="高栏车">高栏车</option>
					<option value="封闭式货车">封闭式货车</option>
					<option value="厢式货车">厢式货车</option>
					<option value="单桥拖头">单桥拖头</option>
					<option value="双桥拖头">双桥拖头</option>
					<option value="集装箱">集装箱</option>
					<option value="冷藏车">冷藏车</option>
					<option value="高低板车">高低板车</option>
					<option value="栏板车">栏板车</option>
					<option value="平板车">平板车</option>
					<option value="自卸车">自卸车</option>
					<option value="危险品车">危险品车</option>
					<option value="其他车">其他车</option>
				</select>
				<select data-width="116" data-column="needLength"
					class="selec_t baseInfo-column agreement-column"
					data-agreement="needLength">
					<option value="">请选择</option>
					<option value="4.2">4.2米</option>
					<option value="5">5米</option>
					<option value="6.2">6.2米</option>
					<option value="6.8">6.8米</option>
					<option value="7.2">7.2米</option>
					<option value="7.6">7.6米</option>
					<option value="8.5">8.5米</option>
					<option value="9.6">9.6米</option>
					<option value="12.5">12.5米</option>
					<option value="13">13米</option>
					<option value="13.5">13.5米</option>
					<option value="15">15米</option>
					<option value="16">16米</option>
					<option value="17.5">17.5米</option>
					<option value="18.5">18.5米</option>
					<option value="21">21米</option>
					<option value="22">22米</option>
				</select>
			</div>
			<div class="layout">
				<label class="labe_l need">线路属性</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="236" data-column="lineAttribute" data-agreement="lineAttribute">
					<option value="">请选择</option>
					<option value="单边">单边</option>
					<option value="往返">往返</option>
					<option value="闭合">闭合</option>
				</select>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">客户单号</label>
				<input maxlength="20" class="inpu_t baseInfo-column" type="text" data-empty="true" data-column="customerBillNo" placeholder="请输入"/>
			</div>
			<div class="layout">
				<label class="labe_l need">订单类型</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="236" data-column="orderType" data-agreement="carryType">
					<option value="">请选择</option>
					<option value="整车">整车</option>
					<option value="零担">零担</option>
				</select>
			</div>
			<div class="layout">
				<label class="labe_l need">始发地</label>
				<select class="selec_t short lineInfo-column p-select" data-width="116" id="startCodeP" data-column="startCodeP" data-agreement="startCodeP"></select>
				<select class="selec_t short lineInfo-column c-select" data-width="116" id="startCodeC" data-column="startCodeC" data-agreement="startCodeC"></select>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">客户全称</label>
				<select class="selec_t sender-column customer-select" data-width="236" data-column="customerId"></select>
			</div>
			<div class="layout">
				<label class="labe_l need">是否允许外请</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="236" data-column="needOwnVehicles" data-agreement="needOwnVehicles">
					<option value="">请选择</option>
					<option>是</option>
					<option>否</option>
				</select>
			</div>
			<div class="layout">
				<label class="labe_l need">目的地</label>
				<select class="selec_t short lineInfo-column p-select" data-width="116" id="endCodeP" data-column="endCodeP" data-agreement="endCodeP"></select>
				<select class="selec_t short lineInfo-column c-select" data-width="116" id="endCodeC" data-column="endCodeC" data-agreement="endCodeC"></select>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">客户合同</label>
				<select class="selec_t baseInfo-column agreement-select" data-width="236" data-column="agreementId"></select>
			</div>
			<div class="layout">
				<label class="labe_l need">是否进口</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="236" data-column="needImportedVehicles" data-agreement="needImportedVehicles">
					<option value="">请选择</option>
					<option>是</option>
					<option>否</option>
				</select>
			</div>
		</div>
	</div>
	<div class="section">
		<h2 class="title">发货人信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">发货人公司</label>
				<input maxlength="40" class="inpu_t sender-column" type="text" data-column="customerName" readonly placeholder="请输入"/>
			</div>
			<div class="layout long">
				<label class="labe_l need">货场地址</label>
				<select class="selec_t baseInfo-column addr-select" data-width="236" data-column="addrId"></select>
				<span class="address" data-column="addr-address"></span>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">发货联系人</label>
				<input maxlength="20" class="inpu_t sender-column" type="text" data-column="contactName" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">发货人手机</label>
				<input maxlength="11" class="inpu_t sender-column" type="text" data-column="mobile" placeholder="手机 / 电话必填一项" />
			</div>
			<div class="layout">
				<label class="labe_l need">发货人电话</label>
				<input maxlength="20" class="inpu_t sender-column" type="text" data-column="telphone" placeholder="如：020-88888888" />
			</div>
		</div>
	</div>
	<div class="section">
		<h2 class="title">收货人信息</h2>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">收货人公司</label>
				<input maxlength="40" class="inpu_t receiver-column" data-empty="true" data-column="customerName" type="text" placeholder="请输入" />
			</div>
			<div class="layout long pcd-column  receiver-column" data-column="areaCode">
				<label class="labe_l need">收货人地址</label>
				<input maxlength="40" class="inpu_t special p_right in_short receiver-column receiver-column-address" data-column="address" type="text" placeholder="请输入详细地址" />
				<span class="location map_btn"></span>				
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">收货联系人</label>
				<input maxlength="20" class="inpu_t receiver-column" data-column="contactName" type="text" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">收货人手机</label>
				<input maxlength="11" class="inpu_t receiver-column" data-column="mobile" type="text" placeholder="手机 / 电话必填一项" />
			</div>
			<div class="layout">
				<label class="labe_l need">收货人电话</label>
				<input maxlength="20" class="inpu_t receiver-column" data-column="telphone" type="text" placeholder="如：020-88888888" />
			</div>
		</div>
	</div>
	<div class="section">
		<h2 class="title">货物及时效</h2>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">货物名称</label>
				<input maxlength="20" class="inpu_t baseInfo-column" data-column="goodsName" type="text" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">单价</label>
				<input maxlength="7" class="inpu_t short baseInfo-column agreement-column" data-column="unitPrice" data-agreement="unitPrice" type="text" placeholder="请输入" />
				<div class="radio baseInfo-column _baseInfo" data-column="isPredict">
					<span class="isPredict_01"></span>准确
					<span class="isPredict_02"></span>预估
				</div>
			</div>
			<div class="layout">
				<label class="labe_l need">计价方式</label>
				<select class="selec_t baseInfo-column charge-select agreement-column" data-width="236" data-column="chargeId" data-agreement="chargeId"></select>				
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">提货时间</label>
				<input class="inpu_t baseInfo-column fr use-date" data-column="pickUpTime" type="text" placeholder="请输入" />
			</div>
			<div class="layout km">
				<label class="labe_l need">公里数</label>
				<input maxlength="8" class="inpu_t pright baseInfo-column unit_short" data-column="miles" type="text" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">结算方式</label>
				<select class="selec_t baseInfo-column agreement-column" data-width="236" data-column="paymentType" data-agreement="paymentType">
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
				<label class="labe_l need">计划发车</label>
				<input class="inpu_t baseInfo-column use-date" data-column="needStartTime" type="text" placeholder="请输入" />
			</div>
			<div class="layout volume">
				<label class="labe_l need">预估体积</label>
				<input maxlength="8" class="inpu_t pright baseInfo-column unit_short" data-column="goodsVolume" type="text" placeholder="请输入" />
			</div>
			<div class="layout">
				<label class="labe_l need">回单要求</label>
				<select class="selec_t baseInfo-column _baseInfo" data-width="236" data-column="orderAdvice">
					<option value="">请选择</option>
					<option value="客户专签">客户专签</option>
					<option value="公章">公章</option>
					<option value="身份证">身份证</option>
					<option value="收条">收条</option>
				</select>
			</div>
		</div>
		<div class="line clearfix">
			<div class="layout">
				<label class="labe_l need">到达时限</label>
				<input class="inpu_t baseInfo-column use-date" data-column="needArriveTime" type="text" placeholder="请输入" />
			</div>
			<div class="layout weight">
				<label class="labe_l need">预估重量</label>
				<input maxlength="8" class="inpu_t pright baseInfo-column unit_short" data-column="goodsWeight" type="text" placeholder="请输入" />
			</div>
		</div>
		<div class="line mar-top clearfix">
			<label class="labe_l">备注</label>
			<textarea maxlength="400" data-column="remark" class="baseInfo-column"></textarea>
		</div>
	</div>
	<div class="operation clearfix">
		<div class="btn fr">
			<a href="javascript:void(0);" class="cancel">取消</a>
			<a href="javascript:void(0);" class="conserve">提交</a>
		</div>
	</div>

</div>

<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=6c1b0d3baab9c9a1631d88a86f8eb3d5"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/explugin/CBSMap.js?v=${sessionId}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSAddOrder.js?v=${sessionId}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/business/CBSOrderMap.js?v=${sessionId}"></script>