# LogsCounter
A program to count different log types on the given interval

# How to run
The run the program provide three arguments:
1) A directory path to log files
2) Interval in milliseconds(e.g. 86400000(day), 3600000(hour), 60000(minute))
3) Type of log to count(Warn, Error, Info, Debug)

Input logs format is expected like:
2019-01-01T00:12:01.001;ERROR; Error 1
2019-01-01T00:12:01.004;ERROR; Error 2
2019-01-01T00:12:01.006;ERROR; Another error 3
2019-01-02T00:13:02.000;WARN; Warning 1
2019-01-02T00:13:02.002;ERROR; Error 5
2019-01-03T00:14:03.003;ERROR; Error 6
 
Output:
2019-01-01, 11.00-12.00 Количество Error: 3
2019-01-02, 12.00-13.00 Количество Error: 1
2019-01-03, 14.00-15.00 Количество Error: 1
