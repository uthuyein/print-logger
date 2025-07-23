package com.jdc.mkt.inter;

import java.util.List;

public interface PrintTableByEntityListInt {

	<T>void printEntityTable(List<T> entityList, Class<?> entityName);
}
