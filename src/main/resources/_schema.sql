--drop schema cccat15 cascade;
--
--create schema cccat15;
--
--create table cccat15.account (
--	account_id uuid primary key,
--	name text not null,
--	email text not null,
--	cpf text not null,
--	car_plate text null,
--	is_passenger boolean not null default false,
--	is_driver boolean not null default false
--);