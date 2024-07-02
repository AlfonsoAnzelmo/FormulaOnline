drop database if exists formulaOnlineDB;
create database formulaOnlineDB;

use formulaOnlineDB;

create table lettore(
	idLettore int primary key not null,
    email varchar(50) not null,
    pass varchar(50) not null,
    nickname varchar(30) not null, 
    scuderiaPreferita varchar(30),
    dataFineSospensione date,
    moderatore tinyint not null
    
);
create table categoria (
    idCategoria int primary key,
    nome varchar(50) not null,
    descrizione varchar(300),
    categoriaPadre int,
    foreign key(categoriaPadre) references categoria(idCategoria),
    creatore int not null,
    foreign key(creatore) references lettore(idLettore)
);

create table discussione(
	idDiscussione int primary key,
	numeroCommenti int not null,
    categoria int not null,
    titolo varchar(50) not null,
    autore int not null,
    foreign key (categoria) references categoria(idCategoria),
	foreign key (autore) references lettore(idLettore)
);

create table commento ( 
    idCommento int primary key,
    corpo varchar(500) not null,
    discussione int not null,
    foreign key (discussione) references discussione(idDiscussione),
    dataCommento date not null,
    autore int not null,
    foreign key (autore) references Lettore(idLettore)
);


create table segnalazione(
	lettore int not null,
	moderatore int not null,
    commento int not null,
    corpo varchar(250) not null,
    Foreign key(lettore) references lettore(idLettore),
    Foreign key(moderatore) references lettore(idLettore),
    Foreign key(commento) references commento(idCommento),
    primary key(lettore, commento)
);





