--TipoRecurso
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (100, 'Antiinflamatorio', 'Antiinflamatorio', null);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (200, 'Alcohol','Alcohol', 2);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (300, 'Seguridad','Seguridad', null);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (400, 'Analgesico','Analgesico', 5);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (500, 'Repelente','Repelente', 3);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (600, 'Desinfectante','Desinfectante', 1);
insert into public.tipo_recurso(id, nombre, descripcion, codigo_periferico) values (700, 'Curitas','Curitas', 4);

---Recurso
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (100, 'Ibuprofeno', 100, 13);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (200, 'Alcohol en Gel', 200, 1);
--insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (300, 'Tapabocas', 300, null);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (400, 'Aspirina', 400, 12);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (500, 'Livopen', 500, 9);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (600, 'Alcohol isopropilico', 400, 2);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (700, 'Lisoform', 600, 3);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (800, 'Lavandina', 600, 4);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (900, 'Mr Musculo', 600, 5);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1000, 'Agua Hane', 600, 6);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1100, 'Repelente off', 500, 7);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1200, 'Fuera mosquitos', 500, 8);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1300, 'Curaflex', 700, 10);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1400, 'Sanasana', 700, 11);
insert into public.recurso(id, nombre, tipo_recurso, codigo_periferico) values (1500, 'Paracetamol', 700, 14);
--TipoEnfermedad
insert into public.tipo_enfermedad(id,nombre) values (100, 'Virus');
insert into public.tipo_enfermedad(id,nombre) values (200, 'Bacteria');
insert into public.tipo_enfermedad(id,nombre) values (300, 'Hongo');

--Sintoma
insert into public.sintoma(id,nombre) values (100, 'Cefalea');
insert into public.sintoma(id,nombre) values (200, 'Vomitos');
insert into public.sintoma(id,nombre) values (300, 'Fiebre');
insert into public.sintoma(id,nombre) values (400, 'Dolor estomacal');
insert into public.sintoma(id,nombre) values (500, 'Dolor de espalda');
insert into public.sintoma(id,nombre) values (600, 'Dolor de cabeza');
insert into public.sintoma(id,nombre) values (700, 'Sarpullido');
insert into public.sintoma(id,nombre) values (800, 'Escalofrios');
insert into public.sintoma(id,nombre) values (900, 'Cansancio');
insert into public.sintoma(id,nombre) values (1000, 'Tos');
insert into public.sintoma(id,nombre) values (1100, 'Nauseas');

--Enfermedad
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (100,false,'COVID-19','SARS-CoV-2',100,false,1);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (200,true,'SIDA','VIH',100,false,0);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (300,true,'Dengue','DENV',100,false,0);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (400,true,'Antrax','Bacillus anthracis',200,false,3);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (500,true,'Gripe Comun',' virus de la influenza',100,false,2);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (600,true,'Gripe Aviar',' cepas tipo A',100,false,2);
insert into public.enfermedad(id, aprobada, nombre, nombre_agente, tipo_enfermedad, rechazada, distancia_contagio) values (700,true,'Hepatitis','VHA',100,false,0);
--Enfermedad-Recurso
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (false, true, 100,200);
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, false, 300,1100);
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, false, 300,1200);
insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, false, 100,700);

--insert into recurso_enfermedad(recurso_previene, recurso_trata,id_enfermedad,id_recurso) values (true, true,100,300);

--Enfermedad-Sintoma
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,100);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (100,300);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (400,900);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (400,800);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (400,1000);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (400,600);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (400,200);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (500,300);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (500,900);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (500,600);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (500,1000);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (600,900);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (600,600);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (600,1000);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (700,400);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (700,300);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (700,900);
insert into enfermedad_sintoma(id_enfermedad,id_sintoma) values (700,1100);

--Proveedor
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico,barrio,ciudad,direccion) values('RECURSO', 100, 'Roemers',false,'1','Cerrito','Montevideo','Jose Batlle y Ordoniez 1154');
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico,barrio,ciudad,direccion) values('RECURSO', 200, 'Quimfa',false,'2','Centro','Montevideo','18 y andes');
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico,barrio,ciudad,direccion) values('RECURSO', 600, 'sanroque',false,'3','Centro','Montevideo','mercedes y convencion');
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico,barrio,ciudad,direccion) values('RECURSO', 700, 'PocitosFarm',false,'4','Pocitos','Montevideo','21 de setiembre y av brasil');
insert into proveedor(proveedor_tipo, id, nombre,deleted,codigo_periferico,barrio,ciudad,direccion) values('RECURSO', 800, 'KwikEMart',false,'5','Aguada','Montevideo','av siempreviva 742');

