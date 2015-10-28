package com.aug3.test.sparkme;

import java.util.HashMap;
import java.util.List;
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

		// Datasource options
		Map<String, String> options = new HashMap<>();
		options.put("driver", MYSQL_DRIVER);
		options.put("url", MYSQL_CONNECTION_URL);
		// options.put("dbtable",
		// "(select dt,tick,close from hq_price where dt = '2015-10-23') as quotes");
		// options.put("partitionColumn", "id");
		// options.put("lowerBound", "000001");
		// options.put("upperBound", "999999");
		// options.put("numPartitions", "10");

		DataFrame dateDF = sqlContext.read().json("trade_date.json");

		dateDF.select(new Column("dt"));

		DataFrame df = sqlContext.read().format("jdbc").options(options).load();

		// Print the schema in a tree format
		// df.printSchema();

		// List<Row> hqPriceList =
		// df.filter(df.col("dt").$eq$eq$eq("2015-10-23")).collectAsList();

		List<Row> hqPriceList = df.collectAsList();

		LOGGER.info(hqPriceList.size());

		for (Row pr : hqPriceList) {

			LOGGER.info(pr);
			break;

		}

	}
}
