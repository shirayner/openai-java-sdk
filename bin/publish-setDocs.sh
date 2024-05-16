#!/bin/sh
OLD_VERSION=$1
NEW_VERSION=$2
HOME=$(pwd)

# shellcheck disable=SC2164
cd "$HOME"/docs/

sed -i '' "s/<em>${OLD_VERSION}/${NEW_VERSION}/g" mkdocs.yml

# shellcheck disable=SC2164
cd "$HOME"

sed -i '' "s/${OLD_VERSION}/${NEW_VERSION}/g" README.md
sed -i '' "s/${OLD_VERSION}/${NEW_VERSION}/g" README.zh_CN.md
