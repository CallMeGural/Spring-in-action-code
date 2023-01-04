delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type)
values ('FLTO', 'pszenna', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'kukurydzianaaaaa', 'WRAP');
insert into Ingredient (id, name, type)
values ('GRBF', 'mielona wołowina', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'kawałki mięsa', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'krojone pomidory', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'sałata', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'sos salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'śmietana', 'SAUCE');