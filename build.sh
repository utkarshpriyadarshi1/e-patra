#!/usr/bin/env bash
# Delegate execution to packaging-builder/build.sh
exec "$(dirname "$0")/packaging-builder/build.sh" "$@"
