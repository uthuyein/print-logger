package com.jdc.mkt;

import com.jdc.mkt.inter.PrintTableByEntityListInt;
import com.jdc.mkt.inter.PrintTableByQueryInt;
import com.jdc.mkt.inter.PrintTableByResultSetInt;
import com.jdc.mkt.utils.impls.PrintTableByEntityListImpl;

public class Logger extends PrintTableByEntityListImpl implements PrintTableByEntityListInt,PrintTableByQueryInt,PrintTableByResultSetInt {

	private static Logger instance;
	
	private Logger(Class<?> config) {
		super(config);
		
	}
	
	public static Logger getInstance(Class<?> config) {
		if (instance == null) {
			instance = new Logger(config);
		}
		return instance;
	}
}
