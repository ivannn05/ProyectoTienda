<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bonsai Sur</title>
<!-- CSS -->
<link rel="stylesheet" href="estilo.css">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
</head>
<body>

	<!-- Barra de Navegación -->
	<nav class="navbar navbar-expand-lg navbar-dark">
		<div class="container-fluid">
			<!-- Logo -->
			<a class="navbar-brand" href="#"> <img
				src="imagenes/LogoTienda.jpg" alt="Logo Bonsai Sur"> BONSAI
				SUR
			</a>
			<!-- Botón hamburguesa -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<!-- Menú -->
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/index.jsp">INICIO</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="arbolesDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">ARBOLES</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/arboles.jsp">Bonsáis</a></li>
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/arboles.jsp">Prebonsáis</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle"
						href="<%=request.getContextPath()%>/macetas.jsp"
						id="macetasDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">MACETAS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/macetas.jsp">Cerámica</a></li>
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/macetas.jsp">Plástico</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle"
						href="<%=request.getContextPath()%>/abonos.jsp"
						id="abonosDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">ABONOS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/abonos.jsp">Orgánicos</a></li>
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/abonos.jsp">Químicos</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="herramientasDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">HERRAMIENTAS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/herramientas.jsp">Corte</a></li>
							<li><a class="dropdown-item"
								href="<%=request.getContextPath()%>/herramientas.jsp">Mantenimiento</a></li>
						</ul></li>
				</ul>
				<!-- Barra de búsqueda -->
				<form class="d-flex me-3">
					<input class="form-control me-2" type="search"
						placeholder="Buscar..." aria-label="Search">
					<button class="btn btn-outline-light" type="submit">
						<i class="bi bi-search"></i>
					</button>
				</form>
				<!-- Íconos -->
				<a href="<%=request.getContextPath()%>/carrito.jsp"
					class="me-3 icono"><i class="bi bi-cart"></i></a> <a
					href="<%=request.getContextPath()%>/login.jsp" class="icono"><i
					class="bi bi-person"></i></a>
			</div>
		</div>
	</nav>

	<!-- Bloque de Contenido -->
	<div class="container my-4">
		<h2 class="text-center mb-4">Acceso a tu Administración</h2>
		        <!-- Pestañas para alternar entre Login, Registro, Actualización y Eliminación -->
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <button class="nav-link active" id="login-tab" data-bs-toggle="tab" data-bs-target="#login" type="button" role="tab" aria-controls="login" aria-selected="true">Iniciar Sesión</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="register-tab" data-bs-toggle="tab" data-bs-target="#register" type="button" role="tab" aria-controls="register" aria-selected="false">Registrarse</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="update-tab" data-bs-toggle="tab" data-bs-target="#update" type="button" role="tab" aria-controls="update" aria-selected="false">Actualizar Usuario</button>
            </li>
            <li class="nav-item" role="presentation">
                <button class="nav-link" id="eliminar-tab" data-bs-toggle="tab" data-bs-target="#eliminar" type="button" role="tab" aria-controls="eliminar" aria-selected="false">Eliminar Usuario</button>
            </li>
        </ul>


		<!-- Formulario de Login -->
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="login" role="tabpanel"
				aria-labelledby="login-tab">
				<form class="mt-4 formulario" id="form_login"
					action="<%=request.getContextPath()%>/login" method="POST">
					<div class="mb-3">
						<label for="correoLogin" class="form-label">Correo
							Electrónico</label> <input type="email" class="form-control"
							name="correo" id="correoLogin"
							placeholder="Introduce tu correo electrónico" required>
					</div>
					<div class="mb-3">
						<label for="contrasenyaLogin" class="form-label">Contraseña</label>
						<input type="password" class="form-control" name="contrasena"
							id="contrasenyaLogin" placeholder="Introduce tu contraseña"
							required>
					</div>
					<button type="submit" class="btn btn-primary w-100">Iniciar
						Sesión</button>
				</form>
			</div>

			<!-- Formulario de Registro -->
			<div class="tab-pane fade" id="register" role="tabpanel"
				aria-labelledby="register-tab">
				<form class="mt-4" action="<%=request.getContextPath()%>/registro"
					method="post">
					<div class="mb-3">
						<label for="nombreRegistro" class="form-label">Nombre</label> <input
							type="text" class="form-control" id="nombreRegistro"
							name="nombre" placeholder="Introduce tu nombre" required>
					</div>
					<div class="mb-3">
						<label for="apellidosRegistro" class="form-label">Apellidos</label>
						<input type="text" class="form-control" id="apellidosRegistro"
							name="apellidos" placeholder="Introduce tus apellidos" required>
					</div>
					<div class="mb-3">
						<label for="correoRegistro" class="form-label">Correo
							Electrónico</label> <input type="email" class="form-control"
							id="correoRegistro" name="correo"
							placeholder="Introduce tu correo electrónico" required>
					</div>
					<div class="mb-3">
						<label for="contrasenaRegistro" class="form-label">Contraseña</label>
						<input type="password" class="form-control"
							id="contrasenaRegistro" name="contrasena"
							placeholder="Introduce tu contraseña" required>
					</div>
					<div class="mb-3">
						<label for="direccionRegistro" class="form-label">Dirección</label>
						<input type="text" class="form-control" id="direccionRegistro"
							name="direccion" placeholder="Introduce tu dirección" required>
					</div>
					<div class="mb-3">
						<label for="telefonoRegistro" class="form-label">Teléfono</label>
						<input type="text" class="form-control" id="telefonoRegistro"
							name="telefono" placeholder="Introduce tu teléfono" required>
					</div>
					<div class="mb-3">
						<label for="rolRegistro" class="form-label">Rol</label> <select
							class="form-control" id="rolRegistro" name="rol" required>
							<option value="Usuario">Usuario</option>
							<option value="Administrador">Administrador</option>
						</select>
					</div>
					<button type="submit" class="btn btn-success w-100">Registrarse</button>
				</form>
			</div>
			   <!-- Formulario de Actualización de Usuario -->
            <div class="tab-pane fade" id="update" role="tabpanel" aria-labelledby="update-tab">
                <form action="<%=request.getContextPath()%>/actualizar" method="POST">
                    <div class="mb-3">
                        <label for="nombreUpdate" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombreUpdate" name="nombre" placeholder="Introduce tu nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="apellidosUpdate" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="apellidosUpdate" name="apellidos" placeholder="Introduce tus apellidos" required>
                    </div>
                    <div class="mb-3">
                        <label for="correoUpdate" class="form-label">Correo Electrónico</label>
                        <input type="email" class="form-control" id="correoUpdate" name="correo" placeholder="Introduce tu correo electrónico" required>
                    </div>
                    <div class="mb-3">
                        <label for="direccionUpdate" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="direccionUpdate" name="direccion" placeholder="Introduce tu dirección" required>
                    </div>
                    <div class="mb-3">
                        <label for="telefonoUpdate" class="form-label">Teléfono</label>
                        <input type="text" class="form-control" id="telefonoUpdate" name="telefono" placeholder="Introduce tu teléfono" required>
                    </div>
                    <button type="submit" class="btn btn-warning w-100">Actualizar Usuario</button>
                </form>
            </div>

			<!-- Formulario de Eliminar Usuario -->
			<div class="tab-pane fade" id="eliminar" role="tabpanel"
				aria-labelledby="eliminar-tab">
				<form action="<%=request.getContextPath()%>/eliminar" method="POST">
					<input type="hidden" name="_method" value="DELETE">
					<div class="mb-3">
						<label for="correoEliminar" class="form-label">Correo
							Electrónico</label> <input type="email" class="form-control"
							name="correo" id="correoEliminar"
							placeholder="Introduce el correo electrónico" required>
					</div>
					<div class="mb-3">
						<label for="contrasenaEliminar" class="form-label">Contraseña</label>
						<input type="password" class="form-control" name="contrasena"
							id="contrasenaEliminar"
							placeholder="Introduce tu contraseña para confirmar" required>
					</div>
					<div class="mb-3">
						<label for="confirmarContrasenaEliminar" class="form-label">Confirmar
							Contraseña</label> <input type="password" class="form-control"
							name="confirmarContrasena" id="confirmarContrasenaEliminar"
							placeholder="Confirma tu contraseña" required>
					</div>
					<button type="submit" class="btn btn-danger w-100">Eliminar
						Usuario</button>
				</form>

			</div>
			<!-- Script de validación para la confirmación de contraseña -->
			<script>
				document
						.getElementById("form_eliminar")
						.addEventListener(
								"submit",
								function(event) {
									var contrasena = document
											.getElementById("contrasenaEliminar").value;
									var confirmarContrasena = document
											.getElementById("confirmarContrasenaEliminar").value;

									if (contrasena !== confirmarContrasena) {
										event.preventDefault(); // Detiene el envío del formulario
										alert("Las contraseñas no coinciden. Por favor, verifica e inténtalo de nuevo.");
									}
								});
			</script>
		</div>

	</div>
	</div>

	<!-- Pie de página -->
	<footer
		style="background-color: #5B3E34; color: #C4D1AA; text-align: center; padding: 15px 0; margin-top: 20px;">
		<p style="margin: 0; font-size: 1rem;">© 2024 Bonsai Sur. Todos
			los derechos reservados.</p>
	</footer>

	<!-- Bootstrap JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
