# Garbage collection PDF text extractor

## Purpose

A couple of months ago, I sent an email to the local waste management department asking if it was possible for them to publish the trash collections schedule in some machine-readable format instead of paper, sent to citizens by traditional mail, or PDF downloadable by their website.

I wanted to integrate the schedule in my smart home's workflows to receive an alert advising me about the type of the upcoming collection  and automate some other processes regarding the disposal, so I proposed them to publish the schedule using Google Calendar format (ICS) but also CSV would have been fine.

I never received an answer and honestly I think that, provided they have ever read my email, they didn't even understand what I was talking about (PDF is already an electronic format, isn't it?).

Then, I realized that being a developer is fantastic because if you need a software you can craft it on your own and so I did, writing a Java application that parses a calendar in PDF format and exports the schedules on a CSV file, to be easly imported on Google Calendar (having fun along the process). 

Even if it is far from being considered a finished good, I decided to open source it on GitHub in order for it to serve as a reference for the once having the same struggle.

## Adapt to your PDF calendar format

Before using the application, open the class `denismaggior8.Main` and modify the `coordinatesArray` elements with the PDF coordinates where the text resides. Grab them using a PDF reader (I used macOS Preview).

Down in the main class, for each coordinate element modify the regex in order to grab the data you need.

## Build the application

To build the application (using Maven) run:

```console
$ mvn clean && mvn package
```

## Run the application

To run the application type (please change the <YEAR> placeholder with the year, i.e. 2021):

```console
$ java -jar target/garbage-collection-PDF-text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar /path/to/file.pdf <YEAR>
```

The provided source code works with this [PDF](PDFs/CHIERI3B_2021.pdf)

```console
$ java -jar target/garbage-collection-PDF-text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar PDFs/CHIERI3B.pdf 2020
...
PLASTICA,13/12/2020,09:00 PM,13/12/2020,09:10 PM,FALSE,PLASTICA
ORG,14/12/2020,09:00 PM,14/12/2020,09:10 PM,FALSE,ORG
CARTA,18/12/2020,09:00 PM,18/12/2020,09:10 PM,FALSE,CARTA
VETRO,20/12/2020,09:00 PM,20/12/2020,09:10 PM,FALSE,VETRO
ORG,21/12/2020,09:00 PM,21/12/2020,09:10 PM,FALSE,ORG
NON REC,23/12/2020,09:00 PM,23/12/2020,09:10 PM,FALSE,NON REC
PLASTICA,27/12/2020,09:00 PM,27/12/2020,09:10 PM,FALSE,PLASTICA
ORG,28/12/2020,09:00 PM,28/12/2020,09:10 PM,FALSE,ORG
...
```

The output lines can be collected in a .csv file and are already formatted in a way that can be imported in a Google Calendar.

## Final thought

To the cities' administrations I would say that the time has come to provide data in much more meaningful ways, becase citizens houses are today much smarter than having 1K reminders pinned on the fridge!

