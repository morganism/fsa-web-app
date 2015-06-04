#!/bin/bash

export SRC_BASE=/home/morgan/src/git/fsa-web-app/fsa
export JARS=$SRC_BASE/jars

CLASSPATH=`ls -1 $JARS/*jar | tr '\n' ':' | sed "s/:$//"`
export CLASSPATH=.:$SRC_BASE/classes:$CLASSPATH

cd $SRC_BASE/fsa/classes
java uk.co.sziraki.fsa.FSAMain
