#!/bin/bash

export PAGE_UPDATE_TIMESTAMP="⚡$(date)⚡"

export ICS_CALENDARS=$(
for year in $(cd PDFs/ && find * -type d | sort -nr | head -2);
do
  echo -e "#### Anno $year"
  for file in $(cd PDFs/${year} && find *.* -type f | cut -d . -f 1);
  do
    echo "- [$file](https://github.com/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases/download/${year}-ICS/$file.ics)"
  done
done
)
rm -r ICSs/*

export CSV_CALENDARS=$(
for year in $(cd PDFs/ && find * -type d | sort -nr | head -2);
do
  echo -e "#### Anno $year"
  for file in $(cd PDFs/${year} && find *.* -type f | cut -d . -f 1);
  do
    echo "- [$file](https://github.com/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases/download/${year}-CSV/$file.csv)"
  done
done
)
rm -r CSVs/*

#echo "$ICS_CALENDARS"
perl -p -i -e "s/^Ultimo aggiornamento:.*$/Ultimo aggiornamento: $PAGE_UPDATE_TIMESTAMP/g" ./README.md
perl -0777 -p -i -e "s/(?<=### Formato iCalendar compatibile con Google Calendar, MacOS Calendar, iOS Calendar, Zimbra)(.*)(?=### Formato MS Outlook)/\n$(echo "$ICS_CALENDARS"  | sed -e 's/\//\\\//g')\n/gs" ./README.md
perl -0777 -p -i -e "s/(?<=### Formato MS Outlook)(.*)(?=## Istruzioni)/\n$(echo "$CSV_CALENDARS"  | sed -e 's/\//\\\//g')\n/gs" ./README.md

