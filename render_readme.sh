#!/bin/bash

export PAGE_UPDATE_TIMESTAMP="⚡$(date)⚡"

export ICS_CALENDARS=$(
for year in $(cd ICSs/ && find * -type d);
do
  echo -e "#### Anno $year"
  for file in $(cd ICSs/${year} && find *.ics -type f);
  do
    echo "- [$file](https://github.com/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases/download/${year}-ICS/$file)"
  done
done
)

export CSV_CALENDARS=$(
for year in $(cd CSVs/ && find * -type d);
do
  echo -e "#### Anno $year"
  for file in $(cd CSVs/${year} && find *.csv -type f);
  do
    echo "- [$file](https://github.com/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases/download/${year}-CSV/$file)"
  done
done
)

#echo "$ICS_CALENDARS"
perl -p -i -e "s/^Ultimo aggiornamento:.*$/Ultimo aggiornamento: $PAGE_UPDATE_TIMESTAMP/g" ./README.md
perl -0777 -p -i -e "s/(?<=### Formato iCalendar compatibile con Google Calendar, MacOS Calendar, iOS Calendar, Zimbra)(.*)(?=### Formato MS Outlook)/\n$(echo "$ICS_CALENDARS"  | sed -e 's/\//\\\//g')\n/gs" ./README.md
perl -0777 -p -i -e "s/(?<=### Formato MS Outlook)(.*)(?=## Istruzioni)/\n$(echo "$CSV_CALENDARS"  | sed -e 's/\//\\\//g')\n/gs" ./README.md

