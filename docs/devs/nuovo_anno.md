# Release nuova annualità

### Prerequisiti:
- Bash o Zsh

### Procedura per release nuova annualità

Esportare la variabile NUOVO_ANNO sul terminale utilizzato per le operazioni, questo aiuterà ad avere una procedura riutilizzabile salvo cambiare il valore di $NUOVO_ANNO per ogni nuova annualità.

Nell'esempio qui sotto, immaginiamo che il nuovo anno per cui si stanno creando i calendari sia il 2024

```console
$ export NUOVO_ANNO=2024
```

Dopo aver clonato questo repository, posizionarsi sul branch Development

```console
$ git checkout development
```

Creare un nuovo "feature branch" dove risiederanno le modifiche per i calendari del nuovo anno

```console
$ git checkout -b feature/calendari-$NUOVO_ANNO
```

Modificare il file `run_me.sh` nella parte alta, inserendo il nuovo anno e mantenendo SOLO quello precedente. E' necessario rimuovere l'anno più vecchio per fare in modo che vengano gestite solamente 2 annualità.

es:

```bash
# Mantenere gli ultimi due anni (quello nuovo e quello precedente)
declare -a years=(
"2023"
"2024"
)
```

Qualora il Consorzio Chierese avesse aggiunto un nuovo comune o riorganizzato le zone di raccolta, aggiornare la lista dei comuni nel file [comuni.txt](../../comuni.txt).

Creare il file con le coordinate per il nuovo anno

```console
$ cp coordinates/coordinates_template_`expr $NUOVO_ANNO - 1`.csv coordinates/coordinates_template_$NUOVO_ANNO.csv
```

Ipotizzando che si stia creando il file di coordinate per l'anno 2024, controllare che dopo il comando appena eseguito sia presente il file `coordinates/coordinates_template_2024.csv`.

Occorre ora modificare il file **coordinates/coordinates_template_$NUOVO_ANNO.csv** in modo che contenga tutte le informazioni (tra cui le coordinate dei punti del PDF da cui estrarre i dati) necessarie a generare i calendari ICS e CSV.

Per ottenere le coordinate dei punti dal file PDF ed inserirle nel file, fare riferimento all'[apposita documentazione](coordinate_PDF.md).

Una volta modificato in maniera opportuna il file **coordinates/coordinates_template_$NUOVO_ANNO.csv**, eseguire

```console
$ ./generate_coordinates.sh $NUOVO_ANNO
```

Lo script crea la cartella coordinates/$NUOVO_ANNO con all'interno un file per ogni comune/zona, contenente le informazioni necessarie per estrapolare i dati dal PDF messo a disposizione dal Consorzio Chierese per i Servizi.

Svolte le operazioni elencate qui sopra, è sufficiente fare il merge del feature branche sul branch development per scatenare la pipeline di generazione dei calendari. Per coloro che hanno forkato questo repo sul loro account GitHub, è necessario aprire pull request.