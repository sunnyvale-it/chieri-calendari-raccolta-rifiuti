# Informazioni tecniche su questo repository

## Statistiche dei download

Prerequisiti:

- curl
- jq

```console
$ curl -s \
  -H "Accept: application/vnd.github.v3+json" \
  https://api.github.com/repos/sunnyvale-it/chieri-calendari-raccolta-rifiuti/releases | jq '.[].assets[] | "\(.name),\(.download_count)"' | sed -e 's/"//g'
```