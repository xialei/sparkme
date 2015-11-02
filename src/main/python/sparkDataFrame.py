from pyspark.sql.functions import rand, randn, mean, min, max
from pyspark.sql.context import SQLContext
from pyspark.context import SparkConf, SparkContext

conf = SparkConf().setMaster("local").setAppName("sparkDataFrame")
sc = SparkContext(conf = conf)
sqlcontext = SQLContext(sc)

# 1. Create a DataFrame with one int column and 10 rows.
df = sqlcontext.range(0, 10)
df.show()

# Generate two other columns using uniform distribution and normal distribution.
df.select("id", rand(seed=10).alias("uniform"), randn(seed=27).alias("normal"))
df.show()

# 2. Summary and Descriptive Statistics
df = sqlcontext.range(0, 10).withColumn('uniform', rand(seed=10)).withColumn('normal', randn(seed=27))
df.describe('uniform', 'normal').show()

df.select([mean('uniform'), min('uniform'), max('uniform')]).show()

# 3. Sample covariance and correlation
# Covariance is a measure of how two variables change with respect to each other. 
# A positive number would mean that there is a tendency that as one variable increases, 
# the other increases as well. 
# A negative number would mean that as one variable increases, 
# the other variable has a tendency to decrease.
df = sqlcontext.range(0, 10).withColumn('rand1', rand(seed=10)).withColumn('rand2', rand(seed=27))
df.stat.cov('rand1', 'rand2')
df.stat.cov('id', 'id')

# Correlation is a normalized measure of covariance that is easier to understand, 
# as it provides quantitative measurements of the statistical dependence between two random variables.
df.stat.corr('rand1', 'rand2')
df.stat.corr('id', 'id')

# 4. Cross-tabulation is a powerful tool in statistics that is used to 
# observe the statistical significance (or independence) of variables.

# Create a DataFrame with two columns (name, item)
names = ["Alice", "Bob", "Mike"]
items = ["milk", "bread", "butter", "apples", "oranges"]
df = sqlcontext.createDataFrame([(names[i % 3], items[i % 5]) for i in range(100)], ["name", "item"])

df.show()
df.stat.crosstab("name", "item").show()



