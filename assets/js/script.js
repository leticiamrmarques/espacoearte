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

