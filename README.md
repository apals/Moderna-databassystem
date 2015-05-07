# Moderna-databassystem
Install tutorial <br/>
https://www.digitalocean.com/community/tutorials/how-to-install-hadoop-on-ubuntu-13-10

Problems:<br/>
* Localhost connection refused
Solution: sudo apt-get install openssh-server

* NameNode and Datanode not showing
Solution: sudo chown -R jb:jb /usr/local/hadoop_store
