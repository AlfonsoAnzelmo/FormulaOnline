use formulaonlinedb;
INSERT INTO lettore values
(null, 'admin@email.com', 'password', 'admin', 'Ferrari', null, true),
(null, 'moderatore@email.com', 'password', 'mod', 'Mercedes', null, true),
(null, 'user@gmail.com', 'user1234', 'utente1', null, null, false),
(null, 'utente2@koala.org', 'koalalove', 'koala', null, date('2024-07-07'), false);
select * from lettore;

INSERT INTO categoria values
(null, 'Nessuna', 'Off topics', null, 1),
(null, 'gp 2024', 'tutte le discussioni riguardo il gran premio 2024', null, 1),
(null, 'gp 2023', 'tutte le discussioni riguardo il gran premio 2023', null, 2),
(null, 'Ferrari', 'tutte le discussioni riguardo la ferrari nel gran premio 2024', 2, 1),
(null, 'LeClerc', 'tutte le discussioni leclerc', 4, 1);
select * from categoria;

INSERT into discussione values
(null, 0, 2, 'gara 1', 1),
(null, 0, 2, 'gara 2', 2),
(null, 0, 2, 'gara 3', 2),
(null, 0, 3, 'gara finale', 3),
(null, 0, 1, 'ruote di scorta', 3),
(null, 0, 4, 'calzini', 1);
select * from discussione;

insert into commento values
(null, 'Lorem ipsum', 5, '2024-07-09 01:03:50', 3),
(null, 'dolor sit amet', 3, '2024-07-09 01:55:53', 2),
(null, 'consectetur adipiscing elit, ', 1, '2024-07-09 03:57:10', 1),
(null, 'sed do eiusmod tempor incididunt ut labore', 5, '2024-07-09 04:26:35', 1),
(null, 'et dolore magna aliqua', 5, '2024-07-09 05:04:10', 1),
(null, 'Ut enim ad minim veniam', 6, '2024-07-09 09:41:27', 1),
(null, 'et dolore magna aliqua', 6, '2024-07-09 09:46:24', 4),
(null, 'ullamco laboris nisi ut aliquip ex', 3, '2024-07-09 10:46:39', 1),
(null, 'ea commodo consequat', 4, '2024-07-09 11:01:52', 3),
(null, 'Duis aute irure dolor ', 2, '2024-07-09 11:57:58', 2),
(null, 'in reprehenderit in voluptate', 1, '2024-07-09 14:31:36', 4),
(null, 'velit esse cillum', 4, '2024-07-09 19:48:04', 2);
select * from commento;