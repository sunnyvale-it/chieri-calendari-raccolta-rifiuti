# Garbage collection PDF text extractor

## Purpose

A couple of months ago, I sent an email to the local waste management department asking if it was possible for them to publish the trash collections schedule in some machine-readable format instead of paper, sent to citizens by traditional mail, or PDF downloadable by their website.

I wanted to integrate the schedule in my smart home's workflows to receive an alert advising me about the type of the upcoming collection  and automate some other processes regarding the disposal, so I proposed them to publish the schedule using Google Calendar format (ICS) but also CSV would have been fine.

I never received an answer and honestly I think that, provided they have ever read my email, they didn't even understand what I was talking about (PDF is already an electronic format, isn't it?).

Then, I realized that being a developer is fantastic because if you need a software you can craft it on your own and so I did, writing a Java application that parses a calendar in PDF format and exports the schedules on a CSV file, to be easly imported on Google Calendar (having fun along the process). 

Even if it is far from being considered a finished good, I decided to open source it on GitHub in order for it to serve as a reference for the once having the same struggle.

## Adapt to your PDF calendar format

Before using the application, provide a coordinates file in **coordinates/\<YEAR\>** (have a look of the existing as an example)

## Build the application

To build the application (using Maven) run:

```console
$ mvn clean && mvn package
```

## Run the application

To run the application type (please change the <YEAR> placeholder with the year, i.e. 2021 and <ZONE> with the zone of interest, i.e. CHIERI3B):

```console
$ java -jar target/garbage-collection-PDF-text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar <ZONE> /path/to/file.pdf /path/to/coordinates_file.csv <YEAR> /path/to/output/csv /path/to/output/ics
```

The provided source code works with this [PDF](PDFs/2021/CHIERI3B_2021.pdf)

```console
$ java -jar target/garbage-collection-PDF-text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar CHIERI3B PDFs/2021/CHIERI3B_2021.pdf coordinates/2021/CHIERI3B_2021_coordinates.csv 2021 CSVs/2021 ICSs/2021

```

The application produces both .csv and .ics files within the path you specified on the command line.

## Final thought

To the cities' administrations I would say that the time has come to provide data in much more meaningful ways, becase citizens houses are today much smarter than having 1K reminders pinned on the fridge!

