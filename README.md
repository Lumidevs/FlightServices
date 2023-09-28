# FlightServices
Used to manage flights
pull the dev branch and Run the application using intellij idea
after running it, on postman, make a GET request to load dummy flights unto a list which is a mock database using 'http://localhost:8080/flights/loadFlights'

After loading the flight, use this to get availiable flight based on cities and dates passed
curl --location 'http://localhost:8080/flights?departureCity=New%20York&destinationCity=Los%20Angeles&date=2023-09-30'


use this to book a flight: curl --location 'http://localhost:8080/flights/book' \
--header 'Content-Type: application/json' \
--data '{
    "userId": "12345",
    "selectedFlight": {
        "flightNumber": "FL123",
        "departureCity": "New York",
        "destinationCity": "Los Angeles",
        "date": "2023-09-30",
        "price": 250.00
    }
}'

it returns the ID of the flight booked which can be used to get the details of the flight later.

After booking a flight, use this to confirm the booked flight: curl --location --request POST 'http://localhost:8080/flights/confirm/?bookingId=B1'
NOTE that the bookingId is passed in as request param
