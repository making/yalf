#!/bin/sh
mvn -f yalf-parent/pom.xml -DaltDeploymentRepository=making-dropbox-releases::default::file:../maven/releases clean deploy
