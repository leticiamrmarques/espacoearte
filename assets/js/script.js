//------------- Index Banner-------------------
const bannerSlider = document.querySelector(".banner-slider");

if (bannerSlider) {
  const listItens = bannerSlider.querySelectorAll("ul li");
  const images = bannerSlider.querySelectorAll(".slide");
  let currentIndex = 0;
  let intervalId;

  function showSlide(index) {
    hideAllItens();
    hideAllImages();
    listItens[index].classList.add("active");
    images[index].classList.add("show");
    currentIndex = index;
  }

  function hideAllItens() {
    listItens.forEach((item) => item.classList.remove("active"));
  }

  function hideAllImages() {
    images.forEach((img) => img.classList.remove("show"));
  }

  listItens.forEach((item, idx) => {
    item.addEventListener("click", () => {
      showSlide(idx);
      resetInterval();
    });
  });

  function startAutoSlide() {
    intervalId = setInterval(() => {
      const nextIndex = (currentIndex + 1) % images.length;
      showSlide(nextIndex);
    }, 5000);
  }

  function resetInterval() {
    clearInterval(intervalId);
    startAutoSlide();
  }

  showSlide(0);
  startAutoSlide();
}
//------------- Fim Banner-------------------

//------------- Index Projeto slides --------
const projetoImg = document.querySelectorAll(
  ".projeto-interiores-carousel-imgs"
);

if (projetoImg.length > 0) {
  const leftProjeto = document.getElementById("left-projeto");
  const rightProjeto = document.getElementById("right-projeto");
  const circulosProjeto = document.querySelectorAll(
    ".carousel-circle-navigation ul li"
  );

  let contadorProjeto = 0;

  function atualizarSlideProjeto() {
    projetoImg.forEach((img, i) => {
      img.classList.toggle("projeto-ativo", i === contadorProjeto);
      if (circulosProjeto[i]) {
        circulosProjeto[i].classList.toggle("active", i === contadorProjeto);
      }
    });

    if (leftProjeto && rightProjeto) {
      leftProjeto.style.opacity = contadorProjeto === 0 ? "0" : "1";
      rightProjeto.style.opacity =
        contadorProjeto === projetoImg.length - 1 ? "0" : "1";

      leftProjeto.style.cursor = contadorProjeto === 0 ? "default" : "pointer";
      rightProjeto.style.cursor =
        contadorProjeto === projetoImg.length - 1 ? "default" : "pointer";
    }
  }

  if (rightProjeto) {
    rightProjeto.addEventListener("click", () => {
      if (contadorProjeto < projetoImg.length - 1) {
        contadorProjeto++;
        atualizarSlideProjeto();
      }
    });
  }

  if (leftProjeto) {
    leftProjeto.addEventListener("click", () => {
      if (contadorProjeto > 0) {
        contadorProjeto--;
        atualizarSlideProjeto();
      }
    });
  }

  circulosProjeto.forEach((circulo, i) => {
    circulo.addEventListener("click", () => {
      contadorProjeto = i;
      atualizarSlideProjeto();
    });
  });

  window.addEventListener("load", atualizarSlideProjeto);
}
//------------- Fim Projeto slides --------

//------------- Moveis Realizados slides --------
const RealizadosImg = document.querySelectorAll(
  ".trabalhos-realizados-carousel-imgs"
);

