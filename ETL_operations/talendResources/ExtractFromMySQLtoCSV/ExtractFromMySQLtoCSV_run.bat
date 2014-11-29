%~d0
 cd %~dp0
 java -Xms256M -Xmx1024M -cp ../lib/dom4j-1.6.1.jar;../lib/mysql-connector-java-5.1.22-bin.jar;../lib/talendcsv.jar;../lib/talend_file_enhanced_20070724.jar;../lib/systemRoutines.jar;../lib/userRoutines.jar;.;extractfrommysqltocsv_0_1.jar; etl_api_tfd_1.extractfrommysqltocsv_0_1.ExtractFromMySQLtoCSV --context=Default %* 