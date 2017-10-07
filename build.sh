#!/usr/bin/env bash

nix-shell --pure --command "runhaskell Build.hs $*"
