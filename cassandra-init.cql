CREATE KEYSPACE testtask WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE testtask.houseStats (
    id uuid,
    crime double,
    zn double,
    indus double,
    chas int,
    nox double,
    averagerooms double,
    ageproportion double,
    weighteddistance double,
    rad int,
    tax double,
    ptratio double,
    bindex double,
    lstat double,
    medianvalue double,
    PRIMARY KEY(id)
);
