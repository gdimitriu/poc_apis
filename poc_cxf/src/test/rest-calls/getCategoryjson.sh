#!/bin/bash

curl -i -G -H "Accept: application/json" http://localhost:$1/categoryservice/category/$2
echo
