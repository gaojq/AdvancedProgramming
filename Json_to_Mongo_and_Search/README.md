# HW2 ReadMe
### Class ToMongo
#### This class can convert Json file and store it under the setted up collection in MongodDB.
##### 1. createDB method
This method create the database and collection that required
##### 2. transferToMongo method
This method read every Json file and store it in Mongo collections under its' name

### Class GetDB
#### This class has the print format and all searching function

##### 1. countDay method
Read in date and collection name, and search the number of heart-rate data inside of the collection

##### 2. running method
Read in date and collection name, and see if this person has been running on the input date, if yes, print out running period, else print no running 

##### 3. countSteps method
Read in date and collection name, and get the number of steps that this person has been finished on specific date