if (RealizadosImg.length > 0) {
  const leftRealizados = document.getElementById("left-moveis");
  const rightRealizados = document.getElementById("right-moveis");
  const circulosRealizados = document.querySelectorAll(
    "#navegacao-trabalhos ul li"
  );

  let contadorRealizados = 0;

  function atualizarSlideRealizados() {
    RealizadosImg.forEach((img, i) => {
      img.classList.toggle("realizados-ativo", i === contadorRealizados);
      if (circulosRealizados[i]) {
        circulosRealizados[i].classList.toggle(
          "active",
          i === contadorRealizados
        );
      }
    });

    if (leftRealizados && rightRealizados) {
      leftRealizados.style.opacity = contadorRealizados === 0 ? "0" : "1";
      rightRealizados.style.opacity =
        contadorRealizados === RealizadosImg.length - 1 ? "0" : "1";

      leftRealizados.style.cursor =
        contadorRealizados === 0 ? "default" : "pointer";
      rightRealizados.style.cursor =
        contadorRealizados === RealizadosImg.length - 1 ? "default" : "pointer";
    }
  }

  if (rightRealizados) {
    rightRealizados.addEventListener("click", () => {
      if (contadorRealizados < RealizadosImg.length - 1) {
        contadorRealizados++;
        atualizarSlideRealizados();
      }
    });
  }

  if (leftRealizados) {
    leftRealizados.addEventListener("click", () => {
      if (contadorRealizados > 0) {
        contadorRealizados--;
        atualizarSlideRealizados();
      }
    });
  }

  circulosRealizados.forEach((circulo, i) => {
    circulo.addEventListener("click", () => {
      contadorRealizados = i;
      atualizarSlideRealizados();
    });
  });

  window.addEventListener("load", atualizarSlideRealizados);
}
//------------- Fim Realizados slides --------

//------------- Accordion - Projeto de Interiores --------
document.addEventListener("DOMContentLoaded", function () {
  const acc = document.getElementsByClassName("accordion");
  const panels = document.getElementsByClassName("panel");
  const images = document.querySelectorAll("#etapas-img img");

  function closeAllPanels() {
    for (let i = 0; i < panels.length; i++) {
      panels[i].style.display = "none";
      acc[i].classList.remove("active");
    }
  }

  function openPanel(index) {
    if (!acc[index] || !panels[index]) return;
    acc[index].classList.add("active");
    panels[index].style.display = "block";

    images.forEach((img) => img.classList.remove("show"));
    if (images[index]) images[index].classList.add("show");
  }

  function anyPanelOpen() {
    for (let i = 0; i < panels.length; i++) {
      if (panels[i].style.display === "block") return true;
    }
    return false;
  }

  for (let i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function () {
      const panel = this.nextElementSibling;

      if (!panel) return;

      if (i === 0) {
        if (panel.style.display === "block") return;
        closeAllPanels();
        openPanel(0);
      } else {
        if (panel.style.display === "block") {
          panel.style.display = "none";
          this.classList.remove("active");
          if (!anyPanelOpen()) openPanel(0);
        } else {
          closeAllPanels();
          openPanel(i);
        }
      }
    });
  }

  openPanel(0); // abre o primeiro ao carregar
});
//------------- Fim Accordion --------

