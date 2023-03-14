# db-sakila

[sakila Database](https://dev.mysql.com/doc/sakila/en/)
 
Consul based Java program (JDBC) for  sakila Database query

The program contains 6 options:
1. Adding a new movie to the system
2. Adding a new player to the system
3. Adding information about an actor who played in the movie
4. Execution of a user query (without correctness check)
5. Pre-built queries:
    1. Search for a movie by a word that appears in the name of the movie.
    2. Search all movies in which an actor appeared by the actor's name.
    3. Search all actors who appeared in movies in a certain language by language
    4. Searching for a movie that, according to the database, is played by exactly X actors
    5. Search for an English language movie that did not star Robert De Niro.
6. Exiting the system
