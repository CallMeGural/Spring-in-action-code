create table if not exists Ingredient (
    id varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco (
    id bigint primary key AUTO_INCREMENT,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4)
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
    id bigint primary key AUTO_INCREMENT,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null,
    orderedBy bigint not null
);

create table if not exists Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

create table if not exists Purchaser (
    id bigint primary key AUTO_INCREMENT,
    username varchar(30) not null,
    password varchar(50) not null,
    fullname varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    phoneNumber varchar(9) not null
    );

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco(id);
alter table Taco_Order
    add foreign key (orderedBy) references Purchaser(id);

