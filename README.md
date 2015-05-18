# Moderna-databassystem
Slides<br/>
http://www8.cs.umu.se/kurser/5DV120/V13/Slides/<br/>
<br/>
Using Hadoop 2.7<br/>
Hadoop Install tutorial <br/>
https://www.digitalocean.com/community/tutorials/how-to-install-hadoop-on-ubuntu-13-10<br/>

OS X Links</br>
http://amodernstory.com/2014/09/23/installing-hadoop-on-mac-osx-yosemite/ </br>
http://amodernstory.com/2014/09/23/hadoop-on-mac-osx-yosemite-part-2/ </br>
http://amodernstory.com/2015/03/29/installing-hive-on-mac/ </br>

====== - Starting several nodes - =========
https://bigdata.wordpress.com/2010/05/27/hadoop-cookbook-4-how-to-run-multiple-data-nodes-on-one-machine/  </br>



Using Hive 1.1.0<br/>
Hive Install Tutorial<br/>
https://cwiki.apache.org/confluence/display/Hive/AdminManual+Installation<br/>
https://cwiki.apache.org/confluence/display/Hive/GettingStarted<br/>
http://www.tutorialspoint.com/hive/hive_installation.htm<br/>

Installing Pig on OSX</br>
http://www.getblueshift.com/setting-up-hadoop-2-4-and-pig-0-12-on-osx-locally/




Problems when installing Hadoop:<br/>
* Localhost connection refused<br/>
Solution: sudo apt-get install openssh-server

* NameNode and Datanode not showing<br/>
Solution: sudo chown -R jb:jb /usr/local/hadoop_store

Problems when installing Hive: <br/>
* $ $HADOOP_HOME/bin/hadoop fs -mkdir /user/hive/warehouse gives error<br/>
Solution: use -p flag<br/>

* starting hive gives cannot create directory<br/>
Solution: sudo chown -R jb:jb /usr/local/hive<br/>

Problems </br>:
rm everything in data-folder in hdfs</br>
check this: http://stackoverflow.com/questions/16020334/hadoop-datanode-process-killed</br>

====== - The article - ======

http://cogcomp.cs.illinois.edu/page/tools_view/2 </br>

http://cogcomp.cs.illinois.edu/page/software_view/POS </br>



====== - HIVE - ========

LOAD FILES INTO HIVE </br>
FILE SHOULD BE .CVC </br>
load data local inpath <path> into table <name>  </br>

create tables : <br/>
create table fras(id int, fras varchar(200)) ROW FORMAT DELIMITED FIELDS TERMINATED BY ","; <br/>

create table sent(id INT, fras STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ".";  </br>

select REGEXP_EXTRACT(fras, '(May)', 0) as from senty; </br>

Set mapreduce tasks </br>
set mapred.job.tracker = local; </br> 



======== - udfs - ==========

1. jar cf myjar className.class </br>
2. hive > add jar '/home/jb/KTH/myjar' </br>
3. create temporary function funcName as 'className' </br>
4. select funcName(fras) from senty; </br>
5. 


============== - queries - ==================
This is documentation from https://github.com/apache/hive/blob/trunk/contrib/src/java/org/apache/hadoop/hive/contrib/serde2/RegexSerDe.java
queries return NULL-columns when the regex doesn't match.

 * In deserialization stage, if a row does not match the regex, then all columns
 * in the row will be NULL. If a row matches the regex but has less than
 * expected groups, the missing groups will be NULL. If a row matches the regex
 * but has more than expected groups, the additional groups are just ignored.

SELECT * FROM (SELECT regexp_extract(fras, "May", 0) as match from fras) t2 WHERE match <> "";






http://www.megatome.com/2013/07/16/simple-data-analysis-with-pig/
fras = LOAD '/user/apals/input2/*' USING PigStorage(',') AS <br/>
    (id:int, fras:chararray); </br>
dump fras;</br>

prutt = foreach fras generate REGEX_EXTRACT(fras, 'May', 0) as (kek:chararray);</br>
dump prutt;</br>
match = filter prutt by kek is not null;</br>
dump match;</br>

RUN PIG LOCALLY </br>
pig -x local </br>
fras = LOAD '/Users/apals/KTH/Moderna-databassystem/pigdata/*' USING PigStorage(',') AS </br>
    (id:int, fras:chararray); </br>
    prutt = foreach fras generate REGEX_EXTRACT(fras, 'May', 0) as (kek:chararray);</br>
dump prutt;</br>
match = filter prutt by kek is not null;</br>
dump match;</br>
