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
insert into recurso_enfermedad(id_enfermedad,id_recurso,recurso_previene, recurso_trata) values (1,2, false, true);
insert into recurso_enfermedad(id_enfermedad,id_recurso,recurso_previene, recurso_trata) values (1,3, true, true);

--Enfermedad-Sintoma
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (1,1);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (1,3);