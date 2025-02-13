<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bonsai Sur</title>
<link rel="icon" href="imagenes/LogoTienda.jpg" type="image/jpg">
<!--  CSS -->
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
			<a class="navbar-brand" href="#"><img src="imagenes/LogoTienda.jpg" alt="Logo Bonsai Sur">BONSAI
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
					<li class="nav-item"><a class="nav-link" href="index.jsp">INICIO</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="arbolesDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">ARBOLES</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="arboles.jsp">Bonsáis</a></li>
							<li><a class="dropdown-item" href="arboles.jsp">Prebonsáis</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="macetas.jsp"
						id="macetasDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">MACETAS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="macetas.jsp">Cerámica</a></li>
							<li><a class="dropdown-item" href="macetas.jsp">Plástico</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="abonos.jsp"
						id="abonosDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">ABONOS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="abonos.jsp">Orgánicos</a></li>
							<li><a class="dropdown-item" href="abonos.jsp">Químicos</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="herramientasDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false">HERRAMIENTAS</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="herramientas.jsp">Corte</a></li>
							<li><a class="dropdown-item" href="herramientas.jsp">Mantenimiento</a></li>
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
				<a href="carrito.jsp" class="me-3 icono"><i class="bi bi-cart"></i></a>
				<a href="login.jsp" class="icono"><i class="bi bi-person"></i></a>
			</div>
		</div>
	</nav>

	<!-- Bloque de Contenido -->
	<div class="container my-4">
		<div class="content-block">
			<!-- Imagen izquierda -->
			<div class="left-image agrandar">
				<a href="herramientas.jsp"><img src="imagenes/Herramienta.jpg"
					alt="Bonsái Izquierda"></a>
			</div>

			<!-- Imágenes centrales -->
			<div class="center-images">
				<a class="agrandar" href="arboles.jsp"><img
					src="imagenes/Bogambilla.jpg" alt="Bonsái Central Arriba"></a> <a
					class="agrandar" href="macetas.jsp"><img
					src="imagenes/Macetas.jpg" alt="Bonsái Central Abajo"></a>
			</div>

			<!-- Imagen derecha -->
			<div class="right-image agrandar">
				<a href="abonos.jsp"><img src="imagenes/Abono.jpg"
					alt="Bonsái Derecha"></a>
			</div>
		</div>
	</div>
	<!-- Bloque "¿Quiénes somos?" -->
	<div class="quienes-somos-section" style="position: relative;">
		<img src="imagenes/Repisas.png" alt="Fondo ¿Quiénes somos?"
			style="width: 100%; height: auto; object-fit: cover;">
		<div
			style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: white; text-align: center; background-color: rgba(0, 0, 0, 0.5); padding: 20px; border-radius: 8px;">
			<h1 style="font-size: 2.5rem; font-weight: bold;">¿QUIÉNES
				SOMOS?</h1>
			<p style="font-size: 1.2rem; line-height: 1.6;">Esta tienda es un
				proyecto de fin de grado donde se muestran imágenes de árboles en
				disponibilidad.</p>
		</div>
	</div>
	<br>
	<!--Carrusel-->
	<div id="carouselExampleIndicators" class="carousel slide"
		data-bs-ride="carousel">
		<!-- Indicadores -->
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="3" class="active" aria-current="true"
				aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<!-- Slides -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img class="d-block w-100" src="imagenes/Bogambilla.jpg"
					alt="First slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="imagenes/Bogambilla.jpg"
					alt="Second slide">
			</div>
			<div class="carousel-item">
				<img class="d-block w-100" src="imagenes/Bogambilla.jpg"
					alt="Third slide">
			</div>
		</div>
		<!-- Controles -->
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- Pie de página -->
	<footer
		style="background-color: #5B3E34; color: #C4D1AA; text-align: center; padding: 15px 0; margin-top: 20px;">
		<p style="margin: 0; font-size: 1rem;">© 2024 Bonsai Sur. Todos
			los derechos reservados.</p>
	</footer>
	<!-- CSS -->
	<link rel="stylesheet" href="estilo.css">
	<!-- Bootstrap JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Obtén el nombre del archivo actual
        const currentPath = window.location.pathname.split("/").pop();

        // Selecciona todos los enlaces de la barra de navegación
        const navLinks = document.querySelectorAll(".nav-link");

        // Recorre los enlaces y compara la ruta actual con el href de cada enlace
        navLinks.forEach(link => {
            const href = link.getAttribute("href");

            if (href === currentPath) {
                link.classList.add("active");
            }
        });
    });
</script>
</body>
</html>
