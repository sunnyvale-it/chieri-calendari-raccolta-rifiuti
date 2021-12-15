#!/bin/bash

declare -a zones=($(cat comuni.txt | tr  '[a-z]' '[A-Z]' | sed 's/ //g'))

declare -a years=(
"2021"
"2022"
)

for year in "${years[@]}"
do
#  mkdir -p coordinates/$year
  mkdir -p CSVs/${year}
  mkdir -p ICSs/${year}
#  ./generate_coordinates.sh $year
done


for zone in "${zones[@]}"
do
  for year in "${years[@]}"
  do
    mkdir -p PDFs/$year
    curl -s -o PDFs/$year/${zone}_${year}.pdf http://www.ccs.to.it/flex/Extensions/appCCSCalendario/pages/serveDownload.php\?a\=${year}\&f\=${zone}.pdf\&t\=raccolta
    if test -f PDFs/$year/${zone}_${year}.pdf; then
      echo "PDF file PDFs/$year/${zone}_${year}.pdf downloaded"
    fi
    if test -f "PDFs/${year}/${zone}_${year}.pdf" && test -f "coordinates/${year}/${zone}_${year}_coordinates.csv"; then
      echo "Processing file PDFs/${year}/${zone}_${year}.pdf using coordinates from coordinates/${year}/${zone}_${year}.csv"
      java -jar target/chieri-calendari-raccolta-rifiuti-1.0-SNAPSHOT-jar-with-dependencies.jar ${zone} PDFs/${year}/${zone}_${year}.pdf coordinates/${year}/${zone}_${year}_coordinates.csv ${year} CSVs/${year} ICSs/${year}
    fi
  done
done