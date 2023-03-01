create schema `kpac`;
use `kpac`;

create table `package` (
    `id` int primary key auto_increment,
    `title` varchar(250) not null,
    `description` varchar(2000) not null,
    `createdAt` date not null
);

create table `container` (
    `id` int primary key auto_increment,
    `title` varchar(250) not null
);

create table `package_container` (
    `package_id` int not null references `package`(`id`),
    `container_id` int not null references `container`(`id`)
);