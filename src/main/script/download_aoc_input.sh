#!/bin/bash

MYDIR="$(dirname "$(realpath "$0")")"
. $MYDIR/auth.txt

YEAR=$(date '+%Y')
DAY=$(date '+%d')
# shellcheck disable=SC2001
DAY_NO_ZEROS="$(echo "$DAY" | sed 's/^0*//')"

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


echo "HEAD:"
echo "====="
head -4 "$PUZZLE_FILE"
echo "TAIL:"
echo "====="
tail -4 "$PUZZLE_FILE"
echo
echo "TEST-FILE: $PUZZLE_FILE_TEST"
echo "FILE:      $PUZZLE_FILE"
echo "NUM-LINES: " $(wc -l $PUZZLE_FILE | awk {'print $1'})
