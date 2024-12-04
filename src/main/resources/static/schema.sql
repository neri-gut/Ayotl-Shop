create database ayotl;

use ayotl;

create table cart_products
(
    id         bigint  not null auto_increment,
    cart_id    bigint  not null,
    product_id bigint  not null,
    quantity   integer not null,
    primary key (id)
);

create table carts
(
    id         bigint not null auto_increment,
    user_id    bigint not null,
    created_at date   not null,
    primary key (id)
);

create table categories
(
    id          bigint       not null auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    primary key (id)
);

create table customizations
(
    id           bigint       not null auto_increment,
    product_id   bigint       not null,
    fragrance    varchar(255) not null,
    presentation varchar(255) not null,
    primary key (id)
);

create table offers
(
    id                  bigint         not null auto_increment,
    product_id          bigint         not null,
    discount_percentage decimal(38, 2) not null,
    start_date          datetime(6)    not null,
    end_date            datetime(6)    not null,
    primary key (id)
);

create table orders
(
    id      bigint         not null auto_increment,
    user_id bigint         not null,
    total   decimal(38, 2) not null,
    date    date           not null,
    status  varchar(255)   not null,
    primary key (id)
);

create table product_orders
(
    id         bigint         not null auto_increment,
    order_id   bigint         not null,
    product_id bigint         not null,
    quantity   integer        not null,
    subtotal   decimal(38, 2) not null,
    primary key (id)
);

create table products
(
    id          bigint         not null auto_increment,
    name        varchar(255)   not null,
    description varchar(255)   not null,
    price       decimal(38, 2) not null,
    stock       integer        not null,
    category_id bigint         not null,
    image_path  varchar(255),
    image_mime_type varchar(255),
    created_at  date           not null,
    updated_at  date           not null,
    primary key (id)
);

create table roles
(
    id          bigint       not null auto_increment,
    name        varchar(255) not null,
    description varchar(255) not null,
    primary key (id)
);

create table shipments
(
    id          bigint       not null auto_increment,
    order_id    bigint       not null,
    address     varchar(255) not null,
    city        varchar(255) not null,
    postal_code varchar(255) not null,
    state       varchar(255) not null,
    date        date         not null,
    status      varchar(255) not null,
    primary key (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (role_id, user_id)
);

create table users
(
    id         bigint       not null auto_increment,
    email      varchar(255) not null,
    password   varchar(255) not null,
    created_at datetime(6)  not null,
    updated_at datetime(6)  not null,
    primary key (id)
);

create table users_data
(
    user_id          bigint       not null,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    second_last_name varchar(255)
);


alter table carts
    add constraint uk_ui_c unique (user_id);

alter table cart_products
    add constraint uk_ci_pi_cp unique (cart_id, product_id);

alter table categories
    add constraint uk_n_c unique (name);

alter table shipments
    add constraint uk_oi_s unique (order_id);

alter table users_data
    add constraint uk_ui_ud unique (user_id);

alter table cart_products
    add constraint fk_ci_cp_c foreign key (cart_id) references carts (id);

alter table cart_products
    add constraint fk_pi_cp_p foreign key (product_id) references products (id);

alter table carts
    add constraint fk_ui_c_u foreign key (user_id) references users (id);

alter table customizations
    add constraint fk_pi_c_p foreign key (product_id) references products (id);

alter table offers
    add constraint fk_pi_o_p foreign key (product_id) references products (id);

alter table orders
    add constraint fk_ui_o_u foreign key (user_id) references users (id);

alter table product_orders
    add constraint fk_oi_po_o foreign key (order_id) references orders (id);

alter table product_orders
    add constraint fk_pi_po_p foreign key (product_id) references products (id);

alter table products
    add constraint fk_ci_p_c foreign key (category_id) references categories (id);

alter table shipments
    add constraint fk_oi_s_o foreign key (order_id) references orders (id);

alter table user_roles
    add constraint fk_ri_ur_r foreign key (role_id) references roles (id);

alter table user_roles
    add constraint fk_ui_ur_u foreign key (user_id) references users (id);

alter table users_data
    add constraint fk_ui_ud_u foreign key (user_id) references users (id);