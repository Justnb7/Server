@echo off
title Server [%date%] [%time%]
"C:\Program Files\Java\jdk1.8.0_66\bin\java.exe" -Xmx8069m  -cp bin;lib/mvgate3.jar;deps/commons-io-2.4.jar;deps/commons-lang3-3.3.2.jar;lib/Motivote-server.jar;lib/guava-17.0.jar;lib/GTLVote.jar;deps/collisionmap.jar;deps/slf4j-nop.jar;deps/slf4j.jar;deps/gson-2.2.4.jar;deps/netty-all-4.0.25.Final.jar;deps/mysql.jar;deps/mysql-connector-java-5.1.6-bin;deps/mvgate2.jar server.Server
pause