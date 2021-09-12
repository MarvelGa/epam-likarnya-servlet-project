CREATE SCHEMA IF NOT EXISTS likarnya77;

use likarnya77;

DROP TABLE IF EXISTS categories, patients, statements, users, medical_cards, treatments;

create table categories
(
    id    bigint auto_increment
        primary key,
    title varchar(50) not null,
    constraint UK_title
        unique (title)
);

create table patients
(
    id         bigint auto_increment
        primary key,
    birth_day  date         not null,
    first_name varchar(25)  not null,
    gender     varchar(255) not null,
    last_name  varchar(25)  not null
);

create table statements
(
    id             bigint auto_increment
        primary key,
    created_at     datetime(6)  not null,
    patient_status varchar(255) not null,
    patient_id     bigint      not null,
    changed        datetime(6)       null,
    constraint FK_patient_id
        foreign key (patient_id) references patients (id)
);

create table users
(
    id          bigint auto_increment
        primary key,
    email       varchar(50)  not null,
    first_name  varchar(50)  not null,
    last_name   varchar(50)  not null,
    password    varchar(50)  not null,
    role        varchar(255) not null,
    category_id bigint           null,
    constraint FK_category_id
        foreign key (category_id) references categories (id)
);

create table medical_cards
(
    id           bigint auto_increment
        primary key,
    complaints   varchar(255) not null,
    diagnosis    varchar(255) null,
    statement_id bigint       not null,
    doctor_id    bigint       not null,
    constraint FK_doctor_id
        foreign key (doctor_id) references users (id),
    constraint FK_statement_id
        foreign key (statement_id) references statements (id)
);

create table treatments
(
    id                 bigint auto_increment
        primary key,
    appointment        varchar(255) not null,
    appointment_status varchar(255) not null,
    changed            datetime(6)  null,
    created_at         datetime(6)  not null,
    executor_id        bigint       null,
    m_card_id    bigint    not   null,
    constraint FK_m_card_id
        foreign key (m_card_id ) references medical_cards (id)
);