#!/bin/bash
YEAR=$(date '+%Y')
DAY=$(date '+%d')
DAY_NO_ZEROS="${DAY//0/}"

AOC_SESSION_COOKIE="FIND_IN_BROWSER"

PUZZLE_URL="https://adventofcode.com/${YEAR}/day/${DAY_NO_ZEROS}/input"

PUZZLE_FILE="/Users/fmr/projects/advent/src/test/resources/${YEAR}/input_${DAY}.txt"
PUZZLE_FILE_TEST="/Users/fmr/projects/advent/src/test/resources/${YEAR}/input_${DAY}_test.txt"

curl -q -s "${PUZZLE_URL}" -H "cookie: session=${AOC_SESSION_COOKIE}" -o "${PUZZLE_FILE}" 2>/dev/null
touch "${PUZZLE_FILE_TEST}"