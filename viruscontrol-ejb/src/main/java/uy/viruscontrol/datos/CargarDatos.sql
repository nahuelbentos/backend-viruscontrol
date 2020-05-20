---Recurso
insert into public.recurso(id, nombre) values (100, 'Ibuprofeno');
insert into public.recurso(id, nombre) values (200, 'Alcohol en Gel');
insert into public.recurso(id, nombre) values (300, 'Tapabocas');

--TipoEnfermedad
insert into public.tipoenfermedad(id,nombre) values (100, 'Virus');
insert into public.tipoenfermedad(id,nombre) values (200, 'Bacteria');

--Sintoma
insert into public.sintoma(id,nombre) values (100, 'Cefalea');
insert into public.sintoma(id,nombre) values (200, 'Vomitos');
insert into public.sintoma(id,nombre) values (300, 'Fiebre');

--Enfermedad
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipoenfermedad) values (100,false,'COVID-19','SARS-CoV-2',100);

--Enfermedad-Recurso
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (false, true, 100,200);
--insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, true,100,300);

--Enfermedad-Sintoma
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,100);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,300);

--Proveedor
insert into proveedor(proveedor_tipo, id, nombre) values('RECURSO', 100, 'Roemers');
insert into proveedor(proveedor_tipo, id, nombre) values('RECURSO', 200, 'Quimfa');

--Usuario
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username) values('administrador',100,'Farcilli','maxifarcilli@gmail.com','Lorenzo Fernandez 3248','1992-08-09 00:00:00','Uruguayo','Maximiliano','21232F297A57A5A743894A0E4A801FC3',false,'maxifarkuso');


