package com.jdc.mkt.utils;

import java.util.List;

public interface PrintLoggerJpa {

	<T>void printEntityTable(List<T> entityList, Class<?> entityName);
}
