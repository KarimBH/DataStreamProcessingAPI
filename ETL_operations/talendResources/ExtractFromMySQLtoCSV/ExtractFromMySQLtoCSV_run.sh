#!/bin/sh
cd `dirname $0`
 ROOT_PATH=`pwd`
 java -Xms256M -Xmx1024M -cp $ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/mysql-connector-java-5.1.22-bin.jar:$ROOT_PATH/../lib/talendcsv.jar:$ROOT_PATH/../lib/talend_file_enhanced_20070724.jar:$ROOT_PATH:$ROOT_PATH/../lib/systemRoutines.jar:$ROOT_PATH/../lib/userRoutines.jar::.:$ROOT_PATH/extractfrommysqltocsv_0_1.jar: etl_api_tfd_1.extractfrommysqltocsv_0_1.ExtractFromMySQLtoCSV --context=Default "$@" 