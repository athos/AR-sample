#!/usr/bin/env sh

echo "Cleaning up project..."
lein clean

echo "Building project..."
lein cljsbuild once min

echo "Copying files..."
cp resources/public/index.html docs
cp resources/public/js/compiled/* docs/js/compiled
cp resources/public/css/* docs/css
cp resources/public/data/* docs/data

echo "Done."
