#!/bin/bash



export ICS_CALENDARS=$(
for year in $(cd ICSs/ && find * -type d);
do
  for file in $(cd ICSs/${year} && find *.ics -type f);
  do
    TAGNAME="$(echo $file)"
    gh release create "$TAGNAME" $file
  done
done
)

export CSV_CALENDARS=$(
for year in $(cd CSVs/ && find * -type d);
do
  for file in $(cd CSVs/${year} && find *.csv -type f);
  do
    TAGNAME="$(echo $file)"
    gh release create "$TAGNAME" $file
  done
done
)
