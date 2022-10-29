# DemoCommisionCalculate

DemoCommisionCalculate is a Springboot applicaton which is constructed as a console application.
In every 10 seconds Application pulls orders kept in Orders.txt file.
then calculates commmission fee by calling this api https://api-rate.fanatest.workers.dev/?price=?

Orders file includes 1000 records which should be processed in 100 seconds by scheduled task in this application.

## PreRequsite

Install Java 8 

place Order.txt file in C:/temp
it has to include orders records. Each line compromise 2 columns which are order name and price that are seperated by blank or tab.

# Run the Application

clone the project or download as a zip 

go to demo-sheduler\src\main\java\com\fana\demosheduler 
To run the application, run the following command in a terminal window (in the complete) directory:

./mvnw spring-boot:run


# Challenge about project 

To calculate 100 commisions in a 10 second, application calls commission fee api concurrently.
Api returns result in 0.5 seconds and calling api sequentially  doesn't meet requirements. We have to enhance performance by using concurreny.

# Improvement

Project can be dockerized in future.
More test cases can be added to improve covareage rate.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


