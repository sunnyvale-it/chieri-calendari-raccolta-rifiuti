if [ $# -eq 0 ]
  then
    echo "No arguments supplied!"
    echo "usage: ./$(basename "$0") <YEAR>"
    echo "ie: ./$(basename "$0") 2021"
    exit 1
fi
export ANNO=$1
export export IFS=$'\n'
mkdir -p coordinates/$ANNO
for comune in $(cat comuni.txt);
do
  export FILENAME=$(echo ${comune} | tr  '[a-z]' '[A-Z]' | sed 's/ //g')_${ANNO}_coordinates.csv;
  echo "cp coordinates/coordinates_template_$ANNO.csv coordinates/$ANNO/$FILENAME"
  cp coordinates/coordinates_template_$ANNO.csv coordinates/$ANNO/$FILENAME
done;
