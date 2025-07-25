### print-logger ###
# Usage of PrintLogger Example ###
```
package com.jdc.mkt;

import java.util.List;

import com.jdc.mkt.utils.anno.Connector;

@Connector(name = "taxiDb",user = "taxiUser", password = "taxiPass")
public class Main {
	
	

	public static void main(String[] args) {
        //To get the logger instance, we need to use the Logger class.
		Logger logger = Logger.getInstance(Main.class);
		
		// Print table by query
		logger.printResultSetAsTable("SELECT * FROM addresses_tbl");
		
		// Print table by query with update like create, drop, etc.
		logger.updateQueryAsTable("""
				create table if not exists tst_tbl(
					id int primary key auto_increment,
					name varchar(24) not null
				)
				""");
		
		logger.updateQueryAsTable("drop table tst_tbl");
		
		// Print table by entity list 
		logger.printEntityTable(List.of(
				new Person("aa",22),
				new Person("bb",33)
				), Person.class);
		logger.printResultSetAsTable("SELECT * FROM addresses_tbl");
		
	}
}

//person class for testing
class Person {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
```
