package com.jdc.mkt.utils;

import java.sql.ResultSet;

public interface PrintLoggerQuery {

	void printResultSetAsTable(String query);
	void printResultSetAsTable(ResultSet rs);
}
