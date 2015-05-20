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

create table Sentences(article_id INT, sentence_id INT, sentence STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ".";</br>


select REGEXP_EXTRACT(fras, '(May)', 0) as from senty; </br>

Set mapreduce tasks </br>
set mapred.job.tracker = local; </br> 




============== - queries - ==================
This is documentation from https://github.com/apache/hive/blob/trunk/contrib/src/java/org/apache/hadoop/hive/contrib/serde2/RegexSerDe.java
queries return NULL-columns when the regex doesn't match.

 * In deserialization stage, if a row does not match the regex, then all columns
 * in the row will be NULL. If a row matches the regex but has less than
 * expected groups, the missing groups will be NULL. If a row matches the regex
 * but has more than expected groups, the additional groups are just ignored.

SELECT * FROM (SELECT regexp_extract(fras, "May", 0) as match from fras) t2 WHERE match <> "";


SELECT * FROM (SELECT regexp_extract(sent, "(january|february|march|april|may|june|july|august|september|october |november|december)(\s+\d?\d\s*,?)?\s*\d{4}", 0) as match from julle) t2 WHERE match <> ""; 



http://www.megatome.com/2013/07/16/simple-data-analysis-with-pig/</br>
grunt> fras = LOAD '/user/apals/input2/*' USING PigStorage(',') AS <br/>
    (id:int, fras:chararray); </br>
grunt> dump fras;</br>

grunt> prutt = foreach fras generate REGEX_EXTRACT(fras, 'May', 0) as (kek:chararray);</br>
grunt> dump prutt;</br>
grunt> match = filter prutt by kek is not null;</br>
grunt> dump match;</br>

RUN PIG LOCALLY </br>
$ pig -x local </br>
grunt> fras = LOAD '/Users/apals/KTH/Moderna-databassystem/pigdata/*' USING PigStorage(',') AS </br>
    (id:int, fras:chararray); </br>
grunt> prutt = foreach fras generate REGEX_EXTRACT(fras, 'May', 0) as (kek:chararray);</br>
grunt> dump prutt;</br>
grunt> match = filter prutt by kek is not null;</br>
grunt> dump match;</br>

==== ----- POS TAGGING ---- ======</br>
http://nlp.stanford.edu/software/tagger.shtml</br>
http://www.galalaly.me/index.php/2011/05/tagging-text-with-stanford-pos-tagger-in-java-applications/</br>
http://en.wikipedia.org/wiki/Proper_noun</br>
https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html</br>



Problems:</br>
 java.lang.NoClassDefFoundError:edu/stanford/nlp/tagger/maxent/MaxentTagger</br>
 Solution: Build with jre 1.8</br>
 
 
 
 
 
 ==== ------ UDF ------- =================
 
 ==== hive ============
 
1. jar cf myjar className.class </br>
2. hive> add jar '/home/jb/KTH/myjar' </br>
3. hive> create temporary function funcName as 'className' </br>
4. hive> select funcName(fras) from senty; </br>

step 2 can give an error.  Fix :  add jar /home/jb/KTH/myjar </br>

===== pig =======

$ pig -x local (otherwise you have to add the .jars to hdfs with hadoop fs -put /path/to/jar </br>
grunt> register /Users/apals/.../*.jar </br>
/* YOU NEED TO REGISTER ALL DEPENDENCIES TOO, I.E. REGISTER LIBS FOLDER FROM CRFProjectPig */ </br>
grunt> things = LOAD '/Users/apals/KTH/Moderna-databassystem/pigdata/*' as (name:chararray); </br>
grunt> matched = foreach things generate myudfs.PigUdf7(name); </br>
    WHERE myudfs is the package where the class is stored. PigUdf7 is the class name. </br>
grunt> dump matched</br>
(-LRB-/OMike/PERSON-RRB-/O)
(-LRB-/OMicrosoft/ORGANIZATION-RRB-/O)

grunt> things = LOAD '/Users/apals/KTH/Moderna-databassystem/pigdata/*' as (name:chararray);

