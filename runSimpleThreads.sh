#!/bin/bash

if [ -z "$1" ];
then
	mvn compile exec:java -Dexec.mainClass="trythreads.simple.SimpleThreads"
else
	mvn compile exec:java -Dexec.mainClass="trythreads.simple.SimpleThreads" -Dexec.args="$1"
if
