#!/bin/bash

declare -a zones=($(cat comuni.txt | tr  '[a-z]' '[A-Z]' | sed 's/ //g'))

declare -a years=(
"2022"
"2023"
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
    echo "PDFs/${year}/${zone}_${year}.*"
    if [ ! -s "PDFs/${year}/${zone}_${year}.*" ]; then
      echo "Downloading from https://www.ccs.to.it/flex/Extensions/appCCSCalendario/pages/serveDownload.php?a=${year}&f=${zone}.pdf&t=raccolta"
      curl -lsk --retry 5 --retry-max-time 120 --max-time 30 -o PDFs/${year}/${zone}_${year}.pdf "https://www.ccs.to.it/flex/Extensions/appCCSCalendario/pages/serveDownload.php?a=${year}&f=${zone}.pdf&t=raccolta"
      if [ -s PDFs/${year}/${zone}_${year}.pdf ]; then
        echo "PDF file PDFs/${year}/${zone}_${year}.pdf downloaded"
      else
        echo "Downloading from https://www.ccs.to.it/flex/Extensions/appCCSCalendario/pages/serveDownload.php?a=${year}&f=${zone}.PDF&t=raccolta"
        curl -lsk --retry 5 --retry-max-time 120 --max-time 30 -o PDFs/${year}/${zone}_${year}.pdf "https://www.ccs.to.it/flex/Extensions/appCCSCalendario/pages/serveDownload.php?a=${year}&f=${zone}.PDF&t=raccolta"
        if [ -f PDFs/${year}/${zone}_${year}.pdf ]; then
          echo "PDF file PDFs/${year}/${zone}_${year}.PDF downloaded"
        fi
      fi
    fi
    if test -s "PDFs/${year}/${zone}_${year}.pdf" && test -f "coordinates/${year}/${zone}_${year}_coordinates.csv"; then
      echo "Processing file PDFs/${year}/${zone}_${year}.pdf using coordinates from coordinates/${year}/${zone}_${year}.csv"
      java -jar target/chieri-calendari-raccolta-rifiuti-1.0-SNAPSHOT-jar-with-dependencies.jar ${zone} PDFs/${year}/${zone}_${year}.pdf coordinates/${year}/${zone}_${year}_coordinates.csv ${year} CSVs/${year} ICSs/${year}
    fi
  done
done