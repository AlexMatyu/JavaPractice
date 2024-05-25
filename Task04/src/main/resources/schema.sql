CREATE TABLE Users (
	   id SERIAL PRIMARY KEY,
	   username VARCHAR,
	   fio VARCHAR
);

CREATE TABLE Logins (
		id SERIAL PRIMARY KEY,
		access_date TIMESTAMP,
		user_id INTEGER REFERENCES Users(id),
		application VARCHAR
);