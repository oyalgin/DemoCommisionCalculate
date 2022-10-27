# DemoCommisionCalculate
DemoCommisionCalculate


This project is conducted as console application.


# DemoCommisionCalculate

DemoCommisionCalculate is a Springboot applicaton which is conducted as console application.
Every 10 second pull data from Orders file, then process and write order's commission fee to another file.

## PreRequsite

Install Java 8 

placed Order.txt file in C:/temp
it has to include orders records, their structure is name price

it is like 

Order1 103
Order4 107
  ..


# Run the Application

clone the project or download as a zip 
go to demo-sheduler\src\main\java\com\fana\demosheduler 
To run the application, run the following command in a terminal window (in the complete) directory:

./mvnw spring-boot:run

What you will see on the terminal 

Each 10 second pullin mechanism will run, process orders.
You can see the logs about.

# Challenge about project 

To calculate commisions in a 10 second, it needs to call commission fee api concurrently.

# Improvement

Project can be dockerized in future.
More test cases can be added to improve covareage rate.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


