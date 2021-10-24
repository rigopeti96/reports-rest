CREATE TABLE Report (
  id INTEGER NOT NULL,
  reportType VARCHAR(255) NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  stationName VARCHAR(255) NOT NULL,
  report_date DATE NOT NULL,
  report_date_until DATE NOT NULL,
  reporter_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);