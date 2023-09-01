#!/bin/bash
set -exuo pipefail

# run [ $ ./buildandrun.sh ] to execute this script in bash shell

# read the Dockerfile and containerize the application
docker build -t websurfer21/techJobsimage .

# docker push websurfer21/techJobsimage
docker run --name techJobscontainer --rm -it -p 8080:8080 websurfer21/techJobsimage

# clean previous image
docker rmi websurfer21/techJobsimage