package com.jdc.mkt.inter;

public interface PrintTableByQueryInt extends PrintTableByResultSetInt{

	void printResultSetAsTable(String query);
	void updateQueryAsTable(String query);
}
