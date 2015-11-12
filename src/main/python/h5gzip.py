#!/user/bin/env python
# -*- coding: utf-8 -*- 
from __future__ import print_function # code will work with python3 as well

# this example creates and writes GZIP compressed dataset
import h5py
import numpy as np
from chunk import Chunk

# initialize data
LeftVector = np.arange(-1,32-1,dtype='int32')
RightVector = np.arange(64,dtype='int32')
# Compute the outer product of two vectors.
DataArray = np.outer(LeftVector, RightVector) # create 32*64 array of i*j-j

# The __with__ construct will automatically create and close the HDF5 file
with h5py.File('D:\gzip.h5', 'w') as h5_fid:
    # create and write /DS1 dataset; in order to use compression, dataset has to be Chunked
    h5_fid.create_dataset('DS1', data=DataArray, chunks=(4,8), compression='gzip', compression_opts=9)

# Read data back; display compression properties and dataset max Value
with h5py.File('D:\gzip.h5', 'r') as h5_fid:
    dataset = h5_fid['DS1']
    print("compression method is", dataset.compression)
    print("maximum value in", dataset.name, "is:", dataset.value.max())
