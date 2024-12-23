# Statistiche dei download

## Prerequisiti:

- curl
- jq
- Bash o Zsh

## Estrazione statistiche download:

Il comando che segue genera un output in formato CSV con il dettaglio dei download di ogni zona/comune nei diversi anni:

```console
$ curl -s \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/repos/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases | jq '.[].assets[] | "\(.name),\(.download_count)"' | sed -e 's/"//g'
```

L'output sarà simile a quello riportato qui di seguito:

```csv
ANDEZENO_2023.ics,522
ARIGNANO_2023.ics,11
BALDISSERO1_2023.ics,8
BALDISSERO2_2023.ics,772
CAMBIANO_2023.ics,2437
CARMAGNOLAA_2023.ics,4
CARMAGNOLAB_2023.ics,4
CARMAGNOLAC_2023.ics,4
CARMAGNOLAD_2023.ics,5
CARMAGNOLAE_2023.ics,4
CARMAGNOLAF_2023.ics,4
CHIERI1A_2023.ics,30
CHIERI1B_2023.ics,11
CHIERI2A_2023.ics,12
CHIERI2B_2023.ics,12
CHIERI3A_2023.ics,75
CHIERI3B_2023.ics,1650
CHIERI4A_2023.ics,412
CHIERI4B_2023.ics,14
CHIERI5A_2023.ics,15552
CHIERI5B_2023.ics,4
CHIERI6A_2023.ics,527
CHIERI6B_2023.ics,7
ISOLABELLA_2023.ics,4
MARENTINO_2023.ics,5
MOMBELLO_2023.ics,489
MONCUCCO_2023.ics,4
MONTALDO_2023.ics,4
MORIONDO_2023.ics,7
PAVAROLO_2023.ics,4
PECETTO_2023.ics,13
PINO1A_2023.ics,6
PINO1B_2023.ics,4
PINO2A_2023.ics,7
PINO2B_2023.ics,4
PINO3B_2023.ics,4
PINO4B_2023.ics,5
POIRINOCASESPARSEA_2023.ics,6
POIRINOCASESPARSEB_2023.ics,5
POIRINOCASESPARSEC_2023.ics,5
POIRINOCASESPARSED_2023.ics,6
POIRINOCONCENTRICOA_2023.ics,10
POIRINOCONCENTRICOB_2023.ics,5
POIRINOCONCENTRICOC_2023.ics,5
POIRINOCONCENTRICOD_2023.ics,5
PRALORMO_2023.ics,6
RIVAPRESSOCHIERI_2023.ics,9
SANTENA1_2023.ics,8
SANTENA2_2023.ics,4
SANTENA3_2023.ics,8
ANDEZENO_2023.csv,4
ARIGNANO_2023.csv,5
BALDISSERO1_2023.csv,4
BALDISSERO2_2023.csv,5
CAMBIANO_2023.csv,9
CARMAGNOLAA_2023.csv,6
CARMAGNOLAB_2023.csv,6
CARMAGNOLAC_2023.csv,5
CARMAGNOLAD_2023.csv,5
CARMAGNOLAE_2023.csv,5
CARMAGNOLAF_2023.csv,5
CHIERI1A_2023.csv,6
CHIERI1B_2023.csv,4
CHIERI2A_2023.csv,4
CHIERI2B_2023.csv,4
CHIERI3A_2023.csv,7
CHIERI3B_2023.csv,8
CHIERI4A_2023.csv,4
CHIERI4B_2023.csv,4
CHIERI5A_2023.csv,4
CHIERI5B_2023.csv,4
CHIERI6A_2023.csv,14
CHIERI6B_2023.csv,4
ISOLABELLA_2023.csv,4
MARENTINO_2023.csv,5
MOMBELLO_2023.csv,4
MONCUCCO_2023.csv,4
MONTALDO_2023.csv,4
MORIONDO_2023.csv,4
PAVAROLO_2023.csv,4
PECETTO_2023.csv,5
PINO1A_2023.csv,5
PINO1B_2023.csv,5
PINO2A_2023.csv,4
PINO2B_2023.csv,4
PINO3B_2023.csv,6
PINO4B_2023.csv,6
POIRINOCASESPARSEA_2023.csv,4
POIRINOCASESPARSEB_2023.csv,5
POIRINOCASESPARSEC_2023.csv,5
POIRINOCASESPARSED_2023.csv,4
POIRINOCONCENTRICOA_2023.csv,5
POIRINOCONCENTRICOB_2023.csv,4
POIRINOCONCENTRICOC_2023.csv,4
POIRINOCONCENTRICOD_2023.csv,6
PRALORMO_2023.csv,4
RIVAPRESSOCHIERI_2023.csv,4
SANTENA1_2023.csv,4
SANTENA2_2023.csv,4
SANTENA3_2023.csv,6
ANDEZENO_2022.ics,1018
ARIGNANO_2022.ics,6
BALDISSERO1_2022.ics,7
BALDISSERO2_2022.ics,7
CAMBIANO_2022.ics,4721
CARMAGNOLAA_2022.ics,8
CARMAGNOLAB_2022.ics,10
CARMAGNOLAC_2022.ics,7
CARMAGNOLAD_2022.ics,6
CARMAGNOLAE_2022.ics,8
CARMAGNOLAF_2022.ics,7
CHIERI1A_2022.ics,20
CHIERI1B_2022.ics,13
CHIERI2A_2022.ics,12
CHIERI2B_2022.ics,1045
CHIERI3A_2022.ics,1520
CHIERI3B_2022.ics,64274
CHIERI4A_2022.ics,998
CHIERI4B_2022.ics,11
CHIERI5A_2022.ics,17
CHIERI5B_2022.ics,18
CHIERI6A_2022.ics,2370
CHIERI6B_2022.ics,9
ISOLABELLA_2022.ics,8
MARENTINO_2022.ics,12
MOMBELLO_2022.ics,7
MONCUCCO_2022.ics,7
MONTALDO_2022.ics,6
MORIONDO_2022.ics,6
PAVAROLO_2022.ics,7
PECETTO_2022.ics,14
PINO1A_2022.ics,7
PINO1B_2022.ics,7
PINO2A_2022.ics,7
PINO2B_2022.ics,8
PINO3B_2022.ics,6
PINO4B_2022.ics,9
POIRINOCASESPARSEA_2022.ics,7
POIRINOCASESPARSEB_2022.ics,6
POIRINOCASESPARSEC_2022.ics,8
POIRINOCASESPARSED_2022.ics,9
POIRINOCONCENTRICOA_2022.ics,10
POIRINOCONCENTRICOB_2022.ics,7
POIRINOCONCENTRICOC_2022.ics,7
POIRINOCONCENTRICOD_2022.ics,6
PRALORMO_2022.ics,8
RIVAPRESSOCHIERI_2022.ics,9
SANTENA1_2022.ics,7
SANTENA2_2022.ics,8
SANTENA3_2022.ics,8
ANDEZENO_2022.csv,8
ARIGNANO_2022.csv,7
BALDISSERO1_2022.csv,6
BALDISSERO2_2022.csv,7
CAMBIANO_2022.csv,6
CARMAGNOLAA_2022.csv,8
CARMAGNOLAB_2022.csv,9
CARMAGNOLAC_2022.csv,8
CARMAGNOLAD_2022.csv,6
CARMAGNOLAE_2022.csv,8
CARMAGNOLAF_2022.csv,9
CHIERI1A_2022.csv,11
CHIERI1B_2022.csv,7
CHIERI2A_2022.csv,10
CHIERI2B_2022.csv,7
CHIERI3A_2022.csv,7
CHIERI3B_2022.csv,10
CHIERI4A_2022.csv,8
CHIERI4B_2022.csv,6
CHIERI5A_2022.csv,9
CHIERI5B_2022.csv,6
CHIERI6A_2022.csv,7
CHIERI6B_2022.csv,8
ISOLABELLA_2022.csv,6
MARENTINO_2022.csv,7
MOMBELLO_2022.csv,7
MONCUCCO_2022.csv,7
MONTALDO_2022.csv,7
MORIONDO_2022.csv,7
PAVAROLO_2022.csv,7
PECETTO_2022.csv,7
PINO1A_2022.csv,7
PINO1B_2022.csv,6
PINO2A_2022.csv,9
PINO2B_2022.csv,8
PINO3B_2022.csv,7
PINO4B_2022.csv,8
POIRINOCASESPARSEA_2022.csv,7
POIRINOCASESPARSEB_2022.csv,8
POIRINOCASESPARSEC_2022.csv,6
POIRINOCASESPARSED_2022.csv,8
POIRINOCONCENTRICOA_2022.csv,7
POIRINOCONCENTRICOB_2022.csv,6
POIRINOCONCENTRICOC_2022.csv,7
POIRINOCONCENTRICOD_2022.csv,7
PRALORMO_2022.csv,7
RIVAPRESSOCHIERI_2022.csv,6
SANTENA1_2022.csv,7
SANTENA2_2022.csv,7
SANTENA3_2022.csv,8
ANDEZENO_2021.ics,7
ARIGNANO_2021.ics,7
BALDISSERO1_2021.ics,7
BALDISSERO2_2021.ics,6
CAMBIANO_2021.ics,7
CARMAGNOLAA_2021.ics,7
CARMAGNOLAB_2021.ics,7
CARMAGNOLAC_2021.ics,7
CARMAGNOLAD_2021.ics,6
CARMAGNOLAE_2021.ics,6
CARMAGNOLAF_2021.ics,9
CHIERI1A_2021.ics,6
CHIERI1B_2021.ics,6
CHIERI2A_2021.ics,6
CHIERI2B_2021.ics,8
CHIERI3A_2021.ics,6
CHIERI3B_2021.ics,6
CHIERI4A_2021.ics,7
CHIERI4B_2021.ics,9
CHIERI5A_2021.ics,7
CHIERI5B_2021.ics,7
CHIERI6A_2021.ics,6
CHIERI6B_2021.ics,12
ISOLABELLA_2021.ics,8
MARENTINO_2021.ics,8
MOMBELLO_2021.ics,7
MONCUCCO_2021.ics,8
MONTALDO_2021.ics,7
MORIONDO_2021.ics,7
PAVAROLO_2021.ics,8
PECETTO_2021.ics,6
PINO1A_2021.ics,7
PINO1B_2021.ics,7
PINO2A_2021.ics,8
PINO2B_2021.ics,6
PINO3B_2021.ics,8
PINO4B_2021.ics,6
POIRINOCASESPARSEA_2021.ics,6
POIRINOCASESPARSEB_2021.ics,6
POIRINOCASESPARSEC_2021.ics,8
POIRINOCASESPARSED_2021.ics,7
POIRINOCONCENTRICOA_2021.ics,7
POIRINOCONCENTRICOB_2021.ics,6
POIRINOCONCENTRICOC_2021.ics,9
POIRINOCONCENTRICOD_2021.ics,10
PRALORMO_2021.ics,8
RIVAPRESSOCHIERI_2021.ics,8
SANTENA1_2021.ics,6
SANTENA2_2021.ics,8
SANTENA3_2021.ics,8
ANDEZENO_2021.csv,9
ARIGNANO_2021.csv,7
BALDISSERO1_2021.csv,6
BALDISSERO2_2021.csv,8
CAMBIANO_2021.csv,8
CARMAGNOLAA_2021.csv,6
CARMAGNOLAB_2021.csv,7
CARMAGNOLAC_2021.csv,6
CARMAGNOLAD_2021.csv,7
CARMAGNOLAE_2021.csv,7
CARMAGNOLAF_2021.csv,8
CHIERI1A_2021.csv,8
CHIERI1B_2021.csv,8
CHIERI2A_2021.csv,7
CHIERI2B_2021.csv,7
CHIERI3A_2021.csv,7
CHIERI3B_2021.csv,6
CHIERI4A_2021.csv,7
CHIERI4B_2021.csv,8
CHIERI5A_2021.csv,7
CHIERI5B_2021.csv,9
CHIERI6A_2021.csv,7
CHIERI6B_2021.csv,7
ISOLABELLA_2021.csv,6
MARENTINO_2021.csv,9
MOMBELLO_2021.csv,7
MONCUCCO_2021.csv,7
MONTALDO_2021.csv,6
MORIONDO_2021.csv,6
PAVAROLO_2021.csv,6
PECETTO_2021.csv,6
PINO1A_2021.csv,7
PINO1B_2021.csv,7
PINO2A_2021.csv,7
PINO2B_2021.csv,7
PINO3B_2021.csv,7
PINO4B_2021.csv,7
POIRINOCASESPARSEA_2021.csv,6
POIRINOCASESPARSEB_2021.csv,8
POIRINOCASESPARSEC_2021.csv,9
POIRINOCASESPARSED_2021.csv,7
POIRINOCONCENTRICOA_2021.csv,6
POIRINOCONCENTRICOB_2021.csv,6
POIRINOCONCENTRICOC_2021.csv,6
POIRINOCONCENTRICOD_2021.csv,6
PRALORMO_2021.csv,7
RIVAPRESSOCHIERI_2021.csv,8
SANTENA1_2021.csv,6
SANTENA2_2021.csv,7
SANTENA3_2021.csv,8
```