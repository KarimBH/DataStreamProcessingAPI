%~d0
 cd %~dp0
 java -Xms256M -Xmx1024M -cp ../lib/dom4j-1.6.1.jar;../lib/ojdbc6.jar;../lib/talend-oracle-timestamptz.jar;../lib/talendcsv.jar;../lib/talend_file_enhanced_20070724.jar;../lib/systemRoutines.jar;../lib/userRoutines.jar;.;extractandmap_0_1.jar; etl_api_tfd_1.extractandmap_0_1.extractAndMap --context=Default %* 