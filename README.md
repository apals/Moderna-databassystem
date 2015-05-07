# Moderna-databassystem

Using Hadoop 2.7<br/>
Hadoop Install tutorial <br/>
https://www.digitalocean.com/community/tutorials/how-to-install-hadoop-on-ubuntu-13-10<br/>

Using Hive 1.1.0<br/>
Hive Install Tutorial<br/>
https://cwiki.apache.org/confluence/display/Hive/AdminManual+Installation<br/>
http://www.tutorialspoint.com/hive/hive_installation.htm<br/>




Problems when installing Hadoop:<br/>
* Localhost connection refused<br/>
Solution: sudo apt-get install openssh-server

* NameNode and Datanode not showing<br/>
Solution: sudo chown -R jb:jb /usr/local/hadoop_store
