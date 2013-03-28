CityFinder
==========
This Android application responds to the following requirements:

• Store a database of all of the zip codes in the United States along with geographic and demographic information.  See the Recommended Approach section for the raw data file.
•	The zip code data has a many-to-many relationship between zip code and locality name.  One locality may have multiple zip codes and one zip code may have multiple localities.
•	The zip code data originates in a CSV file.  The application should read and convert this CSV file into a local database only on the first run of the application on the phone.  Subsequent runs must use the local database created in the first run.  You may pre-convert the data into the appropriate SQL database prior to deployment.
•	The user must be able to search by zip code or locality name to find all localities that match.
•	Free text queries are not required.
•	Users must be able to search by partial zip code or partial locality. Localities are just the city name.  For example if a user searches for Richmond they should find both Richmond, VA and Richmond, IN.
•	The search results must be displayed in a view that displays all of the results without requiring user driven pagination (i.e. infinite tables).
•	The view for each entry must display the zip code, city, state, zip
•	If the user selects an entry the app must open a new view with a map centered on the locality.  
•	The map must display a pin centered on the lat/lng of the location.
•	If the user taps the map pin the app should transition to a new page that displays the full demographic data for the locality.
•	The application should avoid creating SQL queries to the greatest extent possible.  I.e. use an ORM if available.
•	The application must use the embedded SQL engine provided by the platform.
•	The application must conform to the HiG of the running device.
