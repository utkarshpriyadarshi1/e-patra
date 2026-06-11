#!/usr/bin/env bash
# Delegate execution to packaging-builder/clean.sh
exec "$(dirname "$0")/packaging-builder/clean.sh" "$@"
