#!/bin/bash

set -e

while ! nc -z zookeeper 2181
do
  echo -n .
  sleep 5
done
echo "zookeeper detected"

/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master ''
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta ''
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential ''
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential/health ''
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential/health/otms-core ''
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential/health/otms-core/test '{"serviceName":"otms-core","environment":"test","username":"otms.cn","password":"3XNrtgz3*#d$"}'
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential/group '{"username":"user","password":"pass"}'
/zookeeper-3.4.10/bin/zkCli.sh -server zookeeper create /master/meta/credential/instance '{"username":"user","password":"pass"}'

while ! nc -z otms-master-service 8080
do
  echo -n .
  sleep 5
done
echo "master service detected"

curl -H 'Content-Type: application/json' -H 'user: user' -H 'pass: pass' -X POST -d @configs.json -v http://otms-master-service:8080/ws/master/api/group/create

exec "$@"
