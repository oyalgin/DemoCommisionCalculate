# DemoCommisionCalculate

DemoCommisionCalculate is a Springboot applicaton which is conducted as console application.
Application pulls data from Orders file every 10 second, 
then process and write order's commission fee to another file.
Process lasts 100 seconds.

## PreRequsite

Install Java 8 

placed Order.txt file in C:/temp
it has to include orders records. Each line compromise 2 columns which are order name and price that are seperated blank or tab.

# Run the Application

clone the project or download as a zip 
go to demo-sheduler\src\main\java\com\fana\demosheduler 
To run the application, run the following command in a terminal window (in the complete) directory:

./mvnw spring-boot:run

What you will see on the terminal 

Each 10 second task pulling data and processing orders will run
You can see the logs about it.

# Challenge about project 

To calculate commisions in a 10 second, it needs to call commission fee api concurrently.
Each api call takes 0.5 seconds and sequentailly api call doesn't work to meet requirements.

# Improvement

Project can be dockerized in future.
More test cases can be added to improve covareage rate.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


