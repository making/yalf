#!/bin/sh
mvn -DaltDeploymentRepository=making-dropbox-releases::default::file:../../maven/releases deploy