insert into proveedor(proveedor_tipo, id, nombre, ciudad, barrio, direccion, rangohorario, deleted) values('EXAMEN', 300, 'labSA','Montevideo', 'Centro','18 y ejido','24 hs', false);
insert into proveedor(proveedor_tipo, id, nombre, ciudad,  barrio , direccion, rangohorario,deleted) values('EXAMEN', 400, 'BillGates Foundation','Montevideo', 'Malvin','Amazonas 1456 apto 402','24 hs',false);
insert into proveedor(proveedor_tipo, id, nombre, ciudad,  barrio, direccion,  rangohorario,deleted) values ('EXAMEN', 500, 'Algun prov', 'Montevideo','Centro', 'Canelones 2222', '24 hs',false);

--RecursoProveedor
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(22,100,200);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(35,100,600);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(35,200,700);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(22,200,800);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(60,600,800);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(55,600,900);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(105,700,1000);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(105,700,1100);
insert into public.recurso_proveedor(cantidad,id_proveedor,id_recurso) values(245,800,1200);
-- PrestadoraSalud
insert into prestadora_salud (id, nombre, deleted, id_prestadora_rucaf) values (100, 'Servicio Medico Integral - SMI', false,1);
insert into prestadora_salud (id, nombre, deleted, id_prestadora_rucaf) values (101, 'Medica Uruguaya', false,2);

--Examen
insert into examen(id,nombre,enfermedad_id) values(100,'Hisopado',100);
insert into examen(id,nombre,enfermedad_id) values(200,'Conteo globulos',200);
insert into examen(id,nombre,enfermedad_id) values(300,'Detectar Bacillus anthracis ',400);
insert into examen(id,nombre,enfermedad_id) values(400,'MAC ELISA ',300);
insert into examen(id,nombre,enfermedad_id) values(500,'Hisopado N2 ',500);
insert into examen(id,nombre,enfermedad_id) values(600,'Hisopado N3 ',600);

--proveedorexamen-examen
insert into proveedorexamen_examen(id_proveedor, id_examen) values (300,100);
insert into proveedorexamen_examen (id_proveedor, id_examen) values (500, 100);
insert into proveedorexamen_examen(id_proveedor, id_examen) values (300,200);
insert into proveedorexamen_examen (id_proveedor, id_examen) values (500, 200);

