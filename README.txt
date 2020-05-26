Si el proyecto se corre local en Eclipse: 

Se debe configurar en las preferencias del proyecto, la preferencia JBossWS para poder consumir web services.

Ir a Window -> Preferences -> Web Services -> JBossWS Preferences -> Add -> En el campo "Home folder" indicar la ruta a la instalación de wildfly
NOTA: se notará que se llegó a la carpeta indicada cuando el mensaje 
    "Error occurred while loading JBossWS Command. Select the correct JBoss Server folder."
    cambie por
    "Create a JBossWS Runtime"
===================================
Si se quiere probar en mi nube:

Los siguientes Casos de Uso críticos pueden ser probados desde el BackOffice que ya se encuentra deployado y corriendo en MiNube en la siguiente URL:
http://179.27.96.131:8080/viruscontrol-web/login.xhtml

Nota: En caso de que el mismo se encuentre bajo, se puede iniciar logueandose en el servidor por SSH ejecutando ./iniciarWildfly.sh

Admin (user: admin / password: admin)

Autenticación de usuario (Interna) (CU2)
Gestión de nodos periféricos > Alta Proveedores - Recursos y exámenes (CU3)
Gestión de nodos periféricos > Alta Prestador de salud (CU4)
Gestión de enfermedades  > Aprobar Enfermedad Infecciosa (CU8)


Gerente (username: gerente / password: admin)

Autenticación de usuario (Interna) (CU2)
Gestión de enfermedades > Alta enfermedad infecciosa (CU8)
Gestión de enfermedades > Alta de recurso recomendado para enfermedad (CU9)