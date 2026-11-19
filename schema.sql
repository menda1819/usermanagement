create database usermanagement_db;
go

use usermanagement_db;
go

create table dbo.users (
	id bigint identity(1,1) primary key,
	first_name nvarchar(50) not null,
	last_name nvarchar(50) not null,
	email nvarchar(100) not null constraint uq_users_email unique,
	username nvarchar(100) not null constraint uq_users_username unique,
	phone nvarchar(20) null,
	role nvarchar(20) not null constraint ck_users_role check (role in ('ADMIN', 'MANAGER', 'USER', 'admin', 'manager', 'user')),
	active bit not null default 1,
	created_at datetime2 not null default sysutcdatetime(),
	updated_at datetime2 not null default sysutcdatetime()
);

insert into dbo.users (first_name, last_name, email, username, phone, role, active)
values
('Imeth', 'Mendis', 'imeth@gmail.com', 'imethm', '0713176473', 'ADMIN', 1),
('Venuka', 'Sirimanne', 'venuka@gmail.com', 'venukas', '0713176472', 'MANAGER', 1),
('Dassa', 'Rajapakse', 'dassa@gmail.com', 'dassar', '0713176471', 'USER', 1);
