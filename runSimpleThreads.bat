@echo off 

if .%1 == . (
	mvn compile exec:java -Dexec.mainClass="trythreads.simple.SimpleThreads"
	) ELSE (
	mvn compile exec:java -Dexec.mainClass="trythreads.simple.SimpleThreads"  -Dexec.args=%1
	)

