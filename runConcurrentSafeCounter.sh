#!/bin/bash

mvn compile exec:java -Dexec.mainClass="trythreads.concurrency.TestSafeCounter"
