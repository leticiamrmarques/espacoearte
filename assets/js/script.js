const bannerSlider = document.querySelector(".banner-slider");
const listItens = bannerSlider.querySelectorAll("ul li");
const images = bannerSlider.querySelectorAll(".slide");

let currentIndex = 0;
let intervalId;

// Função para mostrar uma imagem e atualizar a bolinha
function showSlide(index) {
  hideAllItens();
  hideAllImages();

  listItens[index].classList.add("active");
  images[index].classList.add("show");

  currentIndex = index;
}

// Esconde todas as bolinhas ativas
function hideAllItens() {
  listItens.forEach((item) => item.classList.remove("active"));
}

// Esconde todas as imagens
function hideAllImages() {
  images.forEach((img) => img.classList.remove("show"));
}

// Adiciona clique nas bolinhas
listItens.forEach((item, idx) => {
  item.addEventListener("click", () => {
    showSlide(idx);
    resetInterval(); // Reinicia o timer ao clicar
  });
});

// Timer automático a cada X segundos
function startAutoSlide() {
  intervalId = setInterval(() => {
    const nextIndex = (currentIndex + 1) % images.length;
    showSlide(nextIndex);
  }, 5000);
}

// Reinicia o timer (para não trocar logo depois do clique)
function resetInterval() {
  clearInterval(intervalId);
  startAutoSlide();
}

// Inicia o primeiro slide e o timer
showSlide(0);
startAutoSlide();
