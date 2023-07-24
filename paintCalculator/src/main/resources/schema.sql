drop table if exists paint_details;

create table paint_details(
id int auto_increment primary key,
user_id varchar not null,
room_length int,
room_height int,
door_size int,
number_of_doors int,
window_size int,
number_of_windows int,
is_trim_required char default 'N',
trim_size int,
paint_required float,
price float
);