# Config Eclipse/python/spark environment

1. Configure PyDev with the Spark Python sources
From the “Preferences” window :
Go to PyDev > Interpreters > Python Interpreter

 Click on the button [New Folder]
Choose the python folder just under your Spark install directory and validate :
e.g : D:\Progra~1\spark-1.5.0-bin-hadoop2.6\python
Note : This path must be absolute (don’t use the Spark home environment variable)

 Click on the button [New Egg/Zip(s)]
From the File Explorer, select [*.zip] rather [*.egg]
Choose the file py4j-0.8.2.1-src.zip just under your Spark python folder and validate :
          e.g : D:\Program Files\spark-1.5.0-bin-hadoop2.6\python\lib\py4j-0.8.2.1-src.zip
          
2. Configure PyDev with the Spark Environment variables in order to execute codes on Spark
From the “Preferences” window :
Go to PyDev > Interpreters > Python Interpreter

Click on the central button [Environment]
Click on the button [New...] to add a new Environment variable.
Add the environment variable SPARK_HOME and validate :
e.g 1 : Name: SPARK_HOME, Value: D:\Progra~1\spark-1.5.0-bin-hadoop2.6

# Install sparklingpandas

pip install numpy --upgrade

yum install atlas atlas-devel lapack-devel blas-devel
pip install scipy

pip install sparklingpandas

Successfully installed numpy-1.9.2 scipy-0.16.0 sparklingpandas-0.0.6


# Use spark-submit to run your application
$ YOUR_SPARK_HOME/bin/spark-submit \
  --master local[4] \
  SimpleApp.py

# Pandas
http://pandas.pydata.org/

# ipython run on spark
https://jupyter.org/

pip install ipython

ipython profile create pyspark

vi /root/.ipython/profile_pyspark/startup/00-pyspark-setup.py

	import os
	import sys
 
	# Configure the environment
	if 'SPARK_HOME' not in os.environ:
	    os.environ['SPARK_HOME'] = '/usr/local/spark'
	 
	# Create a variable for our root path
	SPARK_HOME = os.environ['SPARK_HOME']
	 
	# Add the PySpark/py4j to the Python Path
	sys.path.insert(0, os.path.join(SPARK_HOME, "python", "build"))
	sys.path.insert(0, os.path.join(SPARK_HOME, "python")) 

ipython --profile pyspark

	In [1]: from pyspark import SparkContext
	In [2]: sc = SparkContext('local', 'pyspark')
	In [3]: def isprime(n):
	   ...:     n= abs(int(n))
	   ...:     if n<2:
	   ...:         return False
	   ...:     if n==2:
	   ...:         return True
	   ...:     if not n & 1:
	   ...:         return False
	   ...:     for x in range(3, int(n**0.5)+1, 2):
	   ...:         if n % x ==0:
	   ...:             return False
	   ...:     return True
	   ...: nums= sc.parallelize(xrange(1000000))
	   ...: print nums.filter(isprime).count() 

$ IPYTHON_OPTS=”notebook –pylab inline” pyspark
  
