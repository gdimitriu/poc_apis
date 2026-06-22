#!/bin/bash

curl -i -X POST -H "Content-Type: application/xml" http://localhost:$1/categoryservice/category --data @$2
echo
