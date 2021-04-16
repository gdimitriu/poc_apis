#!/bin/bash

curl -i -X DELETE -H "Content-Type: application/xml" http://localhost:$1/categoryservice/category/@$2
echo