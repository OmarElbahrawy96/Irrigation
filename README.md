# Irrigation
Automatic irrigation system (Springboot)

#Welcome to Automatic irrigation system

Prerequisites:
-------------
1-MySql Database
2-IDE to import Java springboot project


Configurations:
--------------
In application.properties file add username and password for database connection


IMPORTANT NOTES:
---------------
Before Second time running application remove # from this line "spring.jpa.hibernate.ddl-auto=update" and place it to this one "spring.jpa.hibernate.ddl-auto=create" if you want to keep inserted data from first run




/* GET method */
/* To get all plots */
http://localhost:8080/api/plots

/* GET method */
/* to get spacific plot */
http://localhost:8080/api/plots/{plotId}


/* POST method */
/* to create new plot */
http://localhost:8080/api/plots

/* body JSON */
/*
{
    "supervisorName" : "Sam",
    "supervisorEmail" : "sam.will@cornola.com",
    "soil_type" : "dry",
    "longitude" : 10.0,
    "latitude" : 10.0,
    "length" : 50.0,
    "width" : 40.0,
    "status" : "DRY"
}
*/


/* PUT method */
/* to update plot data */
http://localhost:8080/api/plots

/* body JSON */
/*
{
    "supervisorName" : "Sam",
    "supervisorEmail" : "sam.will@cornola.com",
    "soil_type" : "dry",
    "longitude" : 10.0,
    "latitude" : 10.0,
    "length" : 50.0,
    "width" : 40.0,
    "status" : "DRY"
}
*/


/* DELETE method */
/* to delete spacific plot */
http://localhost:8080/api/plots/{plotId}



/* POST method */
/* to Configure plot irrigation time */
http://localhost:8080/api/configure

/* 
Request parameters {plotId, irrigationId}
*/


/* GET method */
/* to get plot irrigation time slots */
http://localhost:8080/api/plot/{plotId}/times


/* GET method */
/* to get all time slots */
http://localhost:8080/api/times


/* GET method */
/* to get spacific time slot */
http://localhost:8080/api/times/{timeId}


/* POST method */
/* to add new irrigation time slot */
http://localhost:8080/api/times

/* body JSON */
/*
{
    "amount_of_water" : 50,
    "eventDatetime" : "16:57:00",
    "plots" : {}
}
*/


/* PUT method */
/* to update irrigation time slot */
http://localhost:8080/api/times

/* body JSON */
/*
{
    "amount_of_water" : 50,
    "eventDatetime" : "16:57:00",
    "plots" : {}
}
*/


/* DELETE method */
/* to delete spacific irrigation time slot */
http://localhost:8080/api/times/{timeId}

   
/* GET method */
/* to call irrigation method for specific slot */
http://localhost:8080/api/irrigate/{plotId}

