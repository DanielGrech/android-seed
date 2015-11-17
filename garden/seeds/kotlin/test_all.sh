#!/bin/bash

DIVIDER="\n\n==========\n\n"

printf "$DIVIDER Unit testing models...$DIVIDER"

./gradlew :model:test :model:jacocoTestReport

printf "$DIVIDER Unit testing network...$DIVIDER"

./gradlew :network:test :network:jacocoTestReport

printf "$DIVIDER Unit testing app...$DIVIDER"

./gradlew :app:testJacocoEnabledUnitTest :app:testJacocoEnabledUnitTestCoverage