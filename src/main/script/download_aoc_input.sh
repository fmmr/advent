#!/bin/bash
YEAR=$(date '+%Y')
DAY=$(date '+%d')
# shellcheck disable=SC2001
DAY_NO_ZEROS="$(echo "$DAY" | sed 's/^0*//')"

AOC_SESSION_COOKIE="FIND_IN_BROWSER"
AOC_DIR="/Users/fmr/projects/advent"
PUZZLE_URL="https://adventofcode.com/${YEAR}/day/${DAY_NO_ZEROS}/input"

PUZZLE_FILE="${AOC_DIR}/src/test/resources/${YEAR}/input_${DAY}.txt"
PUZZLE_FILE_TEST="${AOC_DIR}/src/test/resources/${YEAR}/input_${DAY}_test.txt"

curl -q -s "${PUZZLE_URL}" -H "cookie: session=${AOC_SESSION_COOKIE}" -o "${PUZZLE_FILE}" 2>/dev/null
touch "${PUZZLE_FILE_TEST}"

cd "${AOC_DIR}" || exit
git add "src/test/resources/${YEAR}/input_${DAY}_test.txt"
git commit -a -m "${YEAR} - Day ${DAY} - input/init"
cd - || exit

tail -10 "$PUZZLE_FILE"