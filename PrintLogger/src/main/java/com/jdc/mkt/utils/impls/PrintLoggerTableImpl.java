package com.jdc.mkt.utils.impls;

import java.sql.Statement;

import com.jdc.mkt.utils.PrintLoggerTable;

public class PrintLoggerTableImpl extends PrintLoggerJpaImpl implements PrintLoggerTable {

	/**
	 * @author MKT
	 * @param String query 
	 * @apiNote It is used for table create and drop.
	 */@Override
	public void manipulateTables(String sql) {
		try (Statement st = ConnectionManager.getConnectionManager().getConnection().createStatement()) {

			st.executeUpdate(sql);
			String[] starray = sql.split(" ");
			showResult(starray);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showResult(String[] starray) {

		System.out.println("\n   =============   " + starray[0].toUpperCase() + " TABLE   ==========");

		if (starray[0].equalsIgnoreCase("drop")) {
			System.out.println("\n     ******** Table: " + starray[starray.length - 1].toUpperCase() + " *******");
		
		} else {
			for (int i = 0; i < starray.length; i++) {
				if (starray[i].equalsIgnoreCase("exists")) {
					System.out.println("\n     ******** Table: " + starray[i + 1].toUpperCase() + " *******");
					break;
				}
			}

		}
	}
}
