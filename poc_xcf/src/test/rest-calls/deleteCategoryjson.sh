#!/bin/bash

curl -i -X DELETE -H "Content-Type: application/json" http://localhost:$1/categoryservice/category/@$2
echo