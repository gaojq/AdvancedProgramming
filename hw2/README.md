# HW2 ReadMe
### Class Unzip
#### This class basically set up a new directory and unzip all files separately under the directory of different sensor's name.
##### 1. isDirectory method
This method finds all the zip files that need to be unzipped, and make it into a File list
##### 2. unZipFiles method
(1) This method builds up a new directory based on the name of the folders that appear inside of the zip files. If it finds out a new folder which hasn't been rebuild then this function build-up that folder in a new set up directory.
(2) By using the ZipEntry, it read all the existing files under the current zip file, and write in the new directory

### Class MergeFiles
#### This class is to merge all data under different sensor folders and generate a new txt file for each sensor with all the sensor's data and stored outside of each sensor folder
##### 1. createDir method
Create a new .txt file outside each sensor folder and name it as the sensor
##### 2. mergeFile method
Merger all data inside of the sensor folder into the new created sensor's name .txt 

### Class Find
##### 1. search
Convert all contents of a .txt file into a string, then separate the entire string into a string array, then check if the value that we are searching is inside of it or not. If exist, then print out the whole line, otherwise, print no existence

### Class Main
Connect all other class together, and generate inputs and outputs