//------------- Overlay - Portfólio Projeto de Interiores --------
(() => {
  const imageCollections = {
    cozinha: [
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha1.jpeg",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha2.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha3.jpeg",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha4.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha5.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha6.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha7.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha8.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha9.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha10.png",
      "../assets/img/6.Overlay/Cozinha/projetos-cozinha11.png",
    ],
    quarto: [
      "../assets/img/6.Overlay/Quarto/Quarto (1).png",
      "../assets/img/6.Overlay/Quarto/Quarto (2).png",
      "../assets/img/6.Overlay/Quarto/Quarto (3).png",
      "../assets/img/6.Overlay/Quarto/Quarto (4).png",
      "../assets/img/6.Overlay/Quarto/Quarto (5).png",
      "../assets/img/6.Overlay/Quarto/Quarto (6).png",
      "../assets/img/6.Overlay/Quarto/Quarto (7).png",
      "../assets/img/6.Overlay/Quarto/Quarto (8).png",
      "../assets/img/6.Overlay/Quarto/Quarto (9).png",
      "../assets/img/6.Overlay/Quarto/Quarto (10).png",
      "../assets/img/6.Overlay/Quarto/Quarto (11).png",
      "../assets/img/6.Overlay/Quarto/Quarto (12).png",
      "../assets/img/6.Overlay/Quarto/Quarto (13).png",
      "../assets/img/6.Overlay/Quarto/Quarto (14).png",
      "../assets/img/6.Overlay/Quarto/Quarto (15).png",
      "../assets/img/6.Overlay/Quarto/Quarto (16).png",
      "../assets/img/6.Overlay/Quarto/Quarto (17).png",
      "../assets/img/6.Overlay/Quarto/Quarto (18).png",
      "../assets/img/6.Overlay/Quarto/Quarto (19).png",
      "../assets/img/6.Overlay/Quarto/Quarto (20).png",
      "../assets/img/6.Overlay/Quarto/Quarto (21).png",
      "../assets/img/6.Overlay/Quarto/Quarto (22).png",
    ],
    closet: [
      "../assets/img/6.Overlay/Closet/Closet (1).png",
      "../assets/img/6.Overlay/Closet/Closet (2).png",
      "../assets/img/6.Overlay/Closet/Closet (3).png",
    ],
    sala: [
      "../assets/img/6.Overlay/Salas/Sala1.jpeg",
      "../assets/img/6.Overlay/Salas/Sala2.jpg",
      "../assets/img/6.Overlay/Salas/Sala3.png",
      "../assets/img/6.Overlay/Salas/Sala4.jpg",
      "../assets/img/6.Overlay/Salas/Sala5.png",
      "../assets/img/6.Overlay/Salas/Sala6.png",
      "../assets/img/6.Overlay/Salas/Sala7.png",
      "../assets/img/6.Overlay/Salas/Sala8.png",
      "../assets/img/6.Overlay/Salas/Sala9.png",
      "../assets/img/6.Overlay/Salas/Sala10.png",
      "../assets/img/6.Overlay/Salas/Sala11.png",
      "../assets/img/6.Overlay/Salas/Sala12.png",
      "../assets/img/6.Overlay/Salas/Sala13.png",
      "../assets/img/6.Overlay/Salas/Sala14.png",
      "../assets/img/6.Overlay/Salas/Sala15.png",
      "../assets/img/6.Overlay/Salas/Sala16.png",
      "../assets/img/6.Overlay/Salas/Sala17.png",
      "../assets/img/6.Overlay/Salas/Sala18.png",
    ],
    banheiro: [
      "../assets/img/6.Overlay/Banheiro/banheiro (1).png",
      "../assets/img/6.Overlay/Banheiro/banheiro (2).png",
    ],
    escritorio: [
      "../assets/img/6.Overlay/Escritorio/Escritorio (1).png",
      "../assets/img/6.Overlay/Escritorio/Escritorio (2).png",
      "../assets/img/6.Overlay/Escritorio/Escritorio (3).png",
    ],
    lazer: ["../assets/img/6.Overlay/Lazer/Lazer.png"],
    corporativo: [],
    comercial: ["../assets/img/6.Overlay/Comercial/Comercial.png"],
  };

  const cards = document.querySelectorAll(".portfolio-card");
  const overlay = document.getElementById("overlay");
  const slideImage = document.getElementById("slide-image");
  const closeBtn = document.getElementById("close-btn");
  const prevBtn = document.getElementById("prev-btn");
  const nextBtn = document.getElementById("next-btn");

  let currentImages = [];
  let currentIndex = 0;

  function showSlide(index) {
    if (!slideImage || !currentImages[index]) return;
    slideImage.src = currentImages[index];
    slideImage.alt = `Imagem ${index + 1} da categoria`;
  }

  function openOverlay(category) {
    if (!imageCollections[category]) return;
    currentImages = imageCollections[category];
    currentIndex = 0;
    showSlide(currentIndex);

    if (overlay) {
      overlay.classList.add("active");
      document.body.style.overflow = "hidden";
    }
  }

  function closeOverlay() {
    if (overlay) {
      overlay.classList.remove("active");
      document.body.style.overflow = "auto";
    }
  }

  cards.forEach((card) => {
    card.addEventListener("click", () => {
      const category = card.getAttribute("data-category");
      openOverlay(category);
    });
  });

  if (closeBtn) closeBtn.addEventListener("click", closeOverlay);

  if (prevBtn) {
    prevBtn.addEventListener("click", () => {
      if (currentImages.length === 0) return;
      currentIndex =
        (currentIndex - 1 + currentImages.length) % currentImages.length;
      showSlide(currentIndex);
    });
  }

  if (nextBtn) {
    nextBtn.addEventListener("click", () => {
      if (currentImages.length === 0) return;
      currentIndex = (currentIndex + 1) % currentImages.length;
      showSlide(currentIndex);
    });
  }

  if (overlay) {
    overlay.addEventListener("click", (e) => {
      if (e.target === overlay) closeOverlay();
    });
  }
})();
