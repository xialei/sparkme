===
Eclipse/python/spark envionment

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

===
pip install numpy --upgrade

yum install atlas atlas-devel lapack-devel blas-devel
pip install scipy

pip install sparklingpandas

