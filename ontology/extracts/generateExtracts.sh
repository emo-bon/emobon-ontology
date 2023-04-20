#!/usr/bin/env bash
# Script for extracting classes and properties from other ontologies using ROBOT

robot.sh -vvv extract --method MIREOT \
    --imports exclude \
    --input ../dependencies/envo/envo.owl \
    --prefix "obo: http://purl.obolibrary.org/obo/" \
    --branch-from-term "obo:ENVO_00000428" \
    --output ./envo-module.owl

