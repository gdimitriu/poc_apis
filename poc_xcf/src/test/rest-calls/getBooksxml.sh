#!/bin/bash

curl -i -G -H "Accept: application/xml" http://localhost:$1/categoryservice/category/$2/books
echo
