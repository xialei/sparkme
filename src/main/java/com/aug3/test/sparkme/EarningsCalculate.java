package com.aug3.test.sparkme;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class EarningsCalculate {

	private static final Logger LOGGER = Logger.getLogger(EarningsCalculate.class);

	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_USER = "ada_user";
	private static final String MYSQL_PWD = "ada_user";
	private static final String MYSQL_CONNECTION_URL = "jdbc:mysql://192.168.250.208:3306/ada-fd";
	private static final String MYSQL_TABLE = "(select dt,tick,close from hq_price where dt > '2007') as quotes";

	private static final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("EarningsCalculate")
			.setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {

		DataFrame dateDF = sqlContext.read().json("./trade_date.json");

		Row[] dtRows = dateDF.select(new Column("dt")).collect();

		Properties props = new Properties();
		props.put("driver", MYSQL_DRIVER);
		props.put("user", MYSQL_USER);
		props.put("password", MYSQL_PWD);

		sqlContext.read().jdbc(MYSQL_CONNECTION_URL, MYSQL_TABLE, props).registerTempTable("hq");
		;

		for (Row r : dtRows) {
			String dt = r.getString(0);
			
			Row[] secus = sqlContext.sql("select dt,tick,close from hq where dt='" + dt + "'").collect();
			for (Row secu : secus) {
				LOGGER.info(secu);
				break;
			}
			break;
		}

	}
}
