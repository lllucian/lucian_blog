<template>
  <Carousel :wrap-around="true" :autoplay="autoplay">
    <Slide v-for="slide in 10" :key="slide">
      <div class="carousel__item" :style="{height: pageHeight}">
          <div class="row slider-text justify-content-center align-items-center" style="width: 100%;">
            <div class="col-md-6 text">
              <div class="author mb-4 d-flex align-items-center">
                <a href="#" class="img" style="background-image: url('/src/assets/person_1.jpg');"></a>
                <div class="ml-3 info">
                  <span>Written by</span>
                  <h3><a href="#">Dave Lewis</a>, <span>October 04, 2018</span></h3>
                </div>
              </div>
              <div class="text-2">
                <span class="big">Lucian</span>
                <h1 class="mb-4"><a href="#">Switzerland famous for chocolate making</a></h1>
                <p class="mb-4">A small river named Duden flows by their place and supplies it with the necessary regelialia</p>
                <p><a href="#" class="btn btn-primary p-3 px-xl-4 py-xl-3">Continue Reading</a></p>
              </div>
            </div>
            <div class="img col-md-6" style="background-image: url('/src/assets/bg_1.jpg');width:100%;"
                 :style="{height: pageHeight}"></div>
        </div>

      </div>
    </Slide>

    <template #addons>
      <Pagination/>
    </template>
  </Carousel>
</template>

<script>
import {defineComponent, onMounted, ref, toRefs} from 'vue'
import {Carousel, Navigation, Pagination, Slide} from 'vue3-carousel';

import 'vue3-carousel/dist/carousel.css';

export default defineComponent({
  components: {
    Carousel,
    Slide,
    Pagination,
    Navigation,
  },
  props: {
    isAutoPlay: {type: Boolean, default: false},
    playSpeed: {type: Number, default: 5000}
  },
  setup(props) {
    const pageHeight = ref(window.innerHeight + "px");
    const pageWidth = ref(window.innerWidth + "px");
    const {isAutoPlay, playSpeed} = toRefs(props);
    const autoplay = ref(0);
    if (isAutoPlay.value) autoplay.value = playSpeed.value;

    const getPageRiseHeight = async () => {
      pageHeight.value = window.innerHeight + "px";
      pageWidth.value = window.innerWidth + "px";
    };

    onMounted(() => {
      window.addEventListener("resize", getPageRiseHeight);
    });
    return {pageHeight, pageWidth, autoplay}
  }
});
</script>

<style>
.carousel__item {
  min-height: 200px;
  width: 100%;
  color: var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  /*padding: 10px;*/
}

.carousel__pagination {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 40px;
  width: 100%;
  text-align: center;
}

.carousel__pagination-button {
  background-color: #fff;
}

.carousel__pagination-button--active {
  background-color: #000;
}
</style>
<style scoped>
.img {
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
}
</style>