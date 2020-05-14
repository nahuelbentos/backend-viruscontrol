---Recurso
insert into public.recurso(id, nombre) values (1, 'Ibuprofeno');
insert into public.recurso(id, nombre) values (2, 'Alcohol en Gel');
insert into public.recurso(id, nombre) values (3, 'Tapabocas');

--TipoEnfermedad
insert into public.tipoenfermedad(id,nombre) values (1, 'Virus');
insert into public.tipoenfermedad(id,nombre) values (2, 'Bacteria');

--Sintoma
insert into public.sintoma(id,nombre) values (1, 'Cefalea');
insert into public.sintoma(id,nombre) values (2, 'Vomitos');
insert into public.sintoma(id,nombre) values (3, 'Fiebre');

--Enfermedad
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipoenfermedad) values (1,false,'COVID-19','SARS-CoV-2',1);

--Enfermedad-Recurso
insert into enfermedad_recurso(id_enfermedad,id_recurso) values (1,2);
insert into enfermedad_recurso(id_enfermedad,id_recurso) values (1,3);

--Enfermedad-Sintoma
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (1,1);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (1,3);