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
    `package_id` int not null,
    `container_id` int not null,
    primary key (`package_id`, `container_id`),
    constraint package_container_container_id_fk
        foreign key (`container_id`) references `container`(`id`) on delete cascade,
    constraint package_container_package_id_fk
        foreign key (`package_id`) references `package`(`id`) on delete cascade
);