package com.gzzhwl.core.constant;

/**
 * 全局数据状态码定义
 * 
 * @author anycrane
 *
 */
public enum Global {
	/**
	 * is_del 正常
	 */
	ISDEL_NORMAL("00") {
		@Override
		public String getName() {
			return "正常";
		}
	},
	/**
	 * is_del 已删除
	 */
	ISDEL_DELETE("01") {
		@Override
		public String getName() {
			return "删除";
		}
	},
	/**
	 * status 状态正常
	 */
	STATUS_NORMAL("00") {
		@Override
		public String getName() {
			return "正常";
		}
	},
	/**
	 * status 状态待审核
	 */
	STATUS_WAIT("01") {
		@Override
		public String getName() {
			return "待审核";
		}
	},
	/**
	 * status 审核未通过
	 */
	STATUS_NOT_PASSED("02") {
		@Override
		public String getName() {
			return "审核未通过";
		}
	},
	/**
	 * status 审核通过 与 状态正常值一样
	 */
	STATUS_PASSED("00") {
		@Override
		public String getName() {
			return "审核通过";
		}
	},
	/**
	 * status 未完成录入
	 */
	STATUS_ENTRY_NOT_FINISHED("99") {
		@Override
		public String getName() {
			return "未完成录入";
		}
	},
	/**
	 * 账号冻结
	 */
	STATUS_FROZEN("04") {
		@Override
		public String getName() {
			return "账号冻结";
		}
	},
	
	/**
	 * 客户状态-正常
	 */
	CUST_NORMAL("00") {
		@Override
		public String getName() {
			return "客户状态正常";
		}
	},
	
	/**
	 * 客户状态-正常
	 */
	CUST_INVALID("01") {
		@Override
		public String getName() {
			return "客户状态失效";
		}
	},
	
	/**
	 * 合同状态 00-已创建运单合同
	 *
	 */
	STATUS_CREATED("00") {
		@Override
		public String getName() {
			return "已创建运单合同";
		}
	};
	
	private String status;

	Global(String status) {
		this.status = status;
	}

	public abstract String getName();

	@Override
	public String toString() {
		return this.status;
	}
}
