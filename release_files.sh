#!/bin/bash  

gh auth login --with-token <<<"$GITHUB_TOKEN"
ICS_CALENDARS=""

for year in $(find ICSs/* -type d | cut -d '/' -f 2);
do
  for file in $(find ICSs/${year}/*.ics -type f);
  do
    ZONA=$(echo $file | sed 's/\..*//g' | cut -d "/" -f 3 | cut -d "_" -f 1)
    FORMATO=$(echo $file  | cut -d "." -f 2)
    ICS_CALENDARS+="'./${file}#$ZONA' "
  done
  if [ -z "$(gh release view $year-ICS 2> /dev/null | cat)" ]
  then  
    eval "gh release create \"$year-ICS\" -t \"$year-ICS\" -n \"Calendari $year in formato ICS\" $ICS_CALENDARS"
  else
    echo "Release $year-ICS already present, skipping..."
  fi
  export ICS_CALENDARS=""
done

sleep 20

CSV_CALENDARS=""
for year in $(find CSVs/* -type d | cut -d '/' -f 2);
do
  for file in $(find CSVs/${year}/*.csv -type f);
  do
    ZONA=$(echo $file | sed 's/\..*//g' | cut -d "/" -f 3 | cut -d "_" -f 1)
    FORMATO=$(echo $file  | cut -d "." -f 2)
    CSV_CALENDARS+="'./${file}#$ZONA' "
  done
  if [ -z "$(gh release view $year-CSV 2> /dev/null | cat)" ]
  then  
    eval "gh release create \"$year-CSV\" -t \"$year-CSV\" -n \"Calendari $year in formato CSV\" $CSV_CALENDARS"
  else
    echo "Release $year-CSV already present, skipping..."
  fi
  export CSV_CALENDARS=""
done
