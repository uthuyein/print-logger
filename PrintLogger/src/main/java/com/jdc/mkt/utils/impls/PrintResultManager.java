package com.jdc.mkt.utils.impls;

import com.jdc.mkt.utils.PrintLoggerJpa;
import com.jdc.mkt.utils.PrintLoggerQuery;
import com.jdc.mkt.utils.PrintLoggerTable;

public final class PrintResultManager extends PrintLoggerQueryImpl implements PrintLoggerJpa,PrintLoggerTable,PrintLoggerQuery {

	private static PrintResultManager jdbc;
	
	private PrintResultManager() {}
	
	/**
	 * <code>
	 * 	
	 * ConnectionManager.setConnection("jdbc:mysql://localhost:3306/testDb", "root", "admin");
		
		var manager = PrintResultManager.getPrinter();
		
		
		manager.printResultSetAsTable("select * from product");
		
		manager.manipulateTables("""
				create table if not exists school(
				id int primary key auto_increment,
				name varchar(45) not null
				)
				""");
	
		manager.manipulateTables("drop table school");
		
		manager.printEntityTable(List.of(
				new Human(1, "aa", "aa.gmail.com"),
				new Human(4, "dd", "dd.gmail.com")
				), Human.class);
	}
	<code>
	 * 
	 */

	public static synchronized PrintResultManager getPrinter() {
		if (null == jdbc) {
			return new PrintResultManager();
		}
		return jdbc;
	}

}
