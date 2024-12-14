# Fetch Test receipt-processor-challenge
Nick Hoff
12/13/24

## Build & run
```
$ docker build -t fetch-test . 
$ docker run -p 8080:8080 -t fetch-test
```

## Hit it
```
## Process Receipts
$ curl -H 'Content-Type: application/json' localhost:8080/receipts/process --data @src/test/testdata/ReceiptExample1.json  

## Get Points
$ curl localhost:8080/receipts/6665c87d-e1bf-4a61-81ff-3b28ff75fcb3/points

```