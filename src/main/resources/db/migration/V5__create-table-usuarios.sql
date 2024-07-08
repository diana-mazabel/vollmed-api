create table usuarios(

    id bigserial not null,
    loginUser varchar(100) not null,
    loginPassword varchar(300) not null,

    primary key(id)

);