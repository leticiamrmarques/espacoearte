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
      circulosProjeto[i].classList.toggle("active", i === contadorProjeto);
    });

    leftProjeto.style.opacity = contadorProjeto === 0 ? "0" : "1";
    rightProjeto.style.opacity =
      contadorProjeto === projetoImg.length - 1 ? "0" : "1";

    leftProjeto.style.cursor = contadorProjeto === 0 ? "default" : "pointer";
    rightProjeto.style.cursor =
      contadorProjeto === projetoImg.length - 1 ? "default" : "pointer";
  }

  rightProjeto.addEventListener("click", () => {
    if (contadorProjeto < projetoImg.length - 1) {
      contadorProjeto++;
      atualizarSlideProjeto();
    }
  });

  leftProjeto.addEventListener("click", () => {
    if (contadorProjeto > 0) {
      contadorProjeto--;
      atualizarSlideProjeto();
    }
  });

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
      circulosRealizados[i].classList.toggle(
        "active",
        i === contadorRealizados
      );
    });

    leftRealizados.style.opacity = contadorRealizados === 0 ? "0" : "1";
    rightRealizados.style.opacity =
      contadorRealizados === RealizadosImg.length - 1 ? "0" : "1";

    leftRealizados.style.cursor =
      contadorRealizados === 0 ? "default" : "pointer";
    rightRealizados.style.cursor =
      contadorRealizados === RealizadosImg.length - 1 ? "default" : "pointer";
  }

  rightRealizados.addEventListener("click", () => {
    if (contadorRealizados < RealizadosImg.length - 1) {
      contadorRealizados++;
      atualizarSlideRealizados();
    }
  });

  leftRealizados.addEventListener("click", () => {
    if (contadorRealizados > 0) {
      contadorRealizados--;
      atualizarSlideRealizados();
    }
  });

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
var acc = document.getElementsByClassName("accordion");
var panels = document.getElementsByClassName("panel");
var images = document.querySelectorAll("#etapas-img img");

function closeAllPanels() {
  for (let i = 0; i < panels.length; i++) {
    panels[i].style.display = "none";
    acc[i].classList.remove("active");
  }
}

function openPanel(index) {
  acc[index].classList.add("active");
  panels[index].style.display = "block";

  // Gerenciar imagens
  for (let j = 0; j < images.length; j++) {
    images[j].classList.remove("show");
  }
  images[index].classList.add("show");
}

function anyPanelOpen() {
  for (let i = 0; i < panels.length; i++) {
    if (panels[i].style.display === "block") {
      return true;
    }
  }
  return false;
}

// Comportamento ao clicar
for (let i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function () {
    const panel = this.nextElementSibling;

    if (i === 0) {
      // Section 1 não pode ser fechado clicando nele
      if (panel.style.display === "block") return;

      closeAllPanels();
      openPanel(0);
    } else {
      if (panel.style.display === "block") {
        panel.style.display = "none";
        this.classList.remove("active");

        // Se nenhum outro painel estiver aberto, reabre o primeiro
        if (!anyPanelOpen()) {
          openPanel(0);
        }
      } else {
        closeAllPanels();
        openPanel(i);
      }
    }
  });
}

// Abre o primeiro painel ao carregar
document.addEventListener("DOMContentLoaded", function () {
  openPanel(0);
});

//------------- Fim Accordion - Projeto de Interiores --------

//------------- Overlay - Portfólio Projeto de Interiores --------

(() => {
  // Coleção de imagens por categoria
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

    lazer: [
      "../assets/img/6.Overlay/Lazer/Lazer.png",
    ],

    corporativo: [

    ],

    comercial:[
      "../assets/img/6.Overlay/Comercial/Comercial.png",
    ],
  };

  // Seleção dos elementos
  const cards = document.querySelectorAll(".portfolio-card");
  const overlay = document.getElementById("overlay");
  const slideImage = document.getElementById("slide-image");
  const closeBtn = document.getElementById("close-btn");
  const prevBtn = document.getElementById("prev-btn");
  const nextBtn = document.getElementById("next-btn");

  let currentImages = [];
  let currentIndex = 0;

  // Função para mostrar a imagem
  function showSlide(index) {
    slideImage.src = currentImages[index];
    slideImage.alt = `Imagem ${index + 1} da categoria`;
    console.log(`Mostrando imagem ${index + 1} de ${currentImages.length}`);
  }

  // Função para abrir overlay
  function openOverlay(category) {
    console.log(`Tentando abrir overlay para categoria: ${category}`);

    if (!imageCollections[category]) {
      console.warn(
        `Categoria "${category}" não encontrada em imageCollections.`
      );
      return;
    }

    currentImages = imageCollections[category];
    currentIndex = 0;
    showSlide(currentIndex);

    overlay.classList.add("active");
    document.body.style.overflow = "hidden";
  }

  // Função para fechar overlay
  function closeOverlay() {
    overlay.classList.remove("active");
    document.body.style.overflow = "auto";
    console.log("Overlay fechado");
  }

  // Eventos nos cards
  cards.forEach((card) => {
    card.addEventListener("click", () => {
      const category = card.getAttribute("data-category");
      console.log(`Card clicado, categoria: ${category}`);
      openOverlay(category);
    });
  });

  // Navegação anterior
  prevBtn.addEventListener("click", () => {
    if (currentImages.length === 0) return;
    currentIndex =
      (currentIndex - 1 + currentImages.length) % currentImages.length;
    showSlide(currentIndex);
  });

  // Navegação próxima
  nextBtn.addEventListener("click", () => {
    if (currentImages.length === 0) return;
    currentIndex = (currentIndex + 1) % currentImages.length;
    showSlide(currentIndex);
  });

  // Botão fechar
  closeBtn.addEventListener("click", closeOverlay);

  // Fechar clicando fora da imagem
  overlay.addEventListener("click", (e) => {
    if (e.target === overlay) {
      closeOverlay();
    }
  });

  // Garantir que o script rode após o DOM estar carregado
  document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM carregado, script do overlay pronto.");
  });
})();
