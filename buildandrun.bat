:: run [ $ buildandrun.bat ] to execute this script in command prompt
docker build -t websurfer21/techJobsimage .
:: docker push websurfer21/techJobsimage
docker run --name techJobscontainer --rm -it -p 8080:8080 websurfer21/techJobsimage
docker rmi websurfer21/techJobsimage