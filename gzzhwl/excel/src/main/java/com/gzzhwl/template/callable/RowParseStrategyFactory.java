package com.gzzhwl.template.callable;

public interface RowParseStrategyFactory {
	public RowParseCallable getRowParse(String strategy);
}
