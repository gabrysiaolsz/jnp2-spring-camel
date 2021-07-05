#!/bin/bash
DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
docker run -it -p 8080:8080 -v $DIR:/io springapp:0.1
