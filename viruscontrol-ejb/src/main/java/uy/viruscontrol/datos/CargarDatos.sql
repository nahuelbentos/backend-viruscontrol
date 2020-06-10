--TipoRecurso
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (100, 'Antiinflamatorio', 'Antiinflamatorio', null);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (200, 'Alcohol en Gel','Alcohol en Gel', null);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (300, 'Seguridad','Seguridad', null);

---Recurso
insert into public.recurso(id, nombre, tipo_recurso) values (100, 'Ibuprofeno', 100);
insert into public.recurso(id, nombre, tipo_recurso) values (200, 'Alcohol en Gel', 200);
insert into public.recurso(id, nombre, tipo_recurso) values (300, 'Tapabocas', 300);

--TipoEnfermedad
insert into public.tipo_enfermedad(id,nombre) values (100, 'Virus');
insert into public.tipo_enfermedad(id,nombre) values (200, 'Bacteria');

--Sintoma
insert into public.sintoma(id,nombre) values (100, 'Cefalea');
insert into public.sintoma(id,nombre) values (200, 'Vomitos');
insert into public.sintoma(id,nombre) values (300, 'Fiebre');

--Enfermedad
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (100,false,'COVID-19','SARS-CoV-2',100,false,1);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (200,true,'SIDA','VIH',100,false,0);
--Enfermedad-Recurso
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (false, true, 100,200);
--insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, true,100,300);

--Enfermedad-Sintoma
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,100);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,300);

--Proveedor
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico) values('RECURSO', 100, 'Roemers',false,'COMCHI');
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico) values('RECURSO', 200, 'Quimfa',false,'PRJUAN');
insert into proveedor(proveedor_tipo, id, nombre, barrio, direccion, rangohorario, deleted) values('EXAMEN', 300, 'labSA','centro','18 y ejido','24 hs', false);
insert into proveedor(proveedor_tipo, id, nombre, barrio , direccion, rangohorario,deleted) values('EXAMEN', 400, 'BillGates Foundation','Xanadu','Seattle','24 hs',false);
insert into proveedor(proveedor_tipo, id, barrio, direccion, nombre, rangohorario,deleted) values ('EXAMEN', 500, 'Centro', 'Canelones 2222', 'Algun prov', '24 hs',false);

-- PrestadoraSalud
insert into prestadora_salud (id, nombre, deleted, id_prestadora_rucaf) values (100, 'Servicio Medico Integral - SMI', false,1);
insert into prestadora_salud (id, nombre, deleted, id_prestadora_rucaf) values (101, 'Medica Uruguaya', false,2);

--Examen
insert into examen(id,nombre,enfermedad_id) values(100,'hisopado',100);
insert into examen(id,nombre,enfermedad_id) values(200,'conteo globulos',200);

--proveedorexamen-examen
insert into proveedorexamen_examen(id_proveedor, id_examen) values (300,100);
insert into proveedorexamen_examen (id_proveedor, id_examen) values (500, 100);
insert into proveedorexamen_examen(id_proveedor, id_examen) values (300,200);
insert into proveedorexamen_examen (id_proveedor, id_examen) values (500, 200);

--Departamento
insert into departamento(id,nombre) values(100, 'Montevideo');
insert into departamento(id,nombre) values(200, 'Canelones');
insert into departamento(id,nombre) values(300, 'Maldonado');

--Usuario
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('administrador',100,'Farcilli','maxifarcilli@gmail.com','Lorenzo Fernandez 3248','1992-08-09 00:00:00','Uruguayo','Maximiliano','21232F297A57A5A743894A0E4A801FC3',false,'admin', false);
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('gerente',101,'Farcilli','maxifarcilli@gmail.com','Lorenzo Fernandez 3248','1992-08-09 00:00:00','Uruguayo','Maximiliano','21232F297A57A5A743894A0E4A801FC3',false,'gerente', false);
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('ciudadano',102,'javi','javierms@gmail.com','18 de julio','1991-1-1 00:00:00','Uruguayo','javi','21232F297A57A5A743894A0E4A801FC3',false,'ciudadano', false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('medico', 103, 'Del Covid', 'esclavo.delcovid@smi.ccc', 'Bulevar 1325', '1975-07-02', 'Uruguayo', 'Esclavo', '21232F297A57A5A743894A0E4A801FC3', false, 'edelcovid', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('medico', 104, 'Perez', 'juan.perez@medicauruguaya.ccc', '18 de Julio 2566 apto 507', '1983-03-19', 'Uruguayo', 'Juan', '21232F297A57A5A743894A0E4A801FC3', false, 'jperez', false, 101, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 105, 'Garcia', 'pedro.garcia@algo.ccc', 'Martin Fierro 2556', '1990-12-31', 'Uruguayo', 'Pedro', '21232F297A57A5A743894A0E4A801FC3', false, 'pedrogarcia', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 106, 'Mortal', 'simple.mortal@algo.ccc', 'Dirección 1122 apto 111', '1980-11-05', 'Uruguayo', 'Simple', '21232F297A57A5A743894A0E4A801FC3', false, 'simplemortal', false, null, false);

-- Fuentes de datos
insert into fuente_de_datos (id, codigo, deleted, url) values (100, 'Twitter', false, 'https://twitter.com/');

--Casos
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id ) values(100,'2020-6-3 00:00:00','2020-5-26 00:00:00',0,106,200,100, 100,103,300);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id ) values(200,'2020-6-6 00:00:00','2020-5-15 00:00:00',1,106,100,200, 200,104,300);