--Departamento
insert into departamento(id,nombre) values(100, 'Montevideo');
insert into departamento(id,nombre) values(200, 'Canelones');
insert into departamento(id,nombre) values(300, 'Maldonado');
insert into departamento(id,nombre) values(400, 'Soriano');
insert into departamento(id,nombre) values(500, 'Flores');
insert into departamento(id,nombre) values(600, 'Florida');
insert into departamento(id,nombre) values(700, 'Trenta y tres');
insert into departamento(id,nombre) values(800, 'Durazno');
insert into departamento(id,nombre) values(900, 'Tacuarembo');
insert into departamento(id,nombre) values(1000, 'Cerro largo');
insert into departamento(id,nombre) values(1100, 'Paysandu');
insert into departamento(id,nombre) values(1200, 'Rio negro');
insert into departamento(id,nombre) values(1300, 'Rocha');
insert into departamento(id,nombre) values(1400, 'Sanjose');
insert into departamento(id,nombre) values(1500, 'Lavalleja');
insert into departamento(id,nombre) values(1600, 'Rivera');
insert into departamento(id,nombre) values(1700, 'Salto');
insert into departamento(id,nombre) values(1800, 'Artigas');
insert into departamento(id,nombre) values(1900, 'Colonia');
--Usuario
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('administrador',100,'Farcilli','maxifarcilli@gmail.com','Lorenzo Fernandez 3248','1992-08-09 00:00:00','Uruguayo','Maximiliano','21232F297A57A5A743894A0E4A801FC3',false,'admin', false);
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('gerente',101,'Farcilli','maxifarcilli@gmail.com','Lorenzo Fernandez 3248','1992-08-09 00:00:00','Uruguayo','Maximiliano','21232F297A57A5A743894A0E4A801FC3',false,'gerente', false);
insert into usuario(tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, deleted) values('ciudadano',102,'javi','javierms17@gmail.com','18 de julio','1991-1-1 00:00:00','Uruguayo','javi','21232F297A57A5A743894A0E4A801FC3',false,'ciudadano', false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('medico', 103, 'Del Covid', 'esclavo.delcovid@smi.ccc', 'Bulevar 1325', '1975-07-02', 'Uruguayo', 'Esclavo', '21232F297A57A5A743894A0E4A801FC3', false, 'edelcovid', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('medico', 104, 'Perez', 'juan.perez@medicauruguaya.ccc', '18 de Julio 2566 apto 507', '1983-03-19', 'Uruguayo', 'Juan', '21232F297A57A5A743894A0E4A801FC3', false, 'jperez', false, 101, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 105, 'Garcia', 'pedro.garcia@algo.ccc', 'Martin Fierro 2556', '1990-12-31', 'Uruguayo', 'Pedro', '21232F297A57A5A743894A0E4A801FC3', false, 'pedrogarcia', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 106, 'Mortal', 'simple.mortal@algo.ccc', 'Dirección 1122 apto 111', '1980-11-05', 'Uruguayo', 'Simple', '21232F297A57A5A743894A0E4A801FC3', false, 'simplemortal', false, 101, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 107, 'Hercules', 'Hercules@Olimpo.com', 'Atenas', '1980-11-05', 'Atenas', 'Semidios', '21232F297A57A5A743894A0E4A801FC3', false, 'Semidios', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 108, 'Odin', 'odin@Valhalla.com', 'Valhalla', '1980-11-05', 'Noruega', 'Odin', '21232F297A57A5A743894A0E4A801FC3', false, 'diosOdin', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 109, 'bin laden', 'binladen@torresgemelas.com', 'Medio Oriente', '1980-11-05', 'Arabia', 'Osama', '21232F297A57A5A743894A0E4A801FC3', false, 'osamabinladen', false, 100, false);
insert into usuario (tipo_usuario, id, apellido, correo, direccion, fecha_nacimiento, nacionalidad, nombre, password, primer_ingreso, username, conectado, prestadorasalud_id, deleted) values ('ciudadano', 110, 'Cristo', 'Jebus@paraiso.com', 'Cielo', '1980-11-05', 'Cielo', 'Jebus', '21232F297A57A5A743894A0E4A801FC3', false, 'jebus', false, 100, false);
-- Fuentes de datos
insert into fuente_de_datos (id, codigo, deleted, url) values (100, 'Twitter', false, 'https://twitter.com/');
insert into fuente_de_datos (id, codigo, deleted, url) values (200, 'OMS', false, 'https://www.who.int/es');
insert into fuente_de_datos (id, codigo, deleted, url) values (300, 'MSP', false, 'https://www.gub.uy/ministerio-salud-publica/');
--Casos
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(100,null,'2020-5-26 00:00:00',0,106,200,100, 100,103,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(200,null,'2020-5-15 00:00:00',0,106,100,200, 200,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(300,null,'2020-5-15 00:00:00',0,107,100,300, 400,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(400,null,'2020-5-15 00:00:00',0,107,100,400, 300,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(500,null,'2020-5-15 00:00:00',0,107,600,400, 300,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(600,'2020-1-1 00:00:00','2020-3-15 00:00:00',1,108,300,400, 300,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(700,'2020-1-1 00:00:00','2020-3-15 00:00:00',1,108,400,500, 500,104,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(800,'2020-3-4 00:00:00','2020-4-15 00:00:00',1,108,400,500, 500,103,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(900,'2020-3-4 00:00:00','2020-5-15 00:00:00',1,109,500,500, 500,103,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(1000,'2020-5-5 00:00:00','2020-6-15 00:00:00',1,109,300,600, 600,103,300,false);
insert into caso (id,fecha_confirmado, fecha_sospechoso, tipocaso,ciudadano_id, departamento_id,enfermedad_id, examen_id, medico_id,proveedorexamen_id, notificacion_enviada ) values(1100,'2020-5-5 00:00:00','2020-6-15 00:00:00',1,110,300,600, 600,103,300,false);

--Suscripciones
insert into suscripcion(id,ciudadano_id,barrio,recurso,notificado) values(100,102,'Cerrito','Repelente off',false);

--Configuracion de notificaciones
INSERT INTO configuracion_notificaciones(tipo, notificarciudadano, notificargerentes, notificarmedico) VALUES ('CAMBIOESTADOCASO', true, false, true);