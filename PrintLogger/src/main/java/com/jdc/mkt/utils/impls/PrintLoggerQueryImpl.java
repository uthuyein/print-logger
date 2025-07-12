package com.jdc.mkt.utils.impls;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import com.jdc.mkt.utils.PrintLoggerQuery;

public class PrintLoggerQueryImpl extends PrintLoggerTableImpl implements PrintLoggerQuery {

	/**
	 * @author MKT
	 * @param query
	 * @apiNote will show result table look like sql tables from console .
	 */@Override
	public void printResultSetAsTable(String query) {
		String[] starray = query.split(" ");
		System.out.println("\n     ******** Table: " + starray[starray.length - 1] + " *******");

		try (Statement st = ConnectionManager.getConnectionManager().getConnection().prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)) {

			ResultSet rs = st.executeQuery(query);
			printResultSetAsTable(rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
	  * @author MKT
	  * @param resultSet 
	  * @apiNote will show result table look like sql tables from console .
	  */@Override
	public void printResultSetAsTable(ResultSet rs) {

		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			int[] columnWidths = new int[columnCount];
			String[] columnNames = new String[columnCount];

			for (int i = 0; i < columnCount; i++) {
				columnNames[i] = metaData.getColumnName(i + 1);
				columnWidths[i] = columnNames[i].length();
			}

			rs.beforeFirst(); 
			while (rs.next()) {
				for (int i = 0; i < columnCount; i++) {
					Object value = rs.getObject(i + 1);
					if (value != null) {
						columnWidths[i] = Math.max(columnWidths[i], value.toString().length());
					}
				}
			}

			printLine(columnWidths);
			printRow(columnNames, columnWidths);
			printLine(columnWidths);

			rs.beforeFirst();
			while (rs.next()) {
				String[] row = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					Object value = rs.getObject(i + 1);
					row[i] = (value != null) ? value.toString() : "null";
				}
				printRow(row, columnWidths);
			}

			printLine(columnWidths);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private  void printLine(int[] widths) {
		for (int width : widths) {
			System.out.print("+");
			System.out.print("-".repeat(width + 2));
		}
		System.out.println("+");
	}

	private  void printRow(String[] values, int[] widths) {
		for (int i = 0; i < values.length; i++) {
			System.out.printf("| %-" + widths[i] + "s ", values[i]);
		}
		System.out.println("|");
	}

}
