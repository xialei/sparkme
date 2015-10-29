package com.aug3.test.sparkme;

import java.util.HashMap;
import java.util.Map;

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
	private static final String MYSQL_USERNAME = "ada_user";
	private static final String MYSQL_PWD = "ada_user";
	private static final String MYSQL_CONNECTION_URL = "jdbc:mysql://192.168.250.208:3306/ada-fd?user="
			+ MYSQL_USERNAME + "&password=" + MYSQL_PWD;

	private static final JavaSparkContext sc = new JavaSparkContext(new SparkConf().setAppName("EarningsCalculate")
			.setMaster("local[*]"));

	private static final SQLContext sqlContext = new SQLContext(sc);

	public static void main(String[] args) {

		DataFrame dateDF = sqlContext.read().json("./trade_date.json");

		Row[] dtRows = dateDF.select(new Column("dt")).collect();

		Map<String, String> options = new HashMap<>();
		options.put("driver", MYSQL_DRIVER);
		options.put("url", MYSQL_CONNECTION_URL);
		options.put("dbtable", "(select dt,tick,close from hq_price where dt > '2007') as quotes");
		// options.put("partitionColumn", "id");
		// options.put("lowerBound", "000001");
		// options.put("upperBound", "999999");
		// options.put("numPartitions", "10");
		DataFrame df = sqlContext.read().format("jdbc").options(options).load();

		for (Row r : dtRows) {
			String dt = r.getString(0);

			Row[] secus = df.filter(new Column("dt").$eq$eq$eq(dt)).collect();
			for (Row secu : secus) {
				LOGGER.info(secu);
				break;
			}
			break;
		}

	}
}
