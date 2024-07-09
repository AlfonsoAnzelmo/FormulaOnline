
drop database if exists formulaOnlineDB;
create database formulaOnlineDB;

use formulaOnlineDB;

create table lettore(
	idLettore int auto_increment primary key not null ,
    email varchar(50) not null unique,
    pass varchar(50) not null,
    nickname varchar(30) not null unique,
    scuderiaPreferita varchar(30),
    dataFineSospensione date,
    moderatore tinyint not null default false
);

create table categoria (
    idCategoria int auto_increment primary key,
    nome varchar(50) not null,
    descrizione varchar(300),
    categoriaPadre int,
    foreign key(categoriaPadre) references categoria(idCategoria),
    creatore int not null default 0,
    foreign key(creatore) references lettore(idLettore)
		-- on delete set default
		on update cascade
);

create table discussione(
	idDiscussione int auto_increment primary key,
	numeroCommenti int not null default 0,
    categoria int not null,
    titolo varchar(50) not null,
    autore int not null,
    foreign key (categoria) references categoria(idCategoria)
		on update cascade
		on delete cascade,
	foreign key (autore) references lettore(idLettore)
		on update cascade
		-- on delete set 1
);

create table commento ( 
    idCommento int auto_increment primary key,
    corpo varchar(500) not null,
    discussione int not null,
    foreign key (discussione) references discussione(idDiscussione)
                      on update cascade
                      on delete cascade,
    dataCommento datetime DEFAULT CURRENT_TIMESTAMP,
    autore int not null,
    foreign key (autore) references Lettore(idLettore)
		on update cascade
		-- on delete set default non funziona con innodb
);


create table segnalazione(
    idSegnalazione int primary key,
	lettore int not null,
    commento int not null,
    corpo varchar(250) not null,
    Foreign key(lettore) references lettore(idLettore)
		on update cascade
		on delete cascade,
    Foreign key(commento) references commento(idCommento)
		on update cascade
		on delete cascade
);